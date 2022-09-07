package org.jbashiri.model;

import org.jbashiri.controller.C_Create;
import org.jbashiri.exceptions.CustomError;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.CustomValidator;
import org.jbashiri.utils.DataBase;
import org.jbashiri.view.create.V_Create;
import org.jbashiri.view.create.V_CreateConsole;
import org.jbashiri.view.create.V_CreateGUI;

import java.util.ArrayList;

import static org.jbashiri.utils.CustomStrings.checkIgnoreWords;
import static org.jbashiri.utils.CustomStrings.isOnlyWords;

public class M_Create {
    private V_Create v_create;
    private V_Create v_createConsole, v_createGui;
    private String playerName;
    private CustomEnums.HeroClass playerClass;

    public M_Create(C_Create c_create) {
        v_createConsole = new V_CreateConsole(c_create, this);
        v_createGui = new V_CreateGUI(c_create, this);
    }

    public void reInit(CustomEnums.StateCreate state, boolean isConsole) throws CustomException {
        if (v_create != null)
            v_create.switchUI(state, isConsole);
        v_create = switchView(isConsole);
        v_create.switchUI(state, isConsole);
    }

    public V_Create switchView(boolean isConsole) {
        return isConsole ? v_createConsole : v_createGui;
    }

    public V_Create isConsole() {
        return v_create;
    }

    public CustomError isCorrectNewPlayerName(String newName) {
        ArrayList<String> list = DataBase.getAll();

        if (!CustomValidator.isCorrectPlayerClass(newName).equals("")) {
            CustomLogger.singleton.printLog("isCorrectPlayerClass", 2);
            return new CustomError(CustomValidator.isCorrectPlayerClass(newName),
                    CustomEnums.Error.errorIncorrectPlayerClass);
        }

        if (!isOnlyWords(newName.toLowerCase().toCharArray())) {
            CustomLogger.singleton.printLog("isOnlyWords", 2);
            return new CustomError(CustomEnums.Error.errorWords);
        }

        if (checkIgnoreWords(newName.toLowerCase())) {
            CustomLogger.singleton.printLog("checkIgnoreWords", 2);
            return new CustomError(CustomEnums.Error.errorRestrictionWords);
        }

        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i).toLowerCase().split(" ")[0];
            if (temp.equals(newName.toLowerCase())) {
                CustomLogger.singleton.printLog("check list DataBase", 2);
                return new CustomError(CustomEnums.Error.errorIsTaken);
            }
        }
        return new CustomError(CustomEnums.Error.clear);
    }

    public CustomError isCorrectNewClassName(String newClassName) {
        if (!CustomValidator.isCorrectPlayerClass(CustomEnums.getEnumHeroClass(newClassName)).equals("")) {
            CustomLogger.singleton.printLog("isCorrectPlayerClass", 2);
            return new CustomError(CustomValidator.isCorrectPlayerClass(CustomEnums.getEnumHeroClass(newClassName)),
                    CustomEnums.Error.errorIncorrectPlayerName);
        }

        if (CustomEnums.getEnumHeroClass(newClassName.toLowerCase()) == CustomEnums.HeroClass.Null) {
            CustomLogger.singleton.printLog("No such class exists", 2);
            return new CustomError("No such class exists", CustomEnums.Error.errorIncorrectPlayerClass);
        }
        return new CustomError(CustomEnums.Error.clear);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setClassName(String playerClass) {
        CustomLogger.singleton.printLog("clas1s > " + playerClass, 3);
        this.playerClass = CustomEnums.getEnumHeroClass(playerClass);
        CustomLogger.singleton.printLog("clas1s > " + this.playerClass, 3);
    }

    public String getPlayerName() {
        return this.playerName;
    }
    public CustomEnums.HeroClass getPlayerClass() {
        return this.playerClass;
    }
}
