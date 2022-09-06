package org.jbashiri.model.classes;

import org.jbashiri.model.CustomEnums;

import static org.jbashiri.utils.CustomStrings.getLengthNumber;
import static org.jbashiri.utils.CustomStrings.getSpaces;
import static org.jbashiri.utils.CustomMath.Abs;

public class HeroClass {
    protected CustomEnums.HeroClass className;
    public int hp;
    protected int maxHp;
    public int atk;
    protected int maxAtk;
    public int def;
    protected int maxDef;
    public int luck;
    protected int maxLuck; //max 75

    public HeroClass LoadClass(String className, int hp, int maxHp, int atk, int maxAtk, int def, int maxDef, int luck, int maxLuck) {
        this.className = getHeroClass(className);
        this.hp = hp;
        this.maxHp = maxHp;
        this.atk = atk;
        this.maxAtk = maxAtk;
        this.def = def;
        this.maxDef = maxDef;
        this.luck = luck;
        this.maxLuck = maxLuck;
        return this;
    }

    public String getAllDefaultStats() {
        String result = "";
        result += className + getSpaces(Abs(10 - className.toString().length()));
        result += hp + getSpaces(Abs(5 - getLengthNumber(hp)));
        result += atk + getSpaces(Abs(5 - getLengthNumber(atk)));
        result += def + getSpaces(Abs(5 - getLengthNumber(def)));
        result += luck + getSpaces(Abs(5 - getLengthNumber(luck)));
        return result;
    }

    public void levelUp(int level) {
        updateMaxHp(true, maxHp * level / 10);
        updateMaxAttack(maxAtk * level / 10);
        updateMaxDefence(maxDef * level / 10);
        updateMaxLuck(maxLuck * level / 10);
    }

    public CustomEnums.HeroClass getClassName() {
        return className;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxAtk() {
        return maxAtk;
    }

    public int getMaxDef() {
        return maxDef;
    }

    public int getMaxLuck() {
        return maxLuck;
    }

    public void updateHp(int add) {
        hp += add;
        if (hp > maxHp)
            hp = maxHp;
    }

    public void updateMaxHp(boolean isLevelUp, int add) {
        maxHp += add;
        hp += add;

        if (isLevelUp)
            hp = maxHp;
    }

    public void updateMaxDefence(int add) {
        maxDef += add;
        def = maxDef;
    }

    public void updateMaxAttack(int add) {
        maxAtk += add;
        atk = maxAtk;
    }

    public void updateMaxLuck(int add) {
        maxLuck += add;
        if (maxLuck >= 75) {
            maxLuck = 75;
        }
        luck = maxLuck;
    }

    public CustomEnums.HeroClass getHeroClass(String str) {
        if (str.equals("Warrior"))
            return CustomEnums.HeroClass.Warrior;
        else if (str.equals("Paladin"))
            return CustomEnums.HeroClass.Paladin;
        else if (str.equals("Ranger"))
            return CustomEnums.HeroClass.Ranger;
        else
            return CustomEnums.HeroClass.Mage;
    }
}
