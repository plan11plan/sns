package com.example.sns.common.util;

public class StringInBoundary {

    public static boolean isInBoundary(String text, Long min, Long max) {
        if (text.length() < min) {
            return false;
        }
        if (text.length() > max) {
            return false;
        }
        return true;
    }
}
