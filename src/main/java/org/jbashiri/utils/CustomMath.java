package org.jbashiri.utils;

import java.util.concurrent.ThreadLocalRandom;

public class CustomMath {
    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    //get atk +- 10%
    public static int getRandomCustom(int min) {
        if (min <= 0)
            return 0;
        return ThreadLocalRandom.current().nextInt(min - min / 10, min + min / 10);
    }

    public static int getPow(int num, int pow) {
        int result = num;
        pow--;
        for (; pow > 0; pow--) {
            result *= num;
        }
        return result;
    }

    public static int Abs(int num) {
        if (num < 0)
            num *= -1;
        return num;
    }
}
