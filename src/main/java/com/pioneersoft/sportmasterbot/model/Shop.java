package com.pioneersoft.sportmasterbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Shop {

    @JsonProperty
    private String city;

    @JsonProperty
    private String address;

    @JsonProperty
    private String metroStation;
}
