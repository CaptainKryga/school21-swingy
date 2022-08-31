package org.jbashiri.model;

import org.jbashiri.model.classes.*;
import org.jbashiri.model.classes.Class;

import static org.jbashiri.utils.CustomMath.getRandom;

public class Enemy {
    public int hp;
    public int attack;
    public int defence;
    public int luck;

    public Enemy(int level) {
        Class[] data = new Class[4];
        data[0] = new Warrior();
        data[1] = new Mage();
        data[2] = new Ranger();
        data[3] = new Paladin();

        int rnd = getRandom(0, data.length);

        hp = data[rnd].getHp() / 2 + data[rnd].getHp() * level / 10;
        attack = data[rnd].getAttack() / 2 + data[rnd].getAttack() * level / 10;
        defence = data[rnd].getDefence() / 2 + data[rnd].getDefence() * level / 10;
        luck = data[rnd].getLuck() + data[rnd].getLuck() * level / 10;
    }
}
