package org.jbashiri.view.load;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Load;
import org.jbashiri.model.M_Load;
import org.jbashiri.utils.CustomEnums;

import java.util.ArrayList;
import java.util.Scanner;

import static org.jbashiri.utils.CustomEnums.getEnumButton;

public class V_LoadConsole implements V_Load {
    private C_Load c_load;
    private M_Load m_load;
    private Scanner sc;

    public V_LoadConsole(C_Load c_load, M_Load m_load) {
        this.c_load = c_load;
        this.m_load = m_load;
    }

    @Override
    public void switchUI(ArrayList<String> list, boolean isConsole) {
        if (!isConsole)
            return;

        printStartMessage();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ") " + list.get(i));
        }
        System.out.println(list.size() > 0 ? "Write a old name hero or CREATE new hero." :
                "No save heroes. Please CREATE new hero.");

        printDivider();

        if (sc == null) {
            sc = Main.getScanner();
            init(list);
        }
    }

    public void init(ArrayList<String> list) {
        while(sc.hasNextLine()) {
            String line = sc.nextLine().toLowerCase();
            if (m_load.isConsole() == this) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).toLowerCase().split(" ")[0].equals(line)) {
                        c_load.initGame(list.get(i).split(" ")[0], true);
                        return;
                    }
                }

                CustomEnums.Button btn = getEnumButton(sc.nextLine().toLowerCase());
                if (btn == CustomEnums.Button.InitCreate) {
                    c_load.initCreate(true);
                    break;
                } else if (btn == CustomEnums.Button.InitSwitch) {
                    c_load.switchUI(false);
                    break;
                } else {
                    inputError();
                    printStartMessage();
                    printDivider();
                }
            }
        }
    }

    private void printStartMessage() {
        System.out.println("CREATE => create new hero.");
        System.out.println("SWITCH => switch on GUI.");
        System.out.println("SELECTing a old hero: ");
    }

    @Override
    public void inputError() {
        System.out.println("INPUT ERROR");
    }

    @Override
    public void printDivider() {
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }
}
