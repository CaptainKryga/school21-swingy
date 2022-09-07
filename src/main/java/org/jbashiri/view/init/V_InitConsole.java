package org.jbashiri.view.init;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Init;
import org.jbashiri.model.M_Init;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;

import java.util.Scanner;

import static org.jbashiri.utils.CustomEnums.getEnumButton;

public class V_InitConsole implements V_Init {
    private C_Init c_init;
    private M_Init m_init;
    private Scanner sc;

    public V_InitConsole(C_Init c_init, M_Init m_init) {
        this.c_init = c_init;
        this.m_init = m_init;
    }

    @Override
    public void switchUI(boolean isConsole) {
        if (!isConsole)
            return;

        printStartMessage();
        printChoice();

        if (sc == null) {
            sc = Main.getScanner();
            init();
        }
    }

    public void init() {
        while(sc.hasNextLine()) {
            CustomEnums.Button btn = getEnumButton(sc.nextLine().toLowerCase());
            if (m_init.isConsole() == this) {
                if (btn == CustomEnums.Button.InitCreate) {
                    c_init.initCreate(true);
                } else if (btn == CustomEnums.Button.InitLoad) {
                    c_init.initLoad(true);
                } else if (btn == CustomEnums.Button.InitSwitch) {
                    c_init.switchUI(false);
                } else {
                    inputError();
                    printChoice();
                    printDivider();
                }
                CustomLogger.singleton.printLog("TICK Scanner", 3);
            }
        }
    }

    private void printStartMessage() {
        System.out.println("SWINGY 1.0");
        System.out.println("Hello player. Make you choice...");
    }

    private void printChoice() {
        System.out.println("CREATE => Create new HERO");
        System.out.println("LOAD => Load old save HERO");
        System.out.println("SWITCH => Switch to GUI");
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
