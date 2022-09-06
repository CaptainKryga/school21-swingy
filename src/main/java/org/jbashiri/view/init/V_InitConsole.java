package org.jbashiri.view.init;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Init;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;

import java.util.Scanner;

import static org.jbashiri.utils.CustomEnums.getEnumButton;

public class V_InitConsole implements V_Init {
    private C_Init c_init;

    public V_InitConsole(C_Init c_init) {
        this.c_init = c_init;
    }

    @Override
    public void switchUI(boolean isConsole) {
        if (isConsole) {
            start();
        }
    }

    public void start() {

        printStartMessage();
        printChoice();

        Main.hideFrame();
        Scanner sc = Main.getScanner();

        while(sc.hasNextLine()) {
            CustomEnums.Button btn = getEnumButton(sc.nextLine().toLowerCase());
            if (btn == CustomEnums.Button.InitCreate) {
                c_init.initCreate();
            } else if (btn == CustomEnums.Button.InitLoad) {
                c_init.initLoad();
            } else if (btn == CustomEnums.Button.InitSwitch) {
                c_init.switchUI(false);
            } else {
                inputError();
                printChoice();
                printDivider();
            }
            CustomLogger.singleton.printLog("TICK Scanner", 3);
        }

        Main.closeScanner();
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
