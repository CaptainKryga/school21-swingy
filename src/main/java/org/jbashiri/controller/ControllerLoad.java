package org.jbashiri.controller;

import org.jbashiri.model.Player;
import org.jbashiri.utils.DataBase;
import org.jbashiri.view.load.UILoad;
import org.jbashiri.view.load.UILoadConsole;
import org.jbashiri.view.load.UILoadGUI;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerLoad {
    private ControllerGame game;
    private ControllerCreate create;
    private UILoad uiLoad;

    ControllerLoad(ControllerGame game, ControllerCreate create) {
        this.game = game;
        this.create = create;
    }

    public void init(boolean isConsole) {
        switchUI(isConsole);
    }

    public void switchUI(boolean isConsole) {
        if (uiLoad != null)
            uiLoad = null;
        uiLoad = getLoad(isConsole);
        ArrayList<String> list = DataBase.getAll();
        uiLoad.init(list);
        uiLoad.printDivider();

        if (isConsole) {
            Scanner sc = new Scanner(System.in);
            while(sc.hasNextLine()) {
                String line = sc.nextLine().toLowerCase();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).toLowerCase().split(" ")[0].equals(line)) {
                        game.init(new Player(list.get(i), list.get(i)), isConsole);
                        return;
                    }
                }
                if (line.equals("create")) {
                    create.init(isConsole);
                    break;
                } else {
                    uiLoad.inputError();
                    uiLoad.init(list);
                    uiLoad.printDivider();
                }
            }
            sc.close();
        }
    }

    private UILoad getLoad(boolean isConsole) {
        if (isConsole) {
            return new UILoadConsole();
        } else {
            return new UILoadGUI();
        }
    }
}
