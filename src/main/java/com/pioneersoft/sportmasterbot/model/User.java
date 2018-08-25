package com.pioneersoft.sportmasterbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Column( name = "email" )
    @JsonProperty
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
