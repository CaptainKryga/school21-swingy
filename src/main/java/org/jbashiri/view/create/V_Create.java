package org.jbashiri.view.create;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.utils.CustomEnums;

public interface V_Create {
    void init(CustomEnums.StateCreate state) throws CustomException;
    void printStartState(CustomEnums.StateCreate state);
    void inputError(CustomEnums.StateCreate state);
    void printDivider();
    void switchUI(CustomEnums.StateCreate state, boolean isConsole) throws CustomException;

}
