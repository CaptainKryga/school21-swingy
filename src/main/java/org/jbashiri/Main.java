package org.jbashiri;

import org.jbashiri.controller.C_Init;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.CustomValidator;
import org.jbashiri.utils.DataBase;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static JFrame frame;

    public static void main(String[] argv) throws CustomException, SQLException, ClassNotFoundException {
        if (argv.length != 1 || (!argv[0].equals("gui") && !argv[0].equals("console"))) {
            System.out.println("usage: java -jar swingy.jar <gui or console>");
            return;
        }

        connectDataBase();
        boolean isConsole = argv[0].toLowerCase().equals("console");

        new CustomLogger(3);
        new CustomValidator();
        new C_Init(isConsole);

//        createNewDataBase("heroes");
//        createNewTable("heroes");
//        deleteAllHeroes();

        disconnectDataBase();
    }

    //CONSOLE
    public static Scanner getScanner() {
        if (scanner == null)
            scanner = new Scanner(System.in);
        return scanner;
    }
    public static void closeScanner() {
        CustomLogger.singleton.printLog("closeScanner: " + scanner, 3);
        if (scanner != null)
            scanner.close();
        CustomLogger.singleton.printLog("closeScanner: " + scanner, 3);
    }

    //GUI
    public static JFrame getFrame() {
        CustomLogger.singleton.printLog("getFrame", 3);
        if (frame == null) {
            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frameListener();
        }
        return frame;
    }
    private static void frameListener() {
        getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    disconnectDataBase();
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
                closeScanner();
                super.windowClosing(e);
            }
        });
    }
    public static void showFrame() {
        if (frame != null)
            frame.setVisible(true);
    }
    public static void hideFrame() {
        if (frame != null)
            frame.setVisible(false);
    }

    //DATABASE
    public static void connectDataBase() throws CustomException {
        DataBase.connect();
    }
    public static void disconnectDataBase() throws CustomException {
        DataBase.disconnect();
    }
    public static void createNewDataBase(String name) {
        DataBase.createNewDatabase(name = "heroes.db");
    }
    public static void createNewTable(String name) throws SQLException, CustomException, ClassNotFoundException {
        DataBase.createTable(name = "heroes");
    }
    public static void deleteAllHeroes() {
        DataBase.deleteAllHeroes();
    }
}
