package org.jbashiri.controller;

import org.jbashiri.model.Player;
import org.jbashiri.view.game.UIGame;
import org.jbashiri.view.game.UIGameConsole;
import org.jbashiri.view.game.UIGameGUI;

import java.util.Scanner;

import static org.jbashiri.utils.CustomMath.getRandom;

public class ControllerGame {
    private UIGame uiGame;
    private Player player;

    private boolean isLoop;
    private int[][] map;

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

            map = generateEnemy(createMap(getSizeMap()));

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

            uiGame.printMap(map);

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

    private int getSizeMap() {
        return (player.getLevel() - 1) * 5 + 10;
    }

    private int[][] createMap(int size) {
        int[][] map = new int[size][size];
        return map;
    }

    private int[][] generateEnemy(int[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (getRandom(0, 100) > 65) {
                    map[x][y] = player.getLevel();
                }
                else {
                    map[x][y] = -1;
                }
            }
        }
        return map;
    }
}
