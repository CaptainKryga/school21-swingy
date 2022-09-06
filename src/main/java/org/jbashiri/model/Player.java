package org.jbashiri.model;

import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.model.classes.*;
import org.jbashiri.model.classes.Class;
import org.jbashiri.utils.CustomLogger;

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

    public Player PlayerLoad(String data) {
        level = 1;
        this.name = data;
        heroClass = setClass(data);
        weapon = new Artifact();
        chest = new Artifact();
        head = new Artifact();
        healthBank = 1;
        return this;
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
        CustomLogger.singleton.printLog("exp: " + experience + "|max: " + calculateExperience(level), 2);
        if (experience >= calculateExperience(level)) {
            experience -= calculateExperience(level);
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
        return chest;
    }
    public Artifact getArtifactHead() {
        return head;
    }
    public void updateArtifact(Artifact _new) {
        if (_new == null)
            return;

        if (_new.getType().equals("Weapon")) {
            heroClass.updateMaxAttack(-weapon.getBonus());
            weapon = _new;
            heroClass.updateMaxAttack(weapon.getBonus());
        }
        else if (_new.getType().equals("Chest")) {
            heroClass.updateMaxDefence(-chest.getBonus());
            chest = _new;
            heroClass.updateMaxDefence(chest.getBonus());
        }
        else if (_new.getType().equals("Head")) {
            heroClass.updateMaxHp(false, -head.getBonus());
            head = _new;
            heroClass.updateMaxHp(false, head.getBonus());
        }
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

    public int getCountHealthBanks() {
        return healthBank;
    }

    public void updateHealthBanks(int add) {
        healthBank += add;
        if (healthBank > 3) {
            healthBank = 3;
        }
    }

    public void updateScore(int add) {
        score += add;
        if (score < 0) {
            score = 0;
        }
    }
}
