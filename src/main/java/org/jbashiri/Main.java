package org.jbashiri;

import org.jbashiri.controller.ControllerInit;
import org.jbashiri.view.init.UIInit;

import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        if (argv.length != 1 || (!argv[0].equals("gui") && !argv[0].equals("console"))) {
            System.out.println("usage: java -jar swingy.jar <gui or console>");
            return;
        }

        new ControllerInit().switchUI(argv[0].equals("console"));

        System.out.println("=============> Main");
    }
}
