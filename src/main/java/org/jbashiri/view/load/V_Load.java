package org.jbashiri.view.load;

import java.util.ArrayList;

public interface V_Load {
    void init(ArrayList<String> list);

    void inputError();

    void printDivider();

    void switchUI(ArrayList<String> list, boolean isConsole);
}
