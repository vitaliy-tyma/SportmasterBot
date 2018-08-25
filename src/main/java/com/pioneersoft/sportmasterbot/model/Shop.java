package com.pioneersoft.sportmasterbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table( name = "shops" )
public class Shop {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "shop_id" )
    @JsonProperty
    private Integer orderId;

    @Column( name = "city" )
    @JsonProperty
    private String city;

    @Column( name = "address" )
    @JsonProperty
    private String address;

    @Column( name = "metro_station" )
    @JsonProperty
    private String metroStation;
}
