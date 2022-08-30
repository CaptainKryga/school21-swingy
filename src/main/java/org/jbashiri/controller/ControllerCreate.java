package org.jbashiri.controller;

import org.jbashiri.utils.CustomLogger;
import org.jbashiri.view.create.UICreate;
import org.jbashiri.view.create.UICreateConsole;
import org.jbashiri.view.create.UICreateGUI;

import java.util.Scanner;

public class ControllerCreate {
    private ControllerGame game;
    private UICreate uiCreate;

    //state 0=>name 1=>class
    private int state;

    ControllerCreate(ControllerGame game) {
        this.game = game;
        this.state = 0;
    }

    public void init(boolean isConsole) {
        switchUI(isConsole);
    }

    private void switchUI(boolean isConsole) {
        if (uiCreate != null)
            uiCreate = null;
        uiCreate = getCreate(isConsole);

        //name
        if (state == 0 && isConsole) {
            uiCreate.printState(state);
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine().toLowerCase();
            uiCreate.printName(line);

            sc.close();

            state = 1;
        }

        //class
        if (state == 1 && isConsole) {
            uiCreate.printState(state);
            Scanner sc = new Scanner(System.in);
            while(sc.hasNextLine()) {
                System.out.println("iter");
                String line = sc.nextLine().toLowerCase();

                if (line.equals("warrior") || line.equals("mage") || line.equals("ranger") || line.equals("paladin")) {
                    uiCreate.printClass(line);
                    break;
                } else {
                    uiCreate.inputError(state);
                }
                uiCreate.printName(line);

                CustomLogger.singleton.printLog("while", 3);
            }
            sc.close();

        }
    }

    private UICreate getCreate(boolean isConsole) {
        if (isConsole) {
            return new UICreateConsole();
        } else {
            return new UICreateGUI();
        }
    }

}
