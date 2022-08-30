package org.jbashiri.controller;

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
        uiCreate.printState(state);

        if (state == 0 && isConsole) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine().toLowerCase();
            uiCreate.printName(line);

            sc.close();
        } else if (state == 1 && isConsole) {

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
