package org.jbashiri.view.init;

import org.jbashiri.controller.C_Init;

import javax.swing.*;

public class V_InitGUI implements V_Init {
    private static JFrame jp;
    private C_Init c_init;

    public V_InitGUI(C_Init c_init) {
        this.c_init = c_init;
    }

    public void start() {
        System.out.println("INIT GUI");
    }

    @Override
    public void inputError() {

    }

    @Override
    public void printDivider() {

    }

    @Override
    public void switchUI(boolean isConsole) {

    }
}
