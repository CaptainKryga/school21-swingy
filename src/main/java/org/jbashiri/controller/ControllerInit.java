package org.jbashiri.controller;

import org.jbashiri.view.init.UIInit;
import org.jbashiri.view.init.UIInitConsole;
import org.jbashiri.view.init.UIInitGUI;

import java.util.Scanner;

public class ControllerInit {
    private ControllerCreate create;
    private ControllerLoad load;
    private UIInit init;

    public ControllerInit() {
        create = new ControllerCreate();
        load = new ControllerLoad();
    }

    public void switchUI(boolean isConsole) {
        if (init != null)
            init.delete();

        init = getInit(isConsole);

        if (isConsole) {
            Scanner sc = new Scanner(System.in);
            while(true) {
                String line = sc.nextLine().toLowerCase();

                if (line.equals("create")) {
                    create.init();
                    break;
                } else if (line.equals("load")) {
                    load.init();
                    break;
                } else if (line.equals("switch")) {
                    switchUI(!init.isConsole());
                    break;
                } else {
                    init.inputError();
                }
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
