package org.jbashiri.utils;

public class CustomStrings {
    private static String correctChars = "qwertyuioplkjhgfdsazxcvbnm";
    public static String getSpaces(int num) {
        if (num < 0)
            return "";
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

    public static boolean isOnlyWords(char[] str) {
        char[] correct = correctChars.toCharArray();
        for (int x = 0; x < str.length; x++) {
            boolean isCorrect = false;
            for (int y = 0; y < correct.length; y++) {
                if (str[x] == correct[y]) {
                    isCorrect = true;
                }
            }
            if (!isCorrect) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIgnoreWords(String str) {
        if (!str.equals("load") && !str.equals("create") &&
            !str.equals("switch"))
            return false;
        return true;
    }
}
