package org.jbashiri.view.game;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.Enemy;
import org.jbashiri.model.Player;
import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.utils.CustomEnums;

public interface V_Game {
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
    public void printChanceLeave(boolean isLeave, int rnd);
    public void printUseHealthBank(int rnd, int countHealthBanks);
    public void printSwitch(boolean isConsole);
    public void printArtifact(Artifact nowArtifact, Artifact artifact);
    public void printSetOrDestroyArtifact();
    public void playerKillEnemy(int exp);
    public void printDropHealthBanks(int banks);

    public void printLevelUp(Player player);

    void switchUI(CustomEnums.StateGame state, boolean isConsole) throws CustomException;

}
