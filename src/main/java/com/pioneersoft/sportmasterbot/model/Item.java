package com.pioneersoft.sportmasterbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "items" )
public class Item {

    @Id
    @Column( name = "item_id" )
    @JsonProperty
    private String itemId;

    @Column( name = "item_brand" )
    @JsonProperty
    private String itemBrand;

    @Column( name = "item_name" )
    @JsonProperty
    private String itemName;

    @Column( name = "color" )
    @JsonProperty
    private String color;

    @Column( name = "size" )
    @JsonProperty
    private String size;

    @Column( name = "price" )
    @JsonProperty
    private Integer price;

    @Column( name = "init_price" )
    @JsonProperty
    private Integer initPrice;

    @Column( name = "avialability" )
    @JsonProperty
    private Boolean avialability;

    @Column( name = "item_link" )
    @JsonProperty
    private String itemLink;
}
