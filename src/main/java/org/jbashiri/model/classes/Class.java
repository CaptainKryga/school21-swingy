package org.jbashiri.model.classes;

import static org.jbashiri.utils.CustomStrings.getLengthNumber;
import static org.jbashiri.utils.CustomStrings.getSpaces;
import static org.jbashiri.utils.CustomMath.Abs;

public class Class {
    protected String name;
    public int hp;
    public int attack;
    public int defence;
    public int luck;

    public String getAllDefaultStats() {
        String result = "";
        result += name + getSpaces(Abs(10 - name.length()));
        result += hp + getSpaces(Abs(5 - getLengthNumber(hp)));
        result += attack + getSpaces(Abs(5 - getLengthNumber(attack)));
        result += defence + getSpaces(Abs(5 - getLengthNumber(defence)));
        result += luck + getSpaces(Abs(5 - getLengthNumber(luck)));
        return result;
    }

    public void levelUp(int level) {
        hp = hp + hp * level / 10;
        attack = attack + attack * level / 10;
        defence = defence + defence * level / 10;
        luck = luck + luck * level / 10;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getLuck() {
        return luck;
    }
}
