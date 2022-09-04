package org.jbashiri.view.create;

import org.jbashiri.model.classes.*;

public class UICreateConsole implements UICreate {
    @Override
    public void init() {

    }

    @Override
    public void printName(String name) {
        System.out.println("HERO name: " + name);
    }

    @Override
    public void printClass(String name) {
        System.out.println("You have chosen a class: " + name);
    }

    @Override
    public void printConfirm() {
        System.out.println("Are you confident in your choice?");
    }

    @Override
    public void printState(int state) {
        if (state == 0) {
            System.out.println("CREATing a new character.");
            System.out.println("Write a name:");
        } else if (state == 1) {
            System.out.println("SELECTing a class character:");
            System.out.println("class     hp   att  def  agi  mgc  lck");
            System.out.println(new Warrior().getAllDefaultStats());
            System.out.println(new Mage().getAllDefaultStats());
            System.out.println(new Ranger().getAllDefaultStats());
            System.out.println(new Paladin().getAllDefaultStats());
        }
    }

    @Override
    public void inputError(int state) {
        System.out.println("INPUT ERROR");
        printState(state);
    }

    @Override
    public void printDivider() {
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }
}
