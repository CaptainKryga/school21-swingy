package org.jbashiri.view.game;

import org.jbashiri.model.Enemy;
import org.jbashiri.model.Player;

import static org.jbashiri.utils.CustomMath.getRandom;

public class UIGameConsole implements UIGame {
    @Override
    public void printPlayerInfo(Player player) {
        System.out.println("--- I.N.F.O.R.M.A.T.I.O.N. ---");
        System.out.println("NAME: " + player.getName());
        System.out.println("SCORE: " + player.getScore());
        System.out.println("CLASS: " + player.getHeroClass().getName());
        System.out.println("LVL: " + player.getLevel());
        System.out.println("EXP: " + player.getExperience());
        System.out.println("HP: " + player.getHeroClass().getHp());
        System.out.println("ATK: " + player.getHeroClass().getAttack());
        System.out.println("DEF: " + player.getHeroClass().getDefence());
        System.out.println("LCK: " + player.getHeroClass().getLuck());
        System.out.println("WEAPON: " +
                (player.getArtifactWeapon() == null ? "no weapon" : player.getArtifactWeapon().getNameAndStats()));
        System.out.println("CHEST: " +
                (player.getArtifactChest() == null ? "no chest" : player.getArtifactChest().getNameAndStats()));
        System.out.println("HEAD: " +
                (player.getArtifactHead() == null ? "no head" : player.getArtifactHead().getNameAndStats()));
    }

    @Override
    public void printDirections() {
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
                if (map[x][y] == 0) {
                    System.out.print(' ');
                }
                System.out.print(map[x][y]);
                if (map[x][y] == -1) {
                    System.out.print(' ');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.print('\n');
        }
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

    }
}
