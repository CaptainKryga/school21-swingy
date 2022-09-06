package org.jbashiri.controller;

import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.DataBase;
import org.jbashiri.view.load.V_Load;
import org.jbashiri.view.load.V_LoadConsole;
import org.jbashiri.view.load.V_LoadGUI;

import java.util.ArrayList;
import java.util.Scanner;

public class C_Load {
    private C_Game game;
    private C_Create create;
    private V_Load uiLoad;

    C_Load(C_Game game, C_Create create) {
        this.game = game;
        this.create = create;

        CustomLogger.singleton.printLog("constructor c_load", 3);
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
                        game.init(DataBase.getHero(list.get(i).split(" ")[0]), isConsole);
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

    private V_Load getLoad(boolean isConsole) {
        if (isConsole) {
            return new V_LoadConsole();
        } else {
            return new V_LoadGUI();
        }
    }
}
