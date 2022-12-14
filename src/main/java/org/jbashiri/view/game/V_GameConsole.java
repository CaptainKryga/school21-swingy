package org.jbashiri.view.game;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Game;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.M_Game;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.model.Enemy;
import org.jbashiri.model.Player;
import org.jbashiri.model.artifats.Artifact;

import java.util.Scanner;

import static org.jbashiri.utils.CustomEnums.getEnumButtonGame;
import static org.jbashiri.utils.CustomStrings.*;

public class V_GameConsole implements V_Game {
    private C_Game c_game;
    private M_Game m_game;

    private Scanner sc;

    public V_GameConsole(C_Game c_game, M_Game m_game) {
        this.c_game = c_game;
        this.m_game = m_game;
    }

    @Override
    public void switchUI(CustomEnums.StateGame state, boolean isConsole) throws CustomException {
        if (!isConsole)
            return;

        printPlayerInfo(m_game.getPlayer());
        printDivider();

        if (sc == null) {
            sc = Main.getScanner();
            init(state);
        }
    }

    private void init(CustomEnums.StateGame state) throws CustomException {
//        printMapEnemy(m_game.getMapEnemy());
        printMapPlayer(m_game.getMapPlayer());
        printDirections();
        printDivider();

        Loop();
    }

    public void Loop() throws CustomException {
        while (sc.hasNext()) {
            String line = sc.nextLine().toLowerCase();
            if (m_game.isConsole() == this) {
                c_game.btnPress(getEnumButtonGame(line));
                break;
            }
        }
    }


    @Override
    public void printPlayerInfo(Player player) {
        System.out.println("--- I.N.F.O.R.M.A.T.I.O.N. ---");
        System.out.println("NAME: " + player.getPlayerName());
        System.out.println("SCORE: " + player.getScore());
        System.out.println("CLASS: " + player.getHeroClass().getClassName());
        System.out.println("LVL: " + player.getLevel());
        System.out.println("EXP: " + player.getExperience() + "/" + player.calculateExperience(player.getLevel()));
        System.out.println("HP: " + player.getHeroClass().hp + "/" + player.getHeroClass().getMaxHp());
        System.out.println("ATK: " + player.getHeroClass().atk);
        System.out.println("DEF: " + player.getHeroClass().def);
        System.out.println("LCK: " + player.getHeroClass().luck);
        System.out.println("WEAPON: " +
                (player.getArtifactWeapon().getArtName().equals("-1") ? "no weapon" :
                        player.getArtifactWeapon().getNameAndStats()));
        System.out.println("CHEST: " +
                (player.getArtifactChest().getArtName().equals("-1") ? "no chest" :
                        player.getArtifactChest().getNameAndStats()));
        System.out.println("HEAD: " +
                (player.getArtifactHead().getArtName().equals("-1") ? "no head" :
                        player.getArtifactHead().getNameAndStats()));
        System.out.println("Health banks: " + player.getCountHealthBanks());
    }

    @Override
    public void printDirections() {
        System.out.println("SWITCH => change to GUI");
        System.out.println("HEALTH => restore hp if have health banks.");
        System.out.println("INFO => get player info.");
        System.out.println("Directions:      NORTH");
        System.out.println("          WEST     X     EAST");
        System.out.println("                 SOUTH");
    }

    @Override
    public void inputError() {
        System.out.println("INPUT ERROR");
    }

    @Override
    public void printMapEnemy(int[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                System.out.print(' ');
                System.out.print(map[x][y]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    @Override
    public void printMapPlayer(char[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                System.out.print(' ');
                System.out.print(map[x][y]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
    }

    @Override
    public void printWin(int score) {
        System.out.println("You made it to the edge of the map.");
        System.out.println("CONGRATULATION, it's a WIN!!!");
        System.out.println("YOU SCORE: " + score);
    }

    @Override
    public void printDefeat(int score) {
        System.out.println("YOU ARE DEAD.");
        System.out.println("SCORE: " + score + ".");
        System.out.println("Your HERO has been DELETED.");
    }

    @Override
    public void printDivider() {
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }

    @Override
    public void printFight(Player player, Enemy enemy) {
        System.out.println("You stumbled upon the enemy!!!");
        System.out.println("####   HERO      ENEMY");
        System.out.println("HP     " + player.getHeroClass().hp +
                getSpaces(10 - getLengthNumber(player.getHeroClass().hp)) + "" + enemy.hp);
        System.out.println("ATK    " + player.getHeroClass().atk +
                getSpaces(10 - getLengthNumber(player.getHeroClass().atk)) + "" + enemy.atk);
        System.out.println("DEF    " + player.getHeroClass().def +
                getSpaces(10 - getLengthNumber(player.getHeroClass().def)) + "" + enemy.def);
    }

    @Override
    public void printStartFight() {
        System.out.println("write FIGHT => start battle");
        System.out.println("write LEAVE => chance 50% on leave battle");
    }

    @Override
    public void printAttack() {
        System.out.println("write ATK => attack enemy");
    }

    @Override
    public void printFightFirstAttack(boolean isWinRollPlayer, int rollPlayer, int rollEnemy, int atk) {
        System.out.println("Player roll: " + rollPlayer + "/100");
        System.out.println("Enemy  roll: " + rollEnemy + "/100");
        System.out.println((isWinRollPlayer ? "Player " : "Enemy ") + "atk: " + atk);
    }

    @Override
    public void printFightSecondAttack(boolean isWinRollPlayer, int chance, int atk) {
        System.out.println((!isWinRollPlayer ? "Player " : "Enemy ") + "has a chance for a counter attack.");
        System.out.println((!isWinRollPlayer ? "Player " : "Enemy ") + "roll: " + chance + "/" + 50);
        System.out.println((!isWinRollPlayer ? "Player " : "Enemy ") + (chance >= 50 ? "atk: " + atk : "missed."));
    }

    @Override
    public void printChanceLeave(boolean isLeave, int rnd) {
        System.out.println(rnd + "/50 " +  (isLeave ? "You leave fight." : "You don't leave fight."));
    }

    @Override
    public void printUseHealthBank(int rnd, int countHealthBanks) {
        System.out.println(rnd > 0 ? "you restore health: " + rnd + "pt | health banks left: " + countHealthBanks : "not enough health banks");
    }

    @Override
    public void printSwitch(boolean isConsole) {

    }

    @Override
    public void printArtifact(Artifact old, Artifact _new) {
        System.out.println("Drop new Artifact");
        System.out.println("####      OLD         NEW");
        System.out.println("name      " + (old.getArtName().equals("-1") ? "-" + getSpaces(11) :
                        old.getArtName() + getSpaces(12 - old.getArtName().length())) + "" + _new.getArtName());

        System.out.println("bonus HP  " + GetArtifactBonus(old, CustomEnums.TypeArt.Head) +
                "" + GetArtifactBonus(_new, CustomEnums.TypeArt.Head));
        System.out.println("bonus ATK " + GetArtifactBonus(old, CustomEnums.TypeArt.Weapon) +
                "" + GetArtifactBonus(_new, CustomEnums.TypeArt.Weapon));
        System.out.println("bonus DEF " + GetArtifactBonus(old, CustomEnums.TypeArt.Chest) +
                "" + GetArtifactBonus(_new, CustomEnums.TypeArt.Chest));
    }

    private String GetArtifactBonus(Artifact art, CustomEnums.TypeArt type) {
        if (art.getType() != type)
            return "-" + getSpaces(11);
        return art.getBonus() + getSpaces(12 - getLengthNumber(art.getBonus()));
    }

    @Override
    public void printSetOrDestroyArtifact() {
        System.out.println("SET => Use new artifact.");
        System.out.println("IGNORE => Destroy new artifact.");

        printDivider();
    }

    @Override
    public void playerKillEnemy(int exp) {
        System.out.println("Player KILL enemy. GAIN " + exp + " experience.");
    }

    @Override
    public void printDropHealthBanks(int banks) {
        System.out.println("DROP health banks: " + banks);
    }

    @Override
    public void printLevelUp(Player player) {
        System.out.println("LEVEL UP!!!!!!!!!!!!");
        System.out.println("NOW YOUR LEVEL: " + player.getLevel());
    }
}
