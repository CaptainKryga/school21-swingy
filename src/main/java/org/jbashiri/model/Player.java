package org.jbashiri.model;

import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.model.classes.*;
import org.jbashiri.model.classes.HeroClass;
import org.jbashiri.utils.CustomLogger;

import static org.jbashiri.utils.CustomMath.getPow;
import static org.jbashiri.utils.CustomMath.getRandom;

public class Player {
    protected String playerName;
    protected HeroClass heroClass;
    protected int level;
    protected int experience;
    protected int score;
    protected Artifact weapon;
    protected Artifact chest;
    protected Artifact head;
    protected int countHealthBanks;

    public Player(String name, String clas) {
        level = 1;
        this.playerName = name;
        heroClass = setClass(clas);
        weapon = new Artifact();
        chest = new Artifact();
        head = new Artifact();
        countHealthBanks = 1;
    }

    public Player(String playerName, int playerLevel, int experience, int score, int countHealthBanks,
                           String className, int hp, int maxHp, int atk, int maxAtk, int def, int maxDef, int luck, int maxLuck,
                           String weaponName, int weaponBonus, String chestName, int chestBonus, String headName, int headBonus) {
        this.playerName = playerName;
        this.level = playerLevel;
        this.experience = experience;
        this.score = score;
        this.countHealthBanks = countHealthBanks;
        this.heroClass = new HeroClass().LoadClass(className, hp, maxHp, atk, maxAtk, def, maxDef, luck, maxLuck);
        this.weapon = new Artifact(weaponName, "Weapon", weaponBonus);
        this.chest = new Artifact(chestName, "Chest", chestBonus);
        this.head = new Artifact(headName, "Head", headBonus);
    }

    private HeroClass setClass(String type) {
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

    public String getPlayerName() {
        return playerName;
    }
    public HeroClass getHeroClass() {
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

        if (_new.getType() == CustomEnums.TypeArt.Weapon) {
            heroClass.updateMaxAttack(-weapon.getBonus());
            weapon = _new;
            heroClass.updateMaxAttack(weapon.getBonus());
        }
        else if (_new.getType() == CustomEnums.TypeArt.Chest) {
            heroClass.updateMaxDefence(-chest.getBonus());
            chest = _new;
            heroClass.updateMaxDefence(chest.getBonus());
        }
        else if (_new.getType() == CustomEnums.TypeArt.Head) {
            heroClass.updateMaxHp(false, -head.getBonus());
            head = _new;
            heroClass.updateMaxHp(false, head.getBonus());
        }
    }
    public Artifact getNowArtifact(CustomEnums.TypeArt type) {
        if (type == CustomEnums.TypeArt.Weapon)
            return weapon;
        else if (type == CustomEnums.TypeArt.Chest)
            return chest;
        else if (type == CustomEnums.TypeArt.Head)
            return head;
        return null;
    }

    public int useHealthBank() {
        if (countHealthBanks > 0) {
            countHealthBanks--;
            int rnd = getRandom(1, heroClass.getMaxHp());
            heroClass.updateHp(rnd);
            return rnd;
        }
        return -1;
    }

    public int getCountHealthBanks() {
        return countHealthBanks;
    }

    public void updateHealthBanks(int add) {
        countHealthBanks += add;
        if (countHealthBanks > 3) {
            countHealthBanks = 3;
        }
    }

    public void updateScore(int add) {
        score += add;
        if (score < 0) {
            score = 0;
        }
    }
}
