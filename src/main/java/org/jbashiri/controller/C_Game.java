package org.jbashiri.controller;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.M_Game;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;

public class C_Game {
    private M_Game m_game;


    public C_Game() {
        m_game = new M_Game(this);

        CustomLogger.singleton.printLog("constructor c_game", 3);
    }

    //newPlayer
    public void init(String playerName, CustomEnums.HeroClass heroClass, boolean isConsole) throws CustomException {
        m_game.initPlayer(true, playerName, heroClass);
        CustomLogger.singleton.printLog("init c_game newPlayer", 3);
        switchUI(isConsole);
    }

    //oldPlayer
    public void init(String playerName, boolean isConsole) throws CustomException {
        m_game.initPlayer(false, playerName, null);
        CustomLogger.singleton.printLog("init c_game oldPlayer", 3);
        switchUI(isConsole);
    }

    public void switchUI(boolean isConsole) throws CustomException {
        m_game.reInit(isConsole);
    }

    public void btnPress(CustomEnums.ButtonGame btn) throws CustomException {
        m_game.btnPress(btn);
    }
    public void btnPress(String btn) throws CustomException {
        m_game.btnPress(CustomEnums.getEnumButtonGame(btn));
    }
}
