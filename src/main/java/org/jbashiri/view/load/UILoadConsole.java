package org.jbashiri.view.load;

import java.util.ArrayList;

public class UILoadConsole implements UILoad {
    @Override
    public void init(ArrayList<String> list) {
        System.out.println("CREATE => create new hero.");
        System.out.println("SWITCH => switch on GUI.");
        System.out.println("SELECTing a old hero: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + "] " + list.get(i));
        }
        System.out.println(list.size() > 0 ? "Write a old name hero or CREATE new hero." :
                "No save heroes. Please CREATE new hero.");
    }

    @Override
    public boolean isConsole() {
        return true;
    }

    @Override
    public void inputError() {
        System.out.println("INPUT ERROR");
    }

    @Override
    public void printDivider() {
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }
}
