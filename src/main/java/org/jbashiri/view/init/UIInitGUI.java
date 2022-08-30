package org.jbashiri.view.init;

public class UIInitGUI implements UIInit {
    @Override
    public void init() {
        System.out.println("INIT this must be gui...");
    }

    @Override
    public boolean isConsole() {
        return false;
    }

    @Override
    public void inputError() {

    }
}
