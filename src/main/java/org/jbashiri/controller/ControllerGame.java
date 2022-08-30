package org.jbashiri.controller;

import org.jbashiri.model.Player;
import org.jbashiri.view.game.UIGame;
import org.jbashiri.view.game.UIGameConsole;
import org.jbashiri.view.game.UIGameGUI;

import java.util.Scanner;

public class ControllerGame {
    private UIGame uiGame;
    private Player player;

    private boolean isLoop;

    public void init(String name, String clas, boolean isConsole) {
        player = new Player(name, clas);
        switchUI(isConsole);
    }

    public void switchUI(boolean isConsole) {
        if (uiGame != null)
            uiGame = null;
        uiGame = getGame(isConsole);
        uiGame.printPlayerInfo(player);

        if (!isLoop) {
            Loop();
            isLoop = true;
        }
    }

    private void Loop() {
        Scanner sc = new Scanner(System.in);
        uiGame.printDirections();

        //name
        while (sc.hasNextLine()) {
            String line = sc.nextLine().toLowerCase();

            if (line.equals("north")) {

            }
            else if (line.equals("south")) {

            }
            else if (line.equals("west")) {

            }
            else if (line.equals("east")) {

            }
            else {
                uiGame.inputError();
                uiGame.printDirections();
            }

        }
        sc.close();
    }

    private UIGame getGame(boolean isConsole) {
        if (isConsole) {
            return new UIGameConsole();
        } else {
            return new UIGameGUI();
        }
    }
}
