package com.lakshman.easyrecharge.util;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isValidMobileNumber(final String input) {
        boolean result = false;

        if (input != null && input.length() == 10) {

            try {
                Long.parseLong(input);
                result = true;
            } catch (NumberFormatException ne) {
                result = false;
            }
        }

        return result;
    }

    public static boolean isValidRechargeAmount(final int amount) {
        return amount > 0 && amount <= 10000;
    }
}
