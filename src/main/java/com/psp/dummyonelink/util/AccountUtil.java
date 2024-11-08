package com.psp.dummyonelink.util;

import java.util.Random;
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

    public static String generateSwiftBankAccountNumber(String bankCode) {
        Random random = new Random();
        int randomDigits = 100000 + random.nextInt(900000);
        return bankCode + randomDigits;
    }
}
