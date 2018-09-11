package com.pioneersoft.sportmasterbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Shop {

    private String shopId;

    private String address;

    private String metroStation;

    public String toString() {
        return shopId + " - " + address + ( metroStation!=null && !metroStation.isEmpty() ? " - Metro: " + metroStation : "");
    }
}
