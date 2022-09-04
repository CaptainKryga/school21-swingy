package org.jbashiri.controller;

import org.jbashiri.utils.CustomLogger;
import org.jbashiri.view.init.UIInit;
import org.jbashiri.view.init.UIInitConsole;
import org.jbashiri.view.init.UIInitGUI;

import java.util.Scanner;

public class ControllerInit {
    private ControllerGame game = null;
    private ControllerCreate create;
    private ControllerLoad load;
    private UIInit uiInit;

    public ControllerInit(boolean isConsole) {
        ControllerGame game = new ControllerGame();

        create = new ControllerCreate(game);
        load = new ControllerLoad(game);

        CustomLogger.singleton.printLog("GG", 3);

        switchUI(isConsole);
    }

    public void switchUI(boolean isConsole) {
        if (uiInit != null)
            uiInit = null;
        uiInit = getInit(isConsole);
        uiInit.init();
        uiInit.printDivider();

        if (isConsole) {
            Scanner sc = new Scanner(System.in);
            while(sc.hasNextLine()) {
                String line = sc.nextLine().toLowerCase();

                if (line.equals("create")) {
                    create.init(isConsole);
                    break;
                } else if (line.equals("load")) {
                    load.init(isConsole);
                    break;
                } else if (line.equals("switch")) {
                    switchUI(!uiInit.isConsole());
                    break;
                } else {
                    uiInit.inputError();
                }

                CustomLogger.singleton.printLog("while", 3);
            }
            sc.close();
        }
    }

    private UIInit getInit(boolean isConsole) {
        if (isConsole) {
            return new UIInitConsole();
        } else {
            return new UIInitGUI();
        }
    }
}
