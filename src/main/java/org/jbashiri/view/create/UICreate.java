package org.jbashiri.view.create;

public interface UICreate {
    public void init();

    void printState(int state);
    void printName(String name);
    void printClass(String name);
    void printConfirm();

    void inputError(int state);
    public void printDivider();

}
