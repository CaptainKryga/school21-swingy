package org.jbashiri.model;

import org.jbashiri.controller.C_Load;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.utils.DataBase;
import org.jbashiri.view.load.V_Load;
import org.jbashiri.view.load.V_LoadConsole;
import org.jbashiri.view.load.V_LoadGUI;

import java.util.ArrayList;

public class M_Load {
    private V_Load v_load;
    private V_Load v_loadConsole, v_loadGui;

    public M_Load(C_Load c_load) {
        v_loadConsole = new V_LoadConsole(c_load, this);
        v_loadGui = new V_LoadGUI(c_load, this);
    }

    public void reInit(boolean isConsole) throws CustomException {
        ArrayList<String> list = DataBase.getAll();
        if (v_load != null)
            v_load.switchUI(list, isConsole);
        v_load = switchView(isConsole);
        v_load.switchUI(list, isConsole);
    }

    public V_Load switchView(boolean isConsole) {
        return isConsole ? v_loadConsole : v_loadGui;
    }

    public V_Load isConsole() {
        return v_load;
    }

    public String getPlayerData(String playerName) {
        Player player = DataBase.getHero(playerName);
        return "Name: " + playerName + "\nClass: " + player.getHeroClass().getClassName() +
                "\nLevel: " + player.getLevel() + "\nScore: " + player.getScore();
    }
}
