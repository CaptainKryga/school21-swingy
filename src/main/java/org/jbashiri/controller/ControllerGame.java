package org.jbashiri.controller;

import org.jbashiri.model.Enemy;
import org.jbashiri.model.Player;
import org.jbashiri.utils.Vector2;
import org.jbashiri.view.game.UIGame;
import org.jbashiri.view.game.UIGameConsole;
import org.jbashiri.view.game.UIGameGUI;

import java.util.Scanner;

import static org.jbashiri.utils.CustomMath.getRandom;
import static org.jbashiri.utils.CustomMath.getRandomCustom;

public class ControllerGame {
    private UIGame uiGame;
    private Player player;

    private boolean isLoop;
    private int[][] mapEnemy;
    private char[][] mapPlayer;
    private Vector2 pos;
    private int sizeMap;
    private boolean isWin = false;
    private boolean isDefeat = false;
    private Enemy enemy;

    public void init(String name, String clas, boolean isConsole) {
        player = new Player(name, clas);
        switchUI(isConsole);
    }

    public void switchUI(boolean isConsole) {
        if (uiGame != null)
            uiGame = null;
        uiGame = getGame(isConsole);
        uiGame.printPlayerInfo(player);

        if (!isLoop) {
            Loop();
            isLoop = true;
        }
    }

    private void Loop() {
        Scanner sc = new Scanner(System.in);
        uiGame.printDirections();

        mapEnemy = generateEnemy(getSizeMap());
        mapPlayer = generatePlayer(getSizeMap());

        uiGame.printMapEnemy(mapEnemy);
        uiGame.printMapPlayer(mapPlayer);

        uiGame.printDivider();

        //name
        while (sc.hasNextLine()) {
            String line = sc.nextLine().toLowerCase();

            if (line.equals("north")) {
                UpdateMap(sc, new Vector2(-1, 0));
            } else if (line.equals("south")) {
                UpdateMap(sc, new Vector2(1, 0));
            } else if (line.equals("west")) {
                UpdateMap(sc, new Vector2(0, -1));
            } else if (line.equals("east")) {
                UpdateMap(sc, new Vector2(0, 1));
            } else {
                uiGame.inputError();
            }

            if (isWin) {
                uiGame.printWin(player.getScore());
                break;
            }
            if (isDefeat) {
                uiGame.printDefeat(player.getScore());
                break;
            }

            uiGame.printDirections();
            uiGame.printMapEnemy(mapEnemy);
            uiGame.printMapPlayer(mapPlayer);
            uiGame.printDivider();
        }
        sc.close();
    }

    private UIGame getGame(boolean isConsole) {
        if (isConsole) {
            return new UIGameConsole();
        } else {
            return new UIGameGUI();
        }
    }

    private int getSizeMap() {
        return (player.getLevel() - 1) * 5 + 10;
    }

    private int[][] generateEnemy(int size) {
        int[][] map = new int[size][size];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (getRandom(0, 100) > 65) {
                    map[x][y] = player.getLevel();
                } else {
                    map[x][y] = 0;
                }
            }
        }
        return map;
    }

    private char[][] generatePlayer(int size) {
        char[][] map = new char[size][size];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                map[x][y] = '#';
            }
        }
        sizeMap = size;
        size /= 2;
        pos = new Vector2(size, size);
        map[pos.x][pos.y] = 'P';
        return map;
    }

    private void UpdateMap(Scanner sc, Vector2 vec) {
        mapPlayer[pos.x][pos.y] = '*';
        pos = new Vector2(this.pos.x + vec.x, this.pos.y + vec.y);

        //win
        if (pos.x < 0 || pos.x >= sizeMap ||
                pos.y < 0 || pos.y >= sizeMap) {
            isWin = true;
            return;
        }

        mapPlayer[pos.x][pos.y] = 'P';
        //fight
        if (mapEnemy[pos.x][pos.y] != 0) {
            mapEnemy[pos.x][pos.y] = 0;
            Fight(sc);
            return;
        }
    }

    private void Fight(Scanner sc) {
        enemy = new Enemy(player.getLevel());
        boolean isStartFight = true;
        uiGame.printFight(player, enemy);
        uiGame.printStartFight();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().toLowerCase();

            if (isStartFight && line.equals("fight")) {
                uiGame.printDivider();
                isStartFight = false;
            } else if (line.equals("info")) {
                uiGame.printPlayerInfo(player);
                uiGame.printDivider();
            } else if (line.equals("atk")) {
                uiGame.printDivider();
                //roll
                int rndPlayer = getRandom(player.getHeroClass().getLuck(), 100);
                int rndEnemy = getRandom(enemy.luck, 100);

                //atk
                int atkPlayer = getRandomCustom(
                        player.getHeroClass().getAttack() + player.getArtifactWeapon().getBonusAttack(),
                        player.getHeroClass().getLuck());
                int atkEnemy = getRandomCustom(enemy.attack + enemy.luck, enemy.luck);

                uiGame.printFightFirstAttack(rndPlayer >= rndEnemy, rndPlayer, rndEnemy,
                        rndPlayer >= rndEnemy ? atkPlayer : atkEnemy);

                if (rndPlayer >= rndEnemy) {
                    enemy.hp -= atkPlayer;
                    if (enemy.hp < 0) {
                        player.gainExperience(450 * player.getLevel());
                        return;
                    }
                } else {
                    player.getHeroClass().hp -= atkEnemy;
                    if (player.getHeroClass().hp <= 0) {
                        isDefeat = true;
                        return;
                    }
                }

                //roll
                int chancePlayer = getRandom(player.getHeroClass().getLuck(), 100);
                int chanceEnemy = getRandom(enemy.luck, 100);

                if (rndPlayer >= rndEnemy && chanceEnemy >= 50) {
                    player.getHeroClass().hp -= atkEnemy;
                    if (player.getHeroClass().hp <= 0) {
                        isDefeat = true;
                        return;
                    }
                } else if (chancePlayer >= 50) {
                    enemy.hp -= atkPlayer;
                    if (enemy.hp < 0) {
                        player.gainExperience(450 * player.getLevel());
                        return;
                    }
                }

                uiGame.printFightSecondAttack(rndPlayer >= rndEnemy, rndPlayer >= rndEnemy ? chanceEnemy : chancePlayer,
                        rndPlayer >= rndEnemy ? atkEnemy : atkPlayer);

                uiGame.printFight(player, enemy);
                uiGame.printDivider();

            }

            if (!isStartFight) {
                uiGame.printAttack();
            }
        }



        //if FIGHT => fight

        //else RUN => if rnd > 50 to RUN if < 50 to FIGHT

    }
}
