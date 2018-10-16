package com.pioneersoft.sportmasterbot.util.html;

public class HtmlTemplate {

    public static final String USER_INPUT = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Authorization</title>\n" +
            "    <style type=\"text/css\">\n" +
            "\n" +
            "body{\n" +
            "\tbackground-color: #f1f0f0;\n" +
            "\tcolor: #2E2A25;\n" +
            "}\n" +
            "\n" +
            "#header{\n" +
            "\tpadding: 10px 60px;\n" +
            "\theight: 80px;\n" +
            "\tbackground-color: #ff9900;\n" +
            "}\n" +
            ".content{\n" +
            "\tmin-width: 960px;\n" +
            "\tmax-width: 1200px;\n" +
            "\tmargin: 0px auto;\n" +
            "}\n" +
            "#header h2{\n" +
            "\tmargin: 30px auto;\n" +
            "\tdisplay: block;\n" +
            "\n" +
            "}\n" +
            "#header h2 a{\n" +
            "\ttext-decoration: none;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 22px;\n" +
            "\tcolor: #333333;\n" +
            "}\n" +
            "#header h2 a:hover{\n" +
            "\tcolor: #3399cc;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li{\n" +
            "\tfloat: left;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tfont-weight: bold;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 16px;\n" +
            "\tdisplay: block;\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li a{\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #2E2A25;\n" +
            "\tpadding: 10px 30px;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px 5px 0 0;\n" +
            "  \tborder-color: #ff9900;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li a.active{\n" +
            "\tbackground-color: #f1d290;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs{\n" +
            "\tmargin-bottom: 15px;\n" +
            "\tmargin-left: -40px;\n" +
            "}\n" +
            "\n" +
            ".tab-content{\n" +
            "\tpadding: 10px 10px 20px 10px;\n" +
            "\tmargin-top: 10px;\n" +
            "\twidth: 1180px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 14px;\n" +
            "}\n" +
            "\n" +
            "table{\n" +
            "\twidth: 1227px;\n" +
            "\tborder: 2px solid #dddfe1;\n" +
            "\tborder-collapse: collapse;\n" +
            "\tcolor: #333333;\n" +
            "\n" +
            "\n" +
            "}\n" +
            "\n" +
            "th{\n" +
            "\tbackground-color: #edeeee;\n" +
            "\tcolor: #333333;\n" +
            "}\n" +
            "td, th{\n" +
            "\tborder: 2px solid #dddfe1;\n" +
            "\theight: 10px;\n" +
            "}\n" +
            "tr{\n" +
            "\ttext-align: center;\n" +
            "}\n" +
            "\n" +
            ".tr-header{\n" +
            "\ttext-transform: uppercase;\n" +
            "\tfont-weight: bold;\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".tr-header th{\n" +
            "\tpadding: 10px 0 8px 0;\n" +
            "\tfont-size: 12px;\n" +
            "\tbackground-color: #FEF0AE;\n" +
            "}\n" +
            "\n" +
            ".tr-content th{\n" +
            "\tpadding: 1px 3px;\n" +
            "\tfont-size: 12px;\n" +
            "\ttext-align: left;\n" +
            "}\n" +
            "\n" +
            "tr .row-btn{\n" +
            "\ttext-align: center;\n" +
            "}\n" +
            "\n" +
            ".row-btn text{\n" +
            "\tcolor: #f1f0f0;\n" +
            "}\n" +
            "\n" +
            ".table-header{\n" +
            "\tmargin-top: 8px;\n" +
            "}\n" +
            "\n" +
            ".table-header a{\n" +
            "\tfont-size: 14px;\n" +
            "\tfont-weight: bold;\n" +
            "\tmargin-left: 1128px;\n" +
            "\tpadding: 5px 15px;\n" +
            "\tcolor: #2E2A25;\n" +
            "\ttext-decoration: none;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px;\n" +
            "  \tborder-color: #ff9900;\n" +
            "  \tbackground-color: #f1d290\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".table-header a:hover{\n" +
            "\tcolor: #2E2A25;\n" +
            "}\n" +
            "\n" +
            ".table-footer a{\n" +
            "\tfont-size: 14px;\n" +
            "\tfont-weight: bold;\n" +
            "\tmargin-left: 20px;\n" +
            "\tmargin-bottom: 10px;\n" +
            "\tpadding: 5px 15px;\n" +
            "\tcolor: #2E2A25;\n" +
            "\ttext-decoration: none;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px;\n" +
            "  \tborder-color: #ff9900;\n" +
            "  \tbackground-color: #f1d290\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".table-footer a:hover{\n" +
            "\tcolor: #2E2A25;\n" +
            "}\n" +
            "\n" +
            "#filter{\n" +
            "\tmargin: -4px 0px 10px 0px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 15px;\n" +
            "\tborder-radius: 2px;\n" +
            "\tfloat: left;\n" +
            "}\n" +
            "#authorisation{\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px;\n" +
            "  \tborder-color: #ff9900;\n" +
            "\tmargin-top: 15px;\n" +
            "\twidth: 320px;\n" +
            "\tpadding: 20px 30px 30px 30px;\n" +
            "\tmargin-left: 15px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\ttext-transform: none;\n" +
            "\n" +
            "}\n" +
            "\n" +
            "#label-name{\n" +
            "\tmargin: 10px 30px 10px 0px;\n" +
            "\n" +
            "}\n" +
            "\n" +
            "#input-name{\n" +
            "\tmargin: 10px 0px 10px 14px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 15px;\n" +
            "\tborder-radius: 2px;\n" +
            "}\n" +
            "\n" +
            "#label-pass{\n" +
            "\tmargin: 10px 4px 10px 0px;\n" +
            "}\n" +
            "\n" +
            "#input-pass{\n" +
            "\tmargin-left: 15px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 15px;\n" +
            "\tborder-radius: 2px;\n" +
            "}\n" +
            "\n" +
            "#botton{\n" +
            "\tmargin: 20px 26px 0px 104px;\n" +
            "}\n" +
            "\n" +
            ".btn-class{\n" +
            " display: inline-block;\n" +
            " border:2px solid #CFB335;\n" +
            " color: #191502;\n" +
            " border-radius: 4px 4px 4px 4px;\n" +
            " -webkit-border-radius: 4px 4px 4px 4px;\n" +
            " -moz-border-radius: 4px 4px 4px 4px;\n" +
            " font-family: Verdana;\n" +
            " width: 201px;\n" +
            " height: auto;\n" +
            " font-size: 16px;\n" +
            " padding: 5px;\n" +
            " margin-left: 11px;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " text-shadow: 0 1px 0 #FFFFFF;\n" +
            " background-image: linear-gradient(to top, #F6FCA6, #FCFCFC);\n" +
            " background-color: #A1A165;\n" +
            "}\n" +
            "\n" +
            ".btn-class:hover, {\n" +
            " border:2px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #FCDF5B;\n" +
            "}\n" +
            "\n" +
            ".btn-class:active{\n" +
            "border:2px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #967D09;\n" +
            "}\n" +
            "\n" +
            "#keyname{\n" +
            "\tmargin-right: 10px;\n" +
            "\tfloat: right;\n" +
            "}\n" +
            "#input-count{\n" +
            "\tmargin-top:15px;\n" +
            "\n" +
            "}\n" +
            "#count{\n" +
            "\tmargin-right: 10px;\n" +
            "\tmargin-bottom: 15px;\n" +
            "\tfloat: right;\n" +
            "}\n" +
            "\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div id=\"header\">\n" +
            "    <div class=\"content\">\n" +
            "        <h2>Welcome to Sportmaster order API</h2>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<br>\n" +
            "<br>\n" +
            "<div class=\"content\">\n" +
            "    <div class=\"table\">\n" +
            "        <br>\n" +
            "        <div class=\"tab-content\" class=\"table-tab\">\n" +
            "            <div class=\"table-content\">\n" +
            "                <div class=\"content\" id=\"main-box\">\n" +
            "                    <div id=\"authorisation\">\n" +
            "                        <div id=\"form\">\n" +
            "                            <form action=\"/check/user\" method=\"post\">\n" +
            "                                <div class=\"input-name\">\n" +
            "                                    <label for=\"input-name\" id=\"label-name\">Name:</label>\n" +
            "                                    <input id=\"input-name\" size=\"26\" type=\"text\" name=\"login\" required\n" +
            "                                           placeholder=\"Input login\">\n" +
            "                                </div>\n" +
            "\n" +
            "                                <div class=\"input-pass\">\n" +
            "                                    <label for=\"input-pass\" id=\"label-pass\">Password:</label>\n" +
            "                                    <input id=\"input-pass\" size=\"26\" type=\"password\" name=\"password\" required\n" +
            "                                           placeholder=\"Input password\">\n" +
            "                                </div>\n" +
            "\n" +
            "                                <div id=\"botton\">\n" +
            "                                    <input type=\"submit\" class=\"btn-class\" value=\"CHECK USER\">\n" +
            "                                </div>\n" +
            "                            </form>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";

    public static final String ORDER_INFO = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Order information</title>\n" +
            "\n" +
            "    <style type=\"text/css\">\n" +
            "\n" +
            "body{\n" +
            "\tbackground-color: #f1f0f0;\n" +
            "\tcolor: #2E2A25;\n" +
            "}\n" +
            "\n" +
            "#header{\n" +
            "\tpadding: 10px 60px;\n" +
            "\theight: 80px;\n" +
            "\tbackground-color: #ff9900;\n" +
            "}\n" +
            ".content{\n" +
            "\tmin-width: 960px;\n" +
            "\tmax-width: 1200px;\n" +
            "\tmargin: 0px auto;\n" +
            "}\n" +
            "#header h2{\n" +
            "\tmargin: 30px auto;\n" +
            "\tdisplay: block;\n" +
            "\n" +
            "}\n" +
            "#header h2 a{\n" +
            "\ttext-decoration: none;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 22px;\n" +
            "\tcolor: #333333;\n" +
            "}\n" +
            "#header h2 a:hover{\n" +
            "\tcolor: #3399cc;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li{\n" +
            "\tfloat: left;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tfont-weight: bold;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 16px;\n" +
            "\tdisplay: block;\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li a{\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #2E2A25;\n" +
            "\tpadding: 10px 30px;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px 5px 0 0;\n" +
            "  \tborder-color: #ff9900;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li a.active{\n" +
            "\tbackground-color: #f1d290;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs{\n" +
            "\tmargin-bottom: 15px;\n" +
            "\tmargin-left: -40px;\n" +
            "}\n" +
            "\n" +
            ".tab-content{\n" +
            "\tpadding: 10px 10px 20px 10px;\n" +
            "\tmargin-top: 17px;\n" +
            "\twidth: 1180px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 17px;\n" +
            "}\n" +
            "\n" +
            "#label-name{\n" +
            "\tmargin: 10px 30px 10px 0px;\n" +
            "\n" +
            "}\n" +
            "\n" +
            "#input-name{\n" +
            "\tmargin: 10px 0px 10px 14px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 15px;\n" +
            "\tborder-radius: 2px;\n" +
            "}\n" +
            "\n" +
            "#label-pass{\n" +
            "\tmargin: 10px 4px 10px 0px;\n" +
            "}\n" +
            "\n" +
            "#input-pass{\n" +
            "\tmargin-left: 15px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 15px;\n" +
            "\tborder-radius: 2px;\n" +
            "}\n" +
            "\n" +
            ".botton-right, #link-to-start{\n" +
            "\tmargin: 20px 26px 0px 685px;\n" +
            "\ttext-decoration: none;\n" +
            "\ttext-align:  center;\n" +
            "}\n" +
            "\n" +
            ".botton-left{\n" +
            "\tmargin: 20px 26px 0px 15px;\n" +
            "}\n" +
            "\n" +
            ".btn-class, #link-to-start{\n" +
            " display: inline-block;\n" +
            " border:2px solid #CFB335;\n" +
            " color: #191502;\n" +
            " border-radius: 4px 4px 4px 4px;\n" +
            " -webkit-border-radius: 4px 4px 4px 4px;\n" +
            " -moz-border-radius: 4px 4px 4px 4px;\n" +
            " font-family: Verdana;\n" +
            " width: 201px;\n" +
            " height: auto;\n" +
            " font-size: 16px;\n" +
            " padding: 5px;\n" +
            " margin-left: 11px;\n" +
            " margin-top: 90px;\n\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " text-shadow: 0 1px 0 #FFFFFF;\n" +
            " background-image: linear-gradient(to top, #F6FCA6, #FCFCFC);\n" +
            " background-color: #A1A165;\n" +
            "}\n" +
            "\n" +
            ".btn-class:hover, #link-to-start:hover{\n" +
            " border:2px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #FCDF5B;\n" +
            "}\n" +
            "\n" +
            ".btn-class:active, #link-to-start:active{\n" +
            "border:2px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #967D09;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "#keyname{\n" +
            "\tmargin-right: 10px;\n" +
            "\tfloat: right;\n" +
            "}\n" +
            "#input-count{\n" +
            "\tmargin-top:15px;\n" +
            "\n" +
            "}\n" +
            "#count{\n" +
            "\tmargin-right: 10px;\n" +
            "\tmargin-bottom: 15px;\n" +
            "\tfloat: right;\n" +
            "}\n" +
            "\n" +
            "#authorisation{\n" +
            "    float: left;\n" +
            "    margin: -57px 0 0 -200px;\n" +
            "}\n" +
            "\n" +
            "#order{\n" +
            "    float: right;\n" +
            "    margin: -77px 0 0 -30px;\n" +
            "}\n" +
            "\n" +
            "#order-info-check{\n" +
            "float: left;\n" +
            "color: #ff0c0c;\n" +
            "margin: 0 0 0 20px;\n" +
            "<!--color: #ff9900;-->\n" +
            "}\n" +
            "\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div id=\"header\">\n" +
            "    <div class=\"content\">\n" +
            "        <h2>Welcome to Sportmaster order API</h2>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<br>\n" +
            "<br>\n" +
            "<div class=\"content\">\n" +
            "    <div class=\"table\">\n" +
            "        <br>\n" +
            "        <div class=\"tab-content\" class=\"table-tab\">\n" +
            "            <div class=\"table-content\">\n" +
            "                <div>\n" +
            "                    !!!Separator\n" +
            "\t\t\t\t</div>\n" +
            "\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";


    public static final String SHOP_SELECTION = "<!DOCTYPE html>\n" +
            "<style type=\"text/css\">\n" +
            "\n" +
            "body{\n" +
            "\tbackground-color: #f1f0f0;\n" +
            "\tcolor: #2E2A25;\n" +
            "}\n" +
            "\n" +
            "#header{\n" +
            "\tpadding: 10px 60px;\n" +
            "\theight: 80px;\n" +
            "\tbackground-color: #ff9900;\n" +
            "}\n" +
            ".content{\n" +
            "\tmin-width: 960px;\n" +
            "\tmax-width: 1200px;\n" +
            "\tmargin: 0px auto;\n" +
            "}\n" +
            "#header h2{\n" +
            "\tmargin: 30px auto;\n" +
            "\tdisplay: block;\n" +
            "\n" +
            "}\n" +
            "#header h2 a{\n" +
            "\ttext-decoration: none;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 22px;\n" +
            "\tcolor: #333333;\n" +
            "}\n" +
            "#header h2 a:hover{\n" +
            "\tcolor: #3399cc;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li{\n" +
            "\tfloat: left;\n" +
            "\tmargin-left: -40px;\n" +
            "\tmargin-right: 50px;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tfont-weight: bold;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 16px;\n" +
            "\tdisplay: block;\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li a{\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #2E2A25;\n" +
            "\tpadding: 11px 30px;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px 5px 0 0;\n" +
            "  \tborder-color: #ff9900;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs li a.active{\n" +
            "\tbackground-color: #f1d290;\n" +
            "}\n" +
            "\n" +
            ".nav-tabs{\n" +
            "\tmargin-bottom: 15px;\n" +
            "}\n" +
            "\n" +
            ".tab-content{\n" +
            "\tpadding: 10px 10px 20px 10px;\n" +
            "\tmargin-top: 10px;\n" +
            "\twidth: 1161px;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 0 5px 5px 5px;\n" +
            "  \tborder-color: #ff9900;\n" +
            "  \tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 14px;\n" +
            "}\n" +
            "\n" +
            "table{\n" +
            "    table-layout: fixed; /* Фиксированная ширина ячеек */\n" +
            "    width: 100%; /* Ширина таблицы */\n" +
            " \twidth: 800px;\n" +
            "\tborder: 2px solid #dddfe1;\n" +
            "\tborder-collapse: collapse;\n" +
            "\tcolor: #333333;\n" +
            "}\n" +
            "\n" +
            "th{\n" +
            "\tbackground-color: #edeeee;\n" +
            "\tcolor: #333333;\n" +
            "}\n" +
            "td, th{\n" +
            "\tborder: 2px solid #dddfe1;\n" +
            "\theight: 10px;\n" +
            "}\n" +
            "tr{\n" +
            "\ttext-align: center;\n" +
            "}\n" +
            "\n" +
            ".tr-header{\n" +
            "\ttext-transform: uppercase;\n" +
            "\tfont-weight: bold;\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".tr-header th{\n" +
            "\tpadding: 10px 0 8px 0;\n" +
            "\tfont-size: 12px;\n" +
            "\tbackground-color: #FEF0AE;\n" +
            "}\n" +
            "\n" +
            ".tr-content th{\n" +
            "\tpadding: 1px 3px;\n" +
            "\tfont-size: 12px;\n" +
            "\ttext-align: left;\n" +
            "}\n" +
            "\n" +
            "tr .row-btn{\n" +
            "\ttext-align: center;\n" +
            "}\n" +
            "\n" +
            ".row-btn text{\n" +
            "\tcolor: #f1f0f0;\n" +
            "}\n" +
            "\n" +
            ".table-header{\n" +
            "\tmargin-top: 8px;\n" +
            "}\n" +
            "\n" +
            ".table-header a{\n" +
            "\tfont-size: 14px;\n" +
            "\tfont-weight: bold;\n" +
            "\n" +
            "\tpadding: 5px 15px;\n" +
            "\tcolor: #2E2A25;\n" +
            "\ttext-decoration: none;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px;\n" +
            "  \tborder-color: #ff9900;\n" +
            "  \tbackground-color: #f1d290\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".table-header a:hover{\n" +
            "\tcolor: #2E2A25;\n" +
            "}\n" +
            "\n" +
            ".table-footer a{\n" +
            "\tfont-size: 14px;\n" +
            "\tfont-weight: bold;\n" +
            "\tmargin-left: 20px;\n" +
            "\tmargin-bottom: 10px;\n" +
            "\tpadding: 5px 15px;\n" +
            "\tcolor: #2E2A25;\n" +
            "\ttext-decoration: none;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px;\n" +
            "  \tborder-color: #ff9900;\n" +
            "  \tbackground-color: #f1d290\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".table-footer a:hover{\n" +
            "\tcolor: #2E2A25;\n" +
            "}\n" +
            "\n" +
            "#filter{\n" +
            "\tmargin: -4px 0px 10px 0px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 15px;\n" +
            "\tborder-radius: 2px;\n" +
            "\tfloat: left;\n" +
            "}\n" +
            "\n" +
            "#filter-btn{\n" +
            "\n" +
            " display: inline-block;\n" +
            " border:1px solid #ff9900;\n" +
            " color: #191502;\n" +
            " border-radius: 4px 4px 4px 4px;\n" +
            " -webkit-border-radius: 4px 4px 4px 4px;\n" +
            " -moz-border-radius: 4px 4px 4px 4px;\n" +
            " font-family: Verdana;\n" +
            " width: 95px;\n" +
            " height: auto;\n" +
            " font-size: 14px;\n" +
            " padding: 1px;\n" +
            " margin-left: 15px;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " text-shadow: 0 1px 0 #FFFFFF;\n" +
            " background-image: linear-gradient(to top, #F6FCA6, #FCFCFC);\n" +
            " background-color: #f1d290;\n" +
            "}\n" +
            "\n" +
            "#filter-btn: hover, {\n" +
            " border:1px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #f1d290;\n" +
            "}\n" +
            "\n" +
            "#filter-btn:active{\n" +
            "border:1px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #f1d290;\n" +
            "}\n" +
            "\n" +
            "#item-check{\n" +
            "\tborder: 1px solid transparent;\n" +
            "  \tborder-radius: 5px;\n" +
            "  \tborder-color: #ff9900;\n" +
            "\tmargin-top: 5px;\n" +
            "\twidth: 320px;\n" +
            "\tpadding: 20px 234px 38px 30px;\n" +
            "\tmargin-left: 2px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\ttext-transform: none;\n" +
            "\n" +
            "}\n" +
            "\n" +
            "#label-item{\n" +
            "\tmargin: 10px 4px 10px 0px;\n" +
            "}\n" +
            "\n" +
            ".input-item{\n" +
            " float: left;\n" +
            "}\n" +
            "\n" +
            "#input-item, option{\n" +
            "\tmargin-left: 15px;\n" +
            "\tfont-family: Arial, Verdana, Tahoma sans-serif;\n" +
            "\tfont-size: 15px;\n" +
            "\tborder-radius: 2px;\n" +
            "}\n" +
            "\n" +
            "#botton{\n" +
            "\tmargin: -2px -213px 50px;\n" +
            "\tfloat: right;\n" +
            "}\n" +
            "\n" +
            "#order-btn{\n" +
            "    font-size: 10px;\n" +
            "    width: 90px;\n" +
            "    border: 1px solid #CFB335;\n" +
            "}\n" +
            "\n" +
            ".btn-class{\n" +
            " display: inline-block;\n" +
            " border:2px solid #CFB335;\n" +
            " color: #191502;\n" +
            " border-radius: 4px 4px 4px 4px;\n" +
            " -webkit-border-radius: 4px 4px 4px 4px;\n" +
            " -moz-border-radius: 4px 4px 4px 4px;\n" +
            " font-family: Verdana;\n" +
            " width: 201px;\n" +
            " height: auto;\n" +
            " font-size: 15px;\n" +
            " padding: 1px;\n" +
            " margin-left: 5px;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D,0 2px 4px 0 #D4D4D4;\n" +
            " text-shadow: 0 1px 0 #FFFFFF;\n" +
            " background-image: linear-gradient(to top, #F6FCA6, #FCFCFC);\n" +
            " background-color: #A1A165;\n" +
            "}\n" +
            "\n" +
            ".btn-class:hover, {\n" +
            " border:2px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #FCDF5B;\n" +
            "}\n" +
            "\n" +
            ".btn-class:active{\n" +
            "border:2px solid #967D09;\n" +
            " color: #705D07;\n" +
            " box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -moz-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " -webkit-box-shadow: inset 0 1px 0 0 #FFF6CE,inset 0 -1px 0 0 #E3C852,inset 0 0 0 1px #FCE88D;\n" +
            " background-color: #967D09;\n" +
            "}\n" +
            "\n" +
            ".col-1{\n" +
            "width: 740px;\n" +
            "}\n" +
            "\n" +
            ".col-2{\n" +
            "width: 300px;\n" +
            "}\n" +
            "\n" +
            ".col-3{\n" +
            "width: 114px;\n" +
            "}\n" +
            "\n" +
            "#user-info-check{\n" +
            "float: left;\n" +
            "color: #ff0c0c;\n" +
            "margin: 0 0 0 20px;\n" +
            "<!--color: #ff9900;-->\n" +
            "}\n" +
            "\n" +
            "#order{\n" +
            "float: left;\n" +
            "}\n" +
            "\n" +
            "#order-form{\n" +
            "float: left;\n" +
            "font-size: 11px;\n" +
            "align: center;\n" +
            "width: 95px;\n" +
            "margin: 13px 0px 0px 40px;\n" +
            "}\n" +
            "\n" +
            "</style>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Shop selection</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<div id=\"header\">\n" +
            "    <div class=\"content\">\n" +
            "        <h2>Welcome to Sportmaster order API</h2>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"clear\"></div>\n" +
            "\n" +
            "<br>\n" +
            "<br>\n" +
            "<div class=\"content\">\n" +
            "    <div class=\"table\">\n" +
            "        <ul class=\"nav-tabs\">\n" +
            "            <li><a class=\"active\" href=\"#\">Shop list</a></li>\n" +
            "        </ul>\n" +
            "        <br>\n" +
            "\n" +
            "        <div class=\"tab-content\">\n" +
            "            <div class=\"table-header\">\n" +
            "                !!!separator\n" +
            "            </div>\n" +
            "            <br>\n" +
            "            <div class=\"table-content\">\n" +
            "                <table>\n" +
            "                    <tr class=\"tr-header\">\n" +
            "                        <th class=\"col-1\">Shop address</th>\n" +
            "                        <th class=\"col-2\">Metro</th>\n" +
            "                        <th class=\"col-3\">Action</th>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    !!!separator\n" +
            "                </table>\n" +
            "            </div>\n" +
            "\n" +
            "        </div>\n" +
            "\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}
