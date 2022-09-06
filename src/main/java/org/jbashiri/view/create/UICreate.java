package org.jbashiri.view.create;

public interface UICreate {
    public void init();

    void printState(int state);
    void printName(String name);
    void printClass(String name);

    void inputError(int state);
    void inputErrorName(String line);
    public void printDivider();

}
