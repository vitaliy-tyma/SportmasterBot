package com.pioneersoft.sportmasterbot.util;

import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.Shop;
import com.pioneersoft.sportmasterbot.util.html.HtmlTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class HtmlManager {

    private static Logger logger = Logger.getLogger(HtmlManager.class.getName());

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

    public String getItemCheckBox(String itemId, String login, String password) {
        String html;

        String htmlTemplate = "<div id=\"item-check\">\n" +
                "                    <div id=\"form\">\n" +
                "                        <form action=\"/check/item\" method=\"post\">\n" +
                "                            <div class=\"input-item\">\n" +
                "                                <label for=\"input-item\" id=\"label-item\">Atricle ID:</label>\n" +
                "                                <input id=\"input-item\" size=\"24\" type=\"text\" name=\"itemId\" required\n" +
                "                                       placeholder=\"Input article id\" %1$s>\n" +
                "                                <input type=\"text\" name=\"login\" hidden %2$s>\n" +
                "                                <input type=\"text\" name=\"password\" hidden %3$s>\n" +
                "                            </div>\n" +
                "                            <div id=\"botton\">\n" +
                "                                <input type=\"submit\" class=\"btn-class\" value=\"CHECK ARTICLE ID\">\n" +
                "                            </div>\n" +
                "                        </form>\n" +
                "                    </div>\n" +
                "                </div>";

        if (itemId != null && !itemId.isEmpty()) {
            html = String.format(htmlTemplate, "value=\"" + itemId + "\"", "value=\"" + login + "\"", "value=\"" + password + "\"");
        } else {
            html = String.format(htmlTemplate, " ", "value=\"" + login + "\"", "value=\"" + password + "\"");
        }
        return html;
    }

    public String getItemSelectionPage(String itemBox, String userBox, String rows) {
        String html = HtmlTemplate.SHOP_SELECTION;

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

    public String getOrderPage(Order order, String login, String pass) {
        String html = HtmlTemplate.ORDER_INFO;
        String[] parts = html.split("!!!Separator");

        return parts[0] + getOrderBox(order, login, pass) + parts[1];
    }

    private String getOrderBox(Order order, String login, String pass) {

        if (order != null){
            return "<div id=\"order-info\">" +
                    "                    Item ID: " + order.getItem().getItemId() +
                    "                    <br><br>" +
                    "                    Item name: " + order.getItem().getItemName() +
                    "                    <br><br>" +
                    "                    Item price: " + order.getItem().getPrice() + " RUB" +
                    "                    <br><br>" +
                    "                    User: " + order.getUser().getName() +
                    "                    <br><br>" +
                    "                    User login: " + order.getUser().getLogin() +
                    "                    <br><br>" +
                    "                    Store metro: " + order.getMetro() +
                    "                    <br><br>" +
                    "                    Store address: " + order.getAddress() +
                    "                    <br><br>" +
                    "                    Order ID: " + order.getOrderId() +
                    "                    <br><br><br><br>" +
                    "                </div>"
                    +
                    "                   <div class=\"content\" id=\"main-box\">" +
                    "                    <div id=\"order\">" +
                    "                        <div id=\"order-form\">" +
                    "                            <div class=\"botton-left\">" +
                    "                        <div id=\"form\">\n" +
                    "                            <form action=\"/check/user\" method=\"post\">\n" +
                    "                                <div class=\"input-name\">\n" +
                    "                                    <input id=\"input-name\" hidden name=\"login\" value=\""+order.getUser().getLogin()+"\">\n" +
                    "                                </div>\n" +
                    "\n" +
                    "                                <div class=\"input-pass\">\n" +
                    "                                    <input id=\"input-pass\" hidden name=\"password\" value=\""+order.getUser().getLogin()+"\">\n" +
                    "                                </div>\n" +
                    "\n" +
                    "                                <div id=\"botton\">\n" +
                    "                                    <input type=\"submit\" class=\"btn-class\" value=\"NEW ORDER\">\n" +
                    "                                </div>\n" +
                    "                            </form>\n" +
                    "                        </div>\n" +
                    "                            </div>" +
                    "                        </div>" +
                    "                    </div>" +
                    "                </div>";
        }
        return "<div id=\"order-info-check\">" +
                " Order was not created                   " +
                "    <br><br><br><br>" +
                "</div>"
                +
                "                   <div class=\"content\" id=\"main-box\">" +
                "                    <div id=\"order\">" +
                "                        <div id=\"order-form\">" +
                "                            <div class=\"botton-left\">" +
                "                        <div id=\"form\">\n" +
                "                            <form action=\"/check/user\" method=\"post\">\n" +
                "                                <div class=\"input-name\">\n" +
                "                                    <input id=\"input-name\" hidden name=\"login\" value=\""+login+"\">\n" +
                "                                </div>\n" +
                "\n" +
                "                                <div class=\"input-pass\">\n" +
                "                                    <input id=\"input-pass\" hidden name=\"password\" value=\""+pass+"\">\n" +
                "                                </div>\n" +
                "\n" +
                "                                <div id=\"botton\">\n" +
                "                                    <input type=\"submit\" class=\"btn-class\" value=\"NEW ORDER\">\n" +
                "                                </div>\n" +
                "                            </form>\n" +
                "                        </div>\n" +
                "                            </div>" +
                "                        </div>" +
                "                    </div>" +
                "                </div>";
    }
}
