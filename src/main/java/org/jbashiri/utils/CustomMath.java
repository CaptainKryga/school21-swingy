package org.jbashiri.utils;

import java.util.concurrent.ThreadLocalRandom;

public class CustomMath {
    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min,max);
    }
    public static int getPow(int num, int pow) {
        int result = num;
        for (; pow > 0; pow--) {
            result *= num;
        }
        return result;
    }
}
