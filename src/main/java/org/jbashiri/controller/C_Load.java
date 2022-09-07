package org.jbashiri.controller;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.M_Load;
import org.jbashiri.utils.CustomLogger;

public class C_Load {
    private C_Game game;
    private C_Create create;
    private M_Load m_load;

    C_Load(C_Game game, C_Create create) {
        this.game = game;
        this.create = create;

        CustomLogger.singleton.printLog("constructor c_load", 3);
    }

    public void init(boolean isConsole) throws CustomException {
        m_load = new M_Load(this);
        switchUI(isConsole);

        CustomLogger.singleton.printLog("init c_load", 3);
    }

    public void switchUI(boolean isConsole) throws CustomException {
        m_load.reInit(isConsole);
    }

    public void initCreate(boolean isConsole) throws CustomException {
        create.init(isConsole);
    }

    public void initGame(String playerName, boolean isConsole) throws CustomException {
        game.init(playerName, isConsole);
    }
}
