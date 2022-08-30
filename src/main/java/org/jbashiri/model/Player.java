package org.jbashiri.model;

import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.model.classes.Class;

import static org.jbashiri.utils.CustomMath.getPow;

public class Player {
    protected String name;
    protected Class heroClass;
    protected int level;
    protected int experience;
    protected int score;
    protected Artifact weapon;
    protected Artifact chest;
    protected Artifact head;

    public void GainExperience(int exp) {
        experience += exp;
        if (experience >= calculateExperience()) {

        }
    }

    private int calculateExperience() {
        return level * 1000 + getPow(level, 2) * 450;
    }
}
