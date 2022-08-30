package org.jbashiri.view.create;

public class UICreateGUI implements UICreate {
    @Override
    public void init() {
    }

    @Override
    public void printName(String name) {
        System.out.println("CREATE HERO NAME this must be gui...");
    }

    @Override
    public void printState(int state) {
        if (state == 0) {
            System.out.println("CREATE \"NAME\" this must be gui...");
        } else if (state == 1) {
            System.out.println("CREATE \"SELECT CLASS\" this must be gui...");
        }
    }
}
