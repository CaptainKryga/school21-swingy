package org.jbashiri.utils;

public class CustomLogger {
    public static CustomLogger singleton;

    //3 мусор
    //2 не очень важные
    //1 важные
    //0 ничего
    private int lvl;
    public CustomLogger(int lvl) {
        singleton = this;
        this.lvl = lvl;
    }

    public void printLog(String log, int lvl) {
        if (lvl <= this.lvl) {
            System.out.println("CustomLogger: " + log);
        }
    }
}
