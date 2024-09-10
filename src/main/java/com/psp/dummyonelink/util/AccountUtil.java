package com.psp.dummyonelink.util;

import java.util.UUID;

public class AccountUtil {

    public static String generateIbanNumber(String prefix) {
        String uniqueIdentifier = UUID.randomUUID().toString().replaceAll("-", "");
        return prefix + 0011 + 00 + uniqueIdentifier.substring(0, Math.min(10, uniqueIdentifier.length()));
    }

 public static String generateAccountNumber(String prefix) {
        String uniqueIdentifier = UUID.randomUUID().toString().replaceAll("-", "");
        return prefix + uniqueIdentifier.substring(0, Math.min(7, uniqueIdentifier.length()));
    }
}
