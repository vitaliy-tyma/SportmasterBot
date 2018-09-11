package com.pioneersoft.sportmasterbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

    private String itemId;

    private String productId;

    private String itemBrand;

    private String itemName;

    private String color;

    private String size;

    private Integer price;

    private Integer initPrice;

    private String itemLink;

    public String toString() {
        return itemId + ( itemBrand!=null && !itemBrand.isEmpty() ? " - " + itemBrand : "") + " - " + itemName;
    }
}
