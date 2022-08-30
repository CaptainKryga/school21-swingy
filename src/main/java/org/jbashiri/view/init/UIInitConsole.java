package org.jbashiri.view.init;

public class UIInitConsole implements UIInit {
    public UIInitConsole() {
        System.out.println("SWINGY 1.0");
        System.out.println("Hello player. Make you choice...");
        System.out.println("CREATE => Create new HERO");
        System.out.println("LOAD => Load old save HERO");
    }

    //отключение при необходимости
    public void delete() {

    }
}
