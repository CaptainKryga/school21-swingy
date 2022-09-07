package org.jbashiri.model;

import org.jbashiri.controller.C_Init;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.view.init.V_InitConsole;
import org.jbashiri.view.init.V_InitGUI;
import org.jbashiri.view.init.V_Init;

public class M_Init {
    private V_Init v_init;
    private V_Init v_initConsole, v_initGui;

    public M_Init(C_Init c_init) {
        v_initConsole = new V_InitConsole(c_init, this);
        v_initGui = new V_InitGUI(c_init, this);
    }

    public void reInit(boolean isConsole) throws CustomException {
        CustomLogger.singleton.printLog("reInit " + isConsole, 3);
        CustomLogger.singleton.printLog("v_init " + v_init, 3);
        if (v_init != null)
            v_init.switchUI(isConsole);
        v_init = switchView(isConsole);
        CustomLogger.singleton.printLog("v_init " + v_init, 3);
        v_init.switchUI(isConsole);
    }

    public V_Init switchView(boolean isConsole) {
        return isConsole ? v_initConsole : v_initGui;
    }

    public V_Init isConsole() {
        return v_init;
    }
}
