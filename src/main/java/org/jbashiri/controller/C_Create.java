package org.jbashiri.controller;

import org.jbashiri.model.M_Create;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.view.create.V_Create;
import sun.print.CustomMediaTray;

import javax.validation.constraints.Size;

public class C_Create {
    private C_Game game;
    private M_Create m_create;
    private V_Create v_create;

    private CustomEnums.StateCreate state;

    C_Create(C_Game game) {
        this.game = game;
        this.state = CustomEnums.StateCreate.Name;

        CustomLogger.singleton.printLog("constructor c_create", 3);
    }

    public void init(boolean isConsole) {
        m_create = new M_Create(this);
        switchUI(isConsole);

        CustomLogger.singleton.printLog("init c_create", 3);
    }

    private void switchUI(boolean isConsole) {
        m_create.reInit(state, isConsole);
    }

    public void initGame(String playerName, CustomEnums.HeroClass playerClass, boolean isConsole) {
        game.init(playerName, playerClass, isConsole);
    }
}
