package com.pioneersoft.sportmasterbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

    @JsonProperty
    private String itemId;

    @JsonProperty
    private String itemBrand;

    @JsonProperty
    private String itemName;

    @JsonProperty
    private String color;

    @JsonProperty
    private String size;

    @JsonProperty
    private Integer price;

    @JsonProperty
    private Integer initPrice;

    @JsonProperty
    private String itemLink;
}
