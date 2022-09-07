package org.jbashiri.model;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Game;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.DataBase;
import org.jbashiri.utils.Vector2;
import org.jbashiri.view.game.V_Game;
import org.jbashiri.view.game.V_GameConsole;
import org.jbashiri.view.game.V_GameGUI;

import static org.jbashiri.utils.CustomMath.getRandom;
import static org.jbashiri.utils.CustomMath.getRandomCustom;
import static org.jbashiri.utils.DataBase.addNewHero;

public class M_Game {
    private V_Game v_game;
    private V_Game v_gameConsole, v_gameGui;

    private CustomEnums.StateGame state;
    private Player player;
    private Enemy enemy;
    private Artifact tempArtifact;


    public M_Game(C_Game c_game) {
        v_gameConsole = new V_GameConsole(c_game, this);
        v_gameGui = new V_GameGUI(c_game, this);
    }

    public void initPlayer(boolean isLoad, String playerName, CustomEnums.HeroClass heroClass) {
        if (isLoad) {
            this.player = new Player(playerName, heroClass);
            addNewHero(player);
        } else {
            this.player = DataBase.getHero(playerName);
        }

        player.mapEnemy = generateEnemy(getSizeMap());
        player.mapPlayer = generatePlayer(getSizeMap());

        tempArtifact = new Artifact();

        state = CustomEnums.StateGame.MOVE;
    }

    public void reInit(boolean isConsole) throws CustomException {
        if (v_game != null)
            v_game.switchUI(state, isConsole);
        v_game = switchView(isConsole);
        v_game.switchUI(state, isConsole);
    }

    public V_Game switchView(boolean isConsole) {
        return isConsole ? v_gameConsole : v_gameGui;
    }

    public V_Game isConsole() {
        return v_game;
    }

    public Player getPlayer() {
        return player;
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
        size /= 2;
        player.pos = new Vector2(size, size);
        map[player.pos.x][player.pos.y] = 'P';
        return map;
    }

    public int[][] getMapEnemy() {
        return player.mapEnemy;
    }

    public char[][] getMapPlayer() {
        return player.mapPlayer;
    }

    public void btnPress(CustomEnums.ButtonGame btn) throws CustomException {
        CustomLogger.singleton.printLog("S: " + state + ", B: " + btn, 2);
        if (state == CustomEnums.StateGame.MOVE) {
            if (btn == CustomEnums.ButtonGame.NORTH) {
                UpdateMap(new Vector2(-1, 0));
            } else if (btn == CustomEnums.ButtonGame.SOUTH) {
                UpdateMap(new Vector2(1, 0));
            } else if (btn == CustomEnums.ButtonGame.WEST) {
                UpdateMap(new Vector2(0, -1));
            } else if (btn == CustomEnums.ButtonGame.EAST) {
                UpdateMap(new Vector2(0, 1));
            } else if (btn == CustomEnums.ButtonGame.SWITCH) {
                reInit(!(isConsole() == v_gameConsole));
            } else if (btn == CustomEnums.ButtonGame.INFO) {
                v_game.printPlayerInfo(player);
                v_game.printMapPlayer(player.mapPlayer);
                v_game.printDirections();
                v_game.printDivider();
            } else if (btn == CustomEnums.ButtonGame.HEALTH) {
                useHealthBank();
                v_game.printMapPlayer(player.mapPlayer);
                v_game.printDirections();
                v_game.printDivider();
            } else {
                v_game.inputError();
                v_game.printMapPlayer(getMapPlayer());
                v_game.printDirections();
                v_game.printDivider();
            }
            DataBase.saveHero(player);
        } else if (state == CustomEnums.StateGame.PREFIGHT) {
            if (btn == CustomEnums.ButtonGame.LEAVE) {
                leaveFight();
            } else if (btn == CustomEnums.ButtonGame.FIGHT) {
                v_game.printAttack();
                v_game.printDivider();
                state = CustomEnums.StateGame.FIGHT;
            } else {
                v_game.inputError();
                v_game.printStartFight();
                v_game.printDivider();
            }
        } else if (state == CustomEnums.StateGame.FIGHT) {
            if (btn == CustomEnums.ButtonGame.ATK) {
                Fight();
            } else {
                v_game.inputError();
                v_game.printAttack();
                v_game.printDivider();
            }
        } else if (state == CustomEnums.StateGame.ARTIFACT) {
            if (btn == CustomEnums.ButtonGame.SET) {
                setArtifact();
                state = CustomEnums.StateGame.MOVE;
                v_game.printMapPlayer(player.mapPlayer);
                v_game.printDirections();
                v_game.printDivider();
            } else if (btn == CustomEnums.ButtonGame.IGNORE) {
                state = CustomEnums.StateGame.MOVE;
                v_game.printMapPlayer(player.mapPlayer);
                v_game.printDirections();
                v_game.printDivider();
            } else {
                v_game.inputError();
                v_game.printSetOrDestroyArtifact();
            }
        } else if (state == CustomEnums.StateGame.WIN) {
            v_game.printWin(player.getScore());
            Main.closeScanner();
            Main.disconnectDataBase();
        } else if (state == CustomEnums.StateGame.DEFEAT) {
            v_game.printDefeat(player.getScore());
            DataBase.deleteHero(player.getPlayerName());
            Main.closeScanner();
            Main.disconnectDataBase();
        }

        if (state != CustomEnums.StateGame.WIN && state != CustomEnums.StateGame.DEFEAT) {
            v_game.Loop();
        }
    }

    private void UpdateMap(Vector2 vec) throws CustomException {
        player.mapPlayer[player.pos.x][player.pos.y] = '*';
        player.pos = new Vector2(player.pos.x + vec.x, player.pos.y + vec.y);

        //win
        if (player.pos.x < 0 || player.pos.x >= player.mapPlayer.length ||
                player.pos.y < 0 || player.pos.y >= player.mapPlayer.length) {
            player.updateScore(player.getLevel() * 25);
            state = CustomEnums.StateGame.WIN;
            btnPress(CustomEnums.ButtonGame.NULL);
            return;
        }

        if (player.mapPlayer[player.pos.x][player.pos.y] == '#')
            player.updateScore(1);
        else
            player.updateScore(-1);

        player.mapPlayer[player.pos.x][player.pos.y] = 'P';
        //fight
        if (player.mapEnemy[player.pos.x][player.pos.y] != 0) {
            player.mapEnemy[player.pos.x][player.pos.y] = 0;
            state = CustomEnums.StateGame.PREFIGHT;
            enemy = new Enemy(player.getLevel());
            v_game.printFight(player, enemy);
            v_game.printStartFight();
            v_game.printDivider();
        } else {
            v_game.printMapPlayer(getMapPlayer());
            v_game.printDirections();
            v_game.printDivider();
        }
    }

    private void leaveFight() throws CustomException {
        int rnd = getRandom(player.getHeroClass().luck, 100);
        v_game.printChanceLeave(rnd > 50, rnd);
        if (rnd > 50) {
            v_game.printDivider();
            state = CustomEnums.StateGame.MOVE;
            v_game.printMapPlayer(getMapPlayer());
            v_game.printDirections();
            v_game.printDivider();
            return;
        }

        v_game.printFight(player, enemy);
        v_game.printDivider();

        btnPress(CustomEnums.ButtonGame.FIGHT);
    }

    private void Fight() throws CustomException {
        //roll
        int rndPlayer = getRandom(player.getHeroClass().luck, 100);
        int rndEnemy = getRandom(enemy.luck, 100);

        //atk               attack +- 10%
        int atkPlayer = getRandomCustom(player.getHeroClass().atk +
                player.getArtifactWeapon().getBonus()) - enemy.def;
        int atkEnemy = getRandomCustom(enemy.atk) - player.getHeroClass().def;

        if (atkPlayer <= 0)
            atkPlayer = player.getHeroClass().atk / 10;
        if (atkEnemy <= 0)
            atkEnemy = enemy.atk / 10;

        v_game.printFightFirstAttack(rndPlayer >= rndEnemy, rndPlayer, rndEnemy,
                rndPlayer >= rndEnemy ? atkPlayer : atkEnemy);

        if (rndPlayer >= rndEnemy && playerAtk(atkPlayer)) {
            return;
        } else if (rndPlayer < rndEnemy && enemyAtk(atkEnemy)) {
            return;
        }

        //roll
        int chancePlayer = getRandom(player.getHeroClass().luck, 100);
        int chanceEnemy = getRandom(enemy.luck, 100);

        v_game.printFightSecondAttack(rndPlayer >= rndEnemy, rndPlayer >= rndEnemy ? chanceEnemy : chancePlayer,
                rndPlayer >= rndEnemy ? atkEnemy : atkPlayer);

        if (rndPlayer >= rndEnemy && chanceEnemy >= 50 && enemyAtk(atkEnemy)) {
            return;
        } else if (rndPlayer < rndEnemy && chancePlayer >= 50 && playerAtk(atkPlayer)) {
            return;
        }

        v_game.printFight(player, enemy);
        v_game.printAttack();
        v_game.printDivider();
    }

    private boolean playerAtk(int atkPlayer) {
        CustomLogger.singleton.printLog("Player Attack: " + atkPlayer, 2);
        enemy.hp -= atkPlayer;
        if (enemy.hp < 0) {
            v_game.playerKillEnemy(250 + 450 * player.getLevel());
            if (player.gainExperience(450 * player.getLevel())) {
                v_game.printLevelUp(player);

                player.mapEnemy = generateEnemy(getSizeMap());
                player.mapPlayer = generatePlayer(getSizeMap());
            }

            player.updateScore(10);

            int rnd = getRandom(0, 2);
            CustomLogger.singleton.printLog("generate health banks: " + rnd, 2);
            if (rnd > 0) {
                player.updateHealthBanks(rnd);
                v_game.printDropHealthBanks(rnd);
            }

            int temp = getRandom(0, 100);
            if (temp < tempArtifact.getChanceDrop()) {
                CustomLogger.singleton.printLog("CREATE ARTIFACT: Random: " + temp, 2);
                tempArtifact = new Artifact(player.getLevel(), player.getHeroClass().luck);
                CustomLogger.singleton.printLog("ARTIFACT: " + tempArtifact.getArtName(), 2);
                v_game.printArtifact(player.getNowArtifact(tempArtifact.getType()), tempArtifact);
                v_game.printSetOrDestroyArtifact();

                state = CustomEnums.StateGame.ARTIFACT;
            }
            return true;
        }
        return false;
    }

    private void setArtifact() {
        CustomLogger.singleton.printLog("ARTIFACT: " + tempArtifact.getArtName(), 2);
        player.updateArtifact(tempArtifact);
        CustomLogger.singleton.printLog("ARTIFACT: " + player.getArtifactWeapon().getArtName() +
                "|" + player.getArtifactChest().getArtName() +
                "|" + player.getArtifactHead().getArtName(), 2);
    }

    private boolean enemyAtk(int atkEnemy) throws CustomException {
        CustomLogger.singleton.printLog("Enemy Attack: " + atkEnemy, 2);
        player.getHeroClass().hp -= atkEnemy;
        if (player.getHeroClass().hp <= 0) {
            state = CustomEnums.StateGame.DEFEAT;
            btnPress(CustomEnums.ButtonGame.NULL);
            return true;
        }
        return false;
    }

    private void useHealthBank() {
        int rnd = player.useHealthBank();
        v_game.printUseHealthBank(rnd, player.getCountHealthBanks());
    }

    public CustomEnums.StateGame getState() {
        return state;
    }

}
