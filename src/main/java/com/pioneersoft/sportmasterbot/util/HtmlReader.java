package com.pioneersoft.sportmasterbot.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class HtmlReader {

    private static final String MAIN_DIR = System.getProperty("user.dir");

    private static final String SEP = System.getProperty("file.separator");


    private static final String FILE_PATH =
            MAIN_DIR +
                    SEP +
                    "frontend" +
                    SEP +
                    "html" +
                    SEP;

    public static String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try
                (FileReader reader = new FileReader(FILE_PATH + fileName);
                 BufferedReader bufReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            LogManager.writeLogText("Exception in reading " + fileName);
        }

        return stringBuilder.toString();
    }
}
