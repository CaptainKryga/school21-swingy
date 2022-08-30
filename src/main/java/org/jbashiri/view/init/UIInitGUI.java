package org.jbashiri.view.init;

public class UIInitGUI implements UIInit {
    public UIInitGUI() {
        System.out.println("this must be gui...");
    }

    //отключение при необходимости
    @Override
    public void delete() {

    }

    @Override
    public boolean isConsole() {
        return false;
    }

    @Override
    public void inputError() {

    }
}
