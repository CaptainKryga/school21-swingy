package org.jbashiri.view.game;

import org.jbashiri.model.Enemy;
import org.jbashiri.model.Player;

public interface UIGame {
    public void printPlayerInfo(Player player);

    public void printDirections();

    public void inputError();

    public void printMapEnemy(int[][] map);
    public void printMapPlayer(char[][] map);

    public void printWin(int score);
    public void printDefeat(int score);
    public void printDivider();
    public void printFight(Player player, Enemy enemy);
    public void printStartFight();
    public void printAttack();
    public void printFightFirstAttack(boolean isWinRollPlayer, int rollPlayer, int rollEnemy, int atk);
    public void printFightSecondAttack(boolean isWinRollPlayer, int chance, int atk);
}
