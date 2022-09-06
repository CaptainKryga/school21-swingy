package org.jbashiri.model;

import org.jbashiri.controller.C_Load;
import org.jbashiri.view.load.V_Load;
import org.jbashiri.view.load.V_LoadConsole;
import org.jbashiri.view.load.V_LoadGUI;

public class M_Load {
    private V_Load v_load;
    private V_Load v_loadConsole, v_loadGui;

    public M_Load(C_Load c_load) {
        v_loadConsole = new V_LoadConsole();
        v_loadGui = new V_LoadGUI();
    }
}
