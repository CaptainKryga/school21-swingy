package org.jbashiri.view.init;

public class UIInitConsole implements UIInit {
    public UIInitConsole() {
        printStartMessage();
        printChoise();
    }

    private void printStartMessage() {
        System.out.println("SWINGY 1.0");
        System.out.println("Hello player. Make you choice...");
    }

    private void printChoise() {
        System.out.println("CREATE => Create new HERO");
        System.out.println("LOAD => Load old save HERO");
    }

    //отключение при необходимости
    @Override
    public void delete() {

    }

    @Override
    public boolean isConsole() {
        return true;
    }

    @Override
    public void inputError() {
        System.out.println("INPUT ERROR");
        printChoise();
    }
}
