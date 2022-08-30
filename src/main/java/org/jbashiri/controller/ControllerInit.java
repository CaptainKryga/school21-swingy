package org.jbashiri.controller;

import org.jbashiri.view.init.UIInit;
import org.jbashiri.view.init.UIInitConsole;
import org.jbashiri.view.init.UIInitGUI;

import java.util.Scanner;

public class ControllerInit {
    private ControllerCreate create;
    private ControllerSelect select;
    private UIInit init;

    public ControllerInit() {
        create = new ControllerCreate();
        select = new ControllerSelect();
    }

    public void switchUI(boolean isConsole) {
        if (init != null)
            init.delete();

        init = getInit(isConsole);

        if (isConsole) {
            Scanner sc = new Scanner(System.in);
            while(true) {
                String line = sc.nextLine();
                System.out.println(line);

                if (line.equals("create")) {
                    break;
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
