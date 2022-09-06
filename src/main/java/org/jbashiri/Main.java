package org.jbashiri;

import org.jbashiri.controller.ControllerInit;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.DataBase;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] argv) throws CustomException, SQLException, ClassNotFoundException {
        if (argv.length != 1 || (!argv[0].equals("gui") && !argv[0].equals("console"))) {
            System.out.println("usage: java -jar swingy.jar <gui or console>");
            return;
        }

        boolean isConsole = argv[0].toLowerCase().equals("console");

//        DataBase.createNewDatabase("heroes.db");
//        DataBase.createTable();
        DataBase.connect();

        ArrayList<String> list = DataBase.getAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        DataBase.deleteAllHeroes();

        new CustomLogger(2);
        new ControllerInit(isConsole);
//
        DataBase.disconnect();
    }
}
