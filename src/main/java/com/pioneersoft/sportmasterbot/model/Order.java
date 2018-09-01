package com.pioneersoft.sportmasterbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {

    @JsonProperty
    private Integer orderId;

    @JsonProperty
    private Integer amount;

    @JsonProperty
    private Long orderTime;

    @JsonProperty
    private Item item;

    @JsonProperty
    private User user;
}
