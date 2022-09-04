package org.jbashiri.controller;

import org.jbashiri.model.Enemy;
import org.jbashiri.model.Player;
import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.utils.CustomLogger;
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

    private Artifact tempArtifact;

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

        mapEnemy = generateEnemy(getSizeMap());
        mapPlayer = generatePlayer(getSizeMap());

        uiGame.printMapEnemy(mapEnemy);
        uiGame.printMapPlayer(mapPlayer);
        uiGame.printDirections();

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
            } else if (line.equals("health")) {
                int rnd = player.useHealthBank();
                uiGame.printUseHealthBank(rnd);
            } else if (line.equals("switch")) {
                uiGame.printSwitch(false);
            }  else if (line.equals("info")) {
                uiGame.printPlayerInfo(player);
                uiGame.printDivider();
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

            uiGame.printMapEnemy(mapEnemy);
            uiGame.printMapPlayer(mapPlayer);
            uiGame.printDirections();
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
        }
    }

    private void Fight(Scanner sc) {
        enemy = new Enemy(player.getLevel());
        boolean isStartFight = true;
        uiGame.printFight(player, enemy);
        uiGame.printStartFight();
        uiGame.printDivider();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().toLowerCase();

            if (isStartFight && line.equals("fight")) {
                isStartFight = false;
            } else if (line.equals("leave")) {
                int rnd = getRandom(player.getHeroClass().luck, 100);
                uiGame.printChanceLeave(rnd > 50, rnd);
                if (rnd > 50) {
                    uiGame.printDivider();
                    return;
                }

                uiGame.printFight(player, enemy);
                uiGame.printDivider();
                uiGame.printAttack();
            } else if (line.equals("atk")) {
                //roll
                int rndPlayer = getRandom(player.getHeroClass().getLuck(), 100);
                int rndEnemy = getRandom(enemy.luck, 100);

                //atk               attack +- 10%
                int atkPlayer = getRandomCustom(player.getHeroClass().getAtk() +
                        player.getArtifactWeapon().getBonusAttack()) - enemy.defence;
                int atkEnemy = getRandomCustom(enemy.attack) - player.getHeroClass().def;


                if (atkPlayer <= 0)
                    atkPlayer = player.getHeroClass().atk / 10;
                if (atkEnemy <= 0)
                    atkEnemy = enemy.attack / 10;


                uiGame.printFightFirstAttack(rndPlayer >= rndEnemy, rndPlayer, rndEnemy,
                        rndPlayer >= rndEnemy ? atkPlayer : atkEnemy);

                if (rndPlayer >= rndEnemy && playerAtk(sc, atkPlayer)) {
                    return;
                } else if (rndPlayer < rndEnemy && enemyAtk(atkEnemy)) {
                    return;
                }

                //roll
                int chancePlayer = getRandom(player.getHeroClass().luck, 100);
                int chanceEnemy = getRandom(enemy.luck, 100);

                uiGame.printFightSecondAttack(rndPlayer >= rndEnemy, rndPlayer >= rndEnemy ? chanceEnemy : chancePlayer,
                        rndPlayer >= rndEnemy ? atkEnemy : atkPlayer);

                if (rndPlayer >= rndEnemy && chanceEnemy >= 50 && enemyAtk(atkEnemy)) {
                    return;
                } else if (rndPlayer < rndEnemy && chancePlayer >= 50 && playerAtk(sc, atkPlayer)) {
                    return;
                }

                uiGame.printFight(player, enemy);

            } else {
                uiGame.inputError();
                uiGame.printStartFight();
                uiGame.printDivider();
            }

            if (!isStartFight) {
                uiGame.printAttack();
                uiGame.printDivider();
            }
        }
    }

    private boolean playerAtk(Scanner sc, int atkPlayer) {
        CustomLogger.singleton.printLog("Player Attack: " + atkPlayer, 2);
        enemy.hp -= atkPlayer;
        if (enemy.hp < 0) {
            uiGame.playerKillEnemy(450 * player.getLevel());
            player.gainExperience(450 * player.getLevel());

            int temp = getRandom(0, 100);
            if (temp > 80) {
                CustomLogger.singleton.printLog("CREATE ARTIFACT: Random: " + temp, 2);
                tempArtifact = new Artifact(player.getLevel(), player.getHeroClass().luck);
                uiGame.printArtifact(player.getNowArtifact(tempArtifact.getType()), tempArtifact);
                uiGame.printSetOrDestroyArtifact();

                while (sc.hasNextLine()) {
                    String line = sc.nextLine().toLowerCase();

                    if (line.equals("set")) {
                        player.updateArtifact(tempArtifact);
                        break;
                    } else if (line.equals("ignore")) {
                        break;
                    } else {
                        uiGame.inputError();
                        uiGame.printSetOrDestroyArtifact();
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean enemyAtk(int atkEnemy) {
        CustomLogger.singleton.printLog("Enemy Attack: " + atkEnemy, 2);
        player.getHeroClass().hp -= atkEnemy;
        if (player.getHeroClass().hp <= 0) {
            isDefeat = true;
            return true;
        }
        return false;
    }
}
