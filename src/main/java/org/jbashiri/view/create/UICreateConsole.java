package org.jbashiri.view.create;

public class UICreateConsole implements UICreate {
    @Override
    public void init() {

    }

    @Override
    public void printName(String name) {
        System.out.println("HERO name: " + name);
    }

    @Override
    public void printState(int state) {
        if (state == 0) {
            System.out.println("CREATing a new character.");
            System.out.println("Write a name:");
        } else if (state == 1) {
            System.out.println("SELECTing a class character:");
            System.out.println("");
        }

    }
}
