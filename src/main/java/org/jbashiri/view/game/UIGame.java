package org.jbashiri.view.game;

import org.jbashiri.model.Player;

public interface UIGame {
    public void printPlayerInfo(Player player);

    public void printDirections();

    public void inputError();

    public void printMapEnemy(int[][] map);
    public void printMapPlayer(char[][] map);

    public void printWin(int score);
    public void printGameOver();
    public void printDivider();
}
