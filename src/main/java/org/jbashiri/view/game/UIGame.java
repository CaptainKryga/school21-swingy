package org.jbashiri.view.game;

import org.jbashiri.model.Player;

public interface UIGame {
    public void printPlayerInfo(Player player);

    public void printDirections();

    public void inputError();

    public void printMap(int[][] map);
}
