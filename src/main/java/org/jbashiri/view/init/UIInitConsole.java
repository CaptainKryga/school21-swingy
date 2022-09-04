package org.jbashiri.view.init;

public class UIInitConsole implements UIInit {
    @Override
    public void init() {
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
        System.out.println("SWITCH => Swap to GUI");
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

    @Override
    public void printDivider() {
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }
}
