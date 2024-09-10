package com.psp.dummyonelink.util;

public class StringUtils {

    public static String normalize(String input) {
        if (input == null) {
            return null;
        }
        // Convert to lowercase & remove all spaces with empty strings
        return input.toLowerCase().replaceAll("\\s+", "");
    }
}
