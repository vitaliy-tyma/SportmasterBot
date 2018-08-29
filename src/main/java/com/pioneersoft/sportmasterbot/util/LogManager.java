package com.pioneersoft.sportmasterbot.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogManager {

    private static final String LOG_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "log" + System.getProperty("file.separator") + "log.txt";

    public static void writeLogText(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH)){
            text = new Date(System.currentTimeMillis()) + " - " +  text + "\n";
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
