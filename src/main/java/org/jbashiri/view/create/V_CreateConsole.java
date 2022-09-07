package org.jbashiri.view.create;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Create;
import org.jbashiri.exceptions.CustomError;
import org.jbashiri.model.M_Create;
import org.jbashiri.model.classes.*;
import org.jbashiri.utils.CustomEnums;

import java.util.Scanner;

public class V_CreateConsole implements V_Create {
    private C_Create c_create;
    private M_Create m_create;
    private Scanner sc;

    public V_CreateConsole(C_Create c_create, M_Create m_create) {
        this.c_create = c_create;
        this.m_create = m_create;
    }

    @Override
    public void switchUI(CustomEnums.StateCreate state, boolean isConsole) {
        if (!isConsole)
            return;

        printStartState(state);
        printDivider();

        if (sc == null) {
            sc = Main.getScanner();
            init(state);
        }
    }

    @Override
    public void init(CustomEnums.StateCreate state) {
        CustomError error;
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (m_create.isConsole() == this) {
                if (state == CustomEnums.StateCreate.Name) {
                    error = m_create.isCorrectNewPlayerName(line);
                    if (error.type == CustomEnums.Error.clear) {
                        m_create.setPlayerName(line);
                        state = CustomEnums.StateCreate.Class;
                        System.out.println("HERO name: " + line);
                        printStartState(state);
                        printDivider();
                    }
                    else {
                        inputErrorName(error, line);
                        inputError(state);
                        printDivider();
                    }
                } else if (state == CustomEnums.StateCreate.Class) {
                    error = m_create.isCorrectNewClassName(line);
                    if (error.type == CustomEnums.Error.clear) {
                        m_create.setPlayerClass(line);
                        System.out.println("You have chosen a class: " + line);
                        c_create.initGame(m_create.getPlayerName(), m_create.getPlayerClass(), true);
                    }
                    else {
                        inputErrorName(error, line);
                        inputError(state);
                        printDivider();
                    }
                }
            }
        }
    }

    @Override
    public void printStartState(CustomEnums.StateCreate state) {
        if (state == CustomEnums.StateCreate.Name) {
            System.out.println("CREATing a new character.");
            System.out.println("Write a name:");
        } else if (state == CustomEnums.StateCreate.Class) {
            System.out.println("SELECTing a class character:");
            System.out.println("class     hp   att  def  agi  mgc  lck");
            System.out.println(new Warrior().getAllDefaultStats());
            System.out.println(new Mage().getAllDefaultStats());
            System.out.println(new Ranger().getAllDefaultStats());
            System.out.println(new Paladin().getAllDefaultStats());
        }
    }

    @Override
    public void inputError(CustomEnums.StateCreate state) {
        System.out.println("INPUT ERROR");
        printStartState(state);
    }

    public void inputErrorName(CustomError error, String line) {
        if (error.type == CustomEnums.Error.errorWords) {
            System.out.println(line + " > incorrect name[use only words, don't specific simbols]\nPlease write other.");
        } else if (error.type == CustomEnums.Error.errorRestrictionWords) {
            System.out.println(line + " > restriction name [don't use name's: (create)(load)(switch)]\nPlease write other.");
        } else if (error.type == CustomEnums.Error.errorIsTaken) {
            System.out.println(line + " > name is TAKEN\nPlease write other.");
        } else if (error.type == CustomEnums.Error.errorIncorrectPlayerName) {
            System.out.println(line + " > " + error.error + "\nPlease write other.");
        } else if (error.type == CustomEnums.Error.errorIncorrectPlayerClass) {
            System.out.println(line + " > " + error.error + "\nPlease write other.");
        }
    }

    @Override
    public void printDivider() {
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }
}
