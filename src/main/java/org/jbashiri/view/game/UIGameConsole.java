package org.jbashiri.view.game;

import org.jbashiri.model.Player;

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
}
