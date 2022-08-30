package org.jbashiri.utils;

public class CustomStrings {
    public static String getSpaces(int num) {
        String result = "";
        for (int i = 0; i < num; i++) {
            result += " ";
        }
        return result;
    }

    public static int getLengthNumber(int num) {
        if (num <= 9) {
            return 1;
        }
        int res = 0;
        while (num > 0) {
            num /= 10;
            res++;
            if (num <= 9) {
                res++;
                break;
            }
        }
        return res;
    }
}
