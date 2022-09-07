package org.jbashiri.model;

import org.jbashiri.model.classes.*;
import org.jbashiri.model.classes.HeroClass;

import static org.jbashiri.utils.CustomMath.getRandom;

public class Enemy {
    public int hp;
    public int atk;
    public int def;
    public int luck;

    private int koof = 2;

    public Enemy(int level) {
        HeroClass[] data = new HeroClass[4];
        data[0] = new Warrior();
        data[1] = new Mage();
        data[2] = new Ranger();
        data[3] = new Paladin();

        int rnd = getRandom(0, data.length);

        hp = data[rnd].hp / koof + data[rnd].hp * level / 10;
        atk = data[rnd].atk / koof + data[rnd].atk * level / 10;
        def = data[rnd].def / koof + data[rnd].def * level / 10;
        luck = data[rnd].luck + data[rnd].luck * level / 10;
    }
}
