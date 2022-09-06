package org.jbashiri.view.load;

import java.util.ArrayList;

public interface UILoad {
    public void init(ArrayList<String> list);

    public boolean isConsole();

    public void inputError();

    public void printDivider();
}
