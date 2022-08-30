package org.jbashiri.utils;

import java.util.concurrent.ThreadLocalRandom;

public class CustomRandom {
    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min,max);
    }
}
