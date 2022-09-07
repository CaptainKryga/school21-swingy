package org.jbashiri.view.init;

import org.jbashiri.exceptions.CustomException;

public interface V_Init {
    void inputError();
    void printDivider();
    void switchUI(boolean isConsole) throws CustomException;
}
