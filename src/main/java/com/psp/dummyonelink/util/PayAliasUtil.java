package com.psp.dummyonelink.util;

import java.security.SecureRandom;
import java.util.Random;

public class PayAliasUtil {

    private static final String PREFIX = "1PAY-";
    private static final int ID_LENGTH = 12 - PREFIX.length(); // Total length minus the prefix length
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new SecureRandom();

    public static String generateUnique1PayId() {
        StringBuilder idBuilder = new StringBuilder(PREFIX);

        for (int i = 0; i < ID_LENGTH; i++) {
            int index = RANDOM.nextInt(ALPHANUMERIC.length());
            idBuilder.append(ALPHANUMERIC.charAt(index));
        }

        return idBuilder.toString();
    }

    // Example usage
    public static void main(String[] args) {
        String unique1PayId = generateUnique1PayId();
        System.out.println("Generated 1PAY-ID: " + unique1PayId);
    }
}
