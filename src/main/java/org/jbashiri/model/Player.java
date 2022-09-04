package org.jbashiri.model;

import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.model.classes.*;
import org.jbashiri.model.classes.Class;

import static org.jbashiri.utils.CustomMath.getPow;
import static org.jbashiri.utils.CustomMath.getRandom;

public class Player {
    protected String name;
    protected Class heroClass;
    protected int level;
    protected int experience;
    protected int score;
    protected Artifact weapon;
    protected Artifact chest;
    protected Artifact head;
    protected int healthBank;

    public Player(String name, String clas) {
        level = 1;
        this.name = name;
        heroClass = setClass(clas);
        weapon = new Artifact();
        chest = new Artifact();
        head = new Artifact();
        healthBank = 1;
    }

    private Class setClass(String type) {
        if (type.equals("warrior"))
            return new Warrior();
        else if (type.equals("mage"))
            return new Mage();
        else if (type.equals("ranger"))
            return new Ranger();
        else if (type.equals("paladin"))
            return new Paladin();
        return null;
    }

    //если получаем уровень возвращаем тру и алармим о получении нового уровня
    public boolean gainExperience(int exp) {
        experience += exp;
        if (experience >= calculateExperience(level + 1)) {
            experience -= calculateExperience(level + 1);
            level++;
            heroClass.levelUp(level);
            return true;
        }
        return false;
    }

    public int calculateExperience(int level) {
        return level * 1000 + getPow(level, 2) * 450;
    }

    public String getName() {
        return name;
    }
    public Class getHeroClass() {
        return heroClass;
    }
    public int getLevel() {
        return level;
    }
    public int getExperience() {
        return experience;
    }
    public int getScore() {
        return score;
    }
    public Artifact getArtifactWeapon() {
        return weapon;
    }
    public Artifact getArtifactChest() {
        return weapon;
    }
    public Artifact getArtifactHead() {
        return weapon;
    }
    public void updateArtifact(Artifact artifact) {
        if (artifact == null)
            return;

        if (artifact.getType().equals("Weapon"))
            weapon = artifact;
        else if (artifact.getType().equals("Chest"))
            chest = artifact;
        else if (artifact.getType().equals("Head"))
            head = artifact;
    }
    public Artifact getNowArtifact(String type) {
        if (type.equals("Weapon"))
            return weapon;
        else if (type.equals("Chest"))
            return chest;
        else if (type.equals("Head"))
            return head;
        return null;
    }

    public int useHealthBank() {
        if (healthBank > 0) {
            healthBank--;
            int rnd = getRandom(1, heroClass.getMaxHp());
            heroClass.updateHp(rnd);
            return rnd;
        }
        return -1;
    }
}
