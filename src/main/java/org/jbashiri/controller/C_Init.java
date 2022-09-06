package org.jbashiri.controller;

import org.jbashiri.model.M_Init;
import org.jbashiri.utils.CustomLogger;

public class C_Init {
    private ControllerCreate create;
    private ControllerLoad load;
    private M_Init m_init;

    public C_Init(boolean isConsole) {
        m_init = new M_Init(this);
        create
        switchUI(isConsole);

        CustomLogger.singleton.printLog("constructor c_init", 3);
    }

    public void initCreate() {

    }

    public void initLoad() {

    }

    public void switchUI(boolean isConsole) {
        m_init.reInit(isConsole);
    }
}
