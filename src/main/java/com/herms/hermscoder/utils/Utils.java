package com.herms.hermscoder.utils;

public class Utils {
    private Utils(){}

    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    public static String joinStrings(String... strs) {
        String result = "";
        for(String s : strs) {
            if(!isEmptyOrNull(s)) {
                result += s;
            }
        }
        return result;
    }
}
