package org.jbashiri.view.create;

import org.jbashiri.controller.C_Create;
import org.jbashiri.model.M_Create;
import org.jbashiri.utils.CustomEnums;

public class V_CreateGUI implements V_Create {

    private C_Create c_create;
    private M_Create m_create;

    public V_CreateGUI(C_Create c_create, M_Create m_create) {
        this.c_create = c_create;
        this.m_create = m_create;
    }

    @Override
    public void inputError(CustomEnums.StateCreate state) {
        System.out.println("INPUT ERROR");
        printStartState(state);
    }

    @Override
    public void printDivider() {

    }

    @Override
    public void switchUI(CustomEnums.StateCreate state, boolean isConsole) {

    }

    @Override
    public void init(CustomEnums.StateCreate state) {

    }

    @Override
    public void printStartState(CustomEnums.StateCreate state) {
        if (state == CustomEnums.StateCreate.Name) {
            System.out.println("CREATE \"NAME\" this must be gui...");
        } else if (state == CustomEnums.StateCreate.Class) {
            System.out.println("CREATE \"SELECT CLASS\" this must be gui...");
        }
    }
}
