package org.jbashiri.view.load;

import org.jbashiri.exceptions.CustomException;

import java.util.ArrayList;

public interface V_Load {
    void init(ArrayList<String> list) throws CustomException;

    void inputError();

    void printDivider();

    void switchUI(ArrayList<String> list, boolean isConsole) throws CustomException;
}
