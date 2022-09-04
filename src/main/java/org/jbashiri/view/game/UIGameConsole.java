package org.jbashiri.view.game;

import org.jbashiri.model.Enemy;
import org.jbashiri.model.Player;
import org.jbashiri.model.artifats.Artifact;

public class UIGameConsole implements UIGame {
    @Override
    public void printPlayerInfo(Player player) {
        System.out.println("--- I.N.F.O.R.M.A.T.I.O.N. ---");
        System.out.println("NAME: " + player.getName());
        System.out.println("SCORE: " + player.getScore());
        System.out.println("CLASS: " + player.getHeroClass().getName());
        System.out.println("LVL: " + player.getLevel());
        System.out.println("EXP: " + player.getExperience() + "/" + player.calculateExperience(player.getLevel()));
        System.out.println("HP: " + player.getHeroClass().hp + "/" + player.getHeroClass().getMaxHp());
        System.out.println("ATK: " + player.getHeroClass().atk);
        System.out.println("DEF: " + player.getHeroClass().def);
        System.out.println("LCK: " + player.getHeroClass().luck);
        System.out.println("WEAPON: " +
                (player.getArtifactWeapon().getType().equals("-1") ? "no weapon" : player.getArtifactWeapon().getNameAndStats()));
        System.out.println("CHEST: " +
                (player.getArtifactChest().getType().equals("-1") ? "no chest" : player.getArtifactChest().getNameAndStats()));
        System.out.println("HEAD: " +
                (player.getArtifactHead().getType().equals("-1") ? "no head" : player.getArtifactHead().getNameAndStats()));
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
        System.out.println("Your HERO has been DELETED.");
    }

    @Override
    public void printDefeat(int score) {
        System.out.println("YOU ARE DEAD.");
        System.out.println("Your HERO has been DELETED.");
    }

    @Override
    public void printDivider() {
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }

    @Override
    public void printFight(Player player, Enemy enemy) {
        System.out.println("You stumbled upon the enemy!!!");
        System.out.println("####   HERO   ENEMY");
        System.out.println("HP     " + player.getHeroClass().hp + "     " + enemy.hp);
        System.out.println("ATK    " + player.getHeroClass().atk + "     " + enemy.attack);
        System.out.println("DEF    " + player.getHeroClass().def + "     " + enemy.defence);
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
    public void printUseHealthBank(int rnd) {
        System.out.println(rnd > 0 ? "you restore health: " + rnd + "pt" : "not enough health banks");
    }

    @Override
    public void printSwitch(boolean isConsole) {

    }

    @Override
    public void printArtifact(Artifact old, Artifact _new) {
        System.out.println("Drop new Artifact");
        System.out.println("####        OLD     NEW");
        System.out.println("name        " + (old.getType().equals("-1") ? "-" : old.getName()) +
                " " + _new.getName());
        System.out.println("bonus HP    " + (old.getType().equals("-1") ? "-" : old.getBonusHp()) +
                " " + _new.getBonusHp());
        System.out.println("bonus ATK   " + (old.getType().equals("-1") ? "-" : old.getBonusAttack()) +
                " " + _new.getBonusAttack());
        System.out.println("bonus DEF   " + (old.getType().equals("-1") ? "-" : old.getBonusDefence()) +
                " " + _new.getBonusDefence());
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
}
