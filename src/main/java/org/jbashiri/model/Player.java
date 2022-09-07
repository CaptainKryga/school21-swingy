package org.jbashiri.model;

import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.model.classes.*;
import org.jbashiri.model.classes.HeroClass;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.Vector2;

import javax.validation.constraints.Size;

import static org.jbashiri.utils.CustomMath.getPow;
import static org.jbashiri.utils.CustomMath.getRandom;

public class Player {
    @Size(min = 3, max = 15, message = "name can only be between 3 - 15 characters.")
    protected String playerName;
    protected HeroClass heroClass;
    protected int level;
    protected int experience;
    protected int score;
    protected Artifact weapon;
    protected Artifact chest;
    protected Artifact head;
    protected int countHealthBanks;
    protected Vector2 pos;
    public char[][] mapEnemy;
    public char[][] mapPlayer;

    public Player(String playerName, CustomEnums.HeroClass className) {
        level = 1;
        this.playerName = playerName;
        CustomLogger.singleton.printLog("class > " + className, 3);
        this.heroClass = new HeroClass();
        this.heroClass = setClass(className);
        weapon = new Artifact();
        chest = new Artifact();
        head = new Artifact();
        countHealthBanks = 1;
        pos = new Vector2(0,0);
    }

    public Player(String playerName, int playerLevel, int experience, int score, int countHealthBanks,
                  String className, int hp, int maxHp, int atk, int maxAtk, int def, int maxDef, int luck, int maxLuck,
                  String weaponName, int weaponBonus, String chestName, int chestBonus, String headName, int headBonus,
                  int posX, int posY, String playerMap, String enemyMap) {
        this.playerName = playerName;
        this.level = playerLevel;
        this.experience = experience;
        this.score = score;
        this.countHealthBanks = countHealthBanks;
        this.heroClass = new HeroClass().LoadClass(className, hp, maxHp, atk, maxAtk, def, maxDef, luck, maxLuck);
        this.weapon = new Artifact(weaponName, "Weapon", weaponBonus);
        this.chest = new Artifact(chestName, "Chest", chestBonus);
        this.head = new Artifact(headName, "Head", headBonus);
        this.pos = new Vector2(posX, posY);
        this.mapPlayer = decode(playerMap);
        this.mapEnemy = decode(enemyMap);
    }

    private HeroClass setClass(CustomEnums.HeroClass type) {
        if (type == CustomEnums.HeroClass.Warrior)
            return new Warrior();
        else if (type == CustomEnums.HeroClass.Mage)
            return new Mage();
        else if (type == CustomEnums.HeroClass.Ranger)
            return new Ranger();
        else if (type == CustomEnums.HeroClass.Paladin)
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

    public String encode(char[][] map) {
        StringBuilder res = new StringBuilder("");
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                res.append(map[x][y]);
            }

            if (x + 1 < map.length)
                res.append('>');
        }
        return res.toString();
    }

    public char[][] decode(String map) {
        String[] array = map.split(">");
        char[][] res = new char[array[0].length()][array[0].length()];
        for (int x = 0; x < res.length; x++) {
            for (int y = 0; y < res.length; y++) {
                res[x][y] = array[x].toCharArray()[y];
            }
        }
        return res;
    }

    public Vector2 getPos() {
        return pos;
    }
}
