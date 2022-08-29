package org.jbashiri;

public class Main {
    public static void main(String[] argv) {
        if (argv.length != 1 || (!argv[0].equals("gui") && !argv[0].equals("console"))) {
            System.out.println("usage: java -jar swingy.jar <gui or console>");
            return;
        }
        System.out.println("=============> Main");
    }
}
