package com.pioneersoft.sportmasterbot.util;

public class Timer {

    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep((seconds * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
