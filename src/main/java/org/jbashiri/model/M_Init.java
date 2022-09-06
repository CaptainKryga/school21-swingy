package org.jbashiri.model;

import org.jbashiri.controller.C_Init;
import org.jbashiri.view.init.V_InitConsole;
import org.jbashiri.view.init.V_InitGUI;
import org.jbashiri.view.init.V_Init;

public class M_Init {
    private V_Init v_init;
    private V_Init v_initConsole, v_initGui;

    public M_Init(C_Init c_init) {
        v_initConsole = new V_InitConsole(c_init);
        v_initGui = new V_InitGUI(c_init);
    }

    public void reInit(boolean isConsole) {
        v_init.switchUI(isConsole);
        v_init = switchView(isConsole);
        v_init.switchUI(isConsole);
    }

    public V_Init switchView(boolean isConsole) {
        return isConsole ? v_initConsole : v_initGui;
    }
}
