package com.pioneersoft.sportmasterbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {

    private String orderId;

    private String address;

    private String metro;

    private Integer amount;

    private Long orderTime;

    private Item item;

    private User user;
}
