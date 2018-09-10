package com.pioneersoft.sportmasterbot.util;

import com.pioneersoft.sportmasterbot.model.Shop;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

@Service
public class HtmlManager {

    private static final String MAIN_DIR = System.getProperty("user.dir");

    private static final String SEP = System.getProperty("file.separator");


    private static final String FILE_PATH =
            MAIN_DIR +
                    SEP +
                    "frontend" +
                    SEP +
                    "html" +
                    SEP;

    public String readFromFile(String fileName) {
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

    public String getUnauthorizedUserHtml() {
        return "                <div>\n" +
                "                    <div id=\"user-info-check\">\n" +
                "                        <h3>User was not authorised!</h3>\n" +
                "                    </div>\n" +
                "                    <div id=\"order\">\n" +
                "                        <a href=\"/\" class=\"btn-class\" id=\"order-form\">BACK TO AUTH</a>\n" +
                "                    </div>\n" +
                "                </div>";
    }

    public String getShopSelectionRows(Map<String, Shop> shops, String itemId, String login, String password) {
        String html = "";

        String htmlTemplate =

                "                   <tr class=\"tr-content\">\n" +
                        "                        <td class=\"col-1\">%1$s</td>\n" +
                        "                        <td class=\"col-2\">%2$s</td>\n" +
                        "                        <td class=\"col-3 row-btn\">\n" +
                        "                            <div>\n" +
                        "                                <form action=\"/order\" method=\"post\">\n" +
                        "                                    <input type=\"text\" hidden name=\"itemId\" value=\"%3$s\"/>\n" +
                        "                                    <input type=\"text\" hidden name=\"shopId\" value=\"%4$s\"/>\n" +
                        "                                    <input type=\"text\" hidden name=\"login\" value=\"%5$s\"/>\n" +
                        "                                    <input type=\"text\" hidden name=\"password\" value=\"%6$s\"/>\n" +
                        "                                    <input type=\"submit\" class=\"btn-class\" id=\"order-btn\" value=\"Make order\"/>\n" +
                        "                                </form>\n" +
                        "                            </div>\n" +
                        "                        </td>\n" +
                        "                    </tr>\n";
        for (String shopId : shops.keySet()) {
            html += String.format(htmlTemplate, shops.get(shopId).getAddress(), shops.get(shopId).getMetroStation(), itemId, shopId, login, password);
        }
        return html;
    }

    public String getItemCheckBox(String itemId, String login, String password){
        String html;

        String htmlTemplate = "<div id=\"item-check\">\n" +
                "                    <div id=\"form\">\n" +
                "                        <form action=\"/check/item\" method=\"post\">\n" +
                "                            <div class=\"input-item\">\n" +
                "                                <label for=\"input-item\" id=\"label-item\">Atricle ID:</label>\n" +
                "                                <input id=\"input-item\" size=\"24\" type=\"text\" name=\"itemId\" required\n" +
                "                                       placeholder=\"Input article id\" %s>\n" +
                "                                <input type=\"text\" name=\"login\" hidden %s>\n"+
                "                                <input type=\"text\" name=\"password\" hidden %s>\n"+
                "                            </div>\n" +
                "                            <div id=\"botton\">\n" +
                "                                <input type=\"submit\" class=\"btn-class\" value=\"CHECK ARTICLE ID\">\n" +
                "                            </div>\n" +
                "                        </form>\n" +
                "                    </div>\n" +
                "                </div>";

         if (itemId != null && !itemId.isEmpty()){
             html = String.format(htmlTemplate, "value=\""+itemId+"\"", "value=\""+login+"\"", "value=\""+password+"\"");
         }
         else{
             html = String.format(htmlTemplate, "", "value=\""+login+"\"", "value=\""+password+"\"");
         }
        return html;
    }

    public String getItemSelectionPage(String itemBox, String userBox, String rows){
        String html = readFromFile("shop-selection.html");

        String[] parts = html.split("!!!separator");

        StringBuilder sb = new StringBuilder();
        sb.append(parts[0]);
        sb.append(itemBox);
        sb.append(userBox);
        sb.append(parts[1]);
        sb.append(rows);
        sb.append(parts[2]);

        return sb.toString();
    }
}
