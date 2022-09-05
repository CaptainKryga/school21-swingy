package org.jbashiri;

import org.jbashiri.controller.ControllerInit;
import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.utils.CustomLogger;

import static org.jbashiri.utils.CustomMath.getPow;

public class Main {
    public static void main(String[] argv) {
        if (argv.length != 1 || (!argv[0].equals("gui") && !argv[0].equals("console"))) {
            System.out.println("usage: java -jar swingy.jar <gui or console>");
            return;
        }

        boolean isConsole = argv[0].toLowerCase().equals("console");

        new CustomLogger(2);
        new ControllerInit(isConsole);

//        new Artifact(1,50);
//        new Artifact(1,50);
//        new Artifact(1,50);
//        new Artifact(1,50);
//        new Artifact(1,50);
//        new Artifact(1,50);

//        for (int i = 0; i < 10; i++) {
//            int str = (i - 1) * 5 + 10;
//            CustomLogger.singleton.printLog("(" + i + " * 1000 + getPow(" + i + " - 1, 2) * 450)", 3);
//            CustomLogger.singleton.printLog("(" + i + "000 + " + getPow(i - 1, 2) + " * 450)", 3);
//            CustomLogger.singleton.printLog("(" + i + "000 + " + getPow(i - 1, 2) * 450, 3);
//            CustomLogger.singleton.printLog(str + "", 3);
//        }


    }
}
