package org.jbashiri.controller;

import org.jbashiri.model.Player;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.DataBase;
import org.jbashiri.view.create.UICreate;
import org.jbashiri.view.create.UICreateConsole;
import org.jbashiri.view.create.UICreateGUI;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerCreate {
    private ControllerGame game;
    private UICreate uiCreate;

    //state 0=>name 1=>class
    private int state;
    private String playerName;
    private String playerClass;

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
        Scanner sc = new Scanner(System.in);

        //name
        if (state == 0 && isConsole) {
            ArrayList<String> list = DataBase.getAll();
            boolean isCorrectName = false;

            uiCreate.printState(state);
            uiCreate.printDivider();

            while(sc.hasNextLine()) {

                String line = sc.nextLine().toLowerCase();

                isCorrectName = true;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).toLowerCase().split(" ")[0].equals(line)) {
                        isCorrectName = false;
                        break;
                    }
                }

                if (isCorrectName) {
                    uiCreate.printName(line);
                    playerName = line;
                    state = 1;
                    break;
                }
                else {
                    uiCreate.inputErrorName(line);
                    uiCreate.printState(state);
                    uiCreate.printDivider();
                }
            }
        }

        //class
        if (state == 1 && isConsole) {
            uiCreate.printState(state);
            uiCreate.printDivider();
            while(sc.hasNextLine()) {
                String line = sc.nextLine().toLowerCase();

                if (line.equals("warrior") || line.equals("mage") || line.equals("ranger") || line.equals("paladin")) {
                    uiCreate.printClass(line);
                    playerClass = line;
                    break;
                } else {
                    uiCreate.inputError(state);
                }
            }

            state = 2;
        }
        if (state == 2) {
            game.init(playerName, playerClass, isConsole);
            uiCreate.printDivider();
        }
        sc.close();
    }

    private UICreate getCreate(boolean isConsole) {
        if (isConsole) {
            return new UICreateConsole();
        } else {
            return new UICreateGUI();
        }
    }

}
