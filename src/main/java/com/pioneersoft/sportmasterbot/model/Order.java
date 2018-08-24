package com.pioneersoft.sportmasterbot.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "orders" )
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "order_id" )
    @JsonProperty
    private Integer orderId;

    @Column( name = "amount" )
    @JsonProperty
    private Integer amount;

    @Column( name = "time" )
    @JsonProperty
    private Long time;

    @ManyToOne(targetEntity = Item.class)
    private Item item;
}
