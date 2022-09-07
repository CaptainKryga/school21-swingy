package org.jbashiri.controller;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.M_Init;
import org.jbashiri.utils.CustomLogger;

public class C_Init {
    private C_Create c_create;
    private C_Load c_load;
    private C_Game c_game;
    private M_Init m_init;

    //инициализируем все контроллеры для дальнейшего взаимодействия
    public C_Init(boolean isConsole) throws CustomException {
        m_init = new M_Init(this);
        c_game = new C_Game();
        c_create = new C_Create(c_game);
        c_load = new C_Load(c_game, c_create);

        switchUI(isConsole);

        CustomLogger.singleton.printLog("constructor c_init", 3);
    }

    //запуск окна создания персонажей
    public void initCreate(boolean isConsole) throws CustomException {
        c_create.init(isConsole);
    }

    //открытие окна загрузки персонажей из бд
    public void initLoad(boolean isConsole) throws CustomException {
        c_load.init(isConsole);
    }

    //смена юай
    public void switchUI(boolean isConsole) throws CustomException {
        CustomLogger.singleton.printLog("switchUI", 3);
        m_init.reInit(isConsole);
    }
}
