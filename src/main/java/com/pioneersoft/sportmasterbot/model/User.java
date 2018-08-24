package com.pioneersoft.sportmasterbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "users" )
public class User {

    @Id
    @Column( name = "login" )
    @JsonProperty
    private String login;

    @Column( name = "password" )
    @JsonProperty
    private String password;

    @Column( name = "first_name" )
    @JsonProperty
    private String firstName;

    @Column( name = "second_name" )
    @JsonProperty
    private String secondName;

}
