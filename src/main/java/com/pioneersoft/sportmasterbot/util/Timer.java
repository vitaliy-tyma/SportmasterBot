package com.pioneersoft.sportmasterbot.util;

public class Timer {

    public static void waitSeconds(int seconds) {
        Thread thread = new Thread();
        try {
            thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
