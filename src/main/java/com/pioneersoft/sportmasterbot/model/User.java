package com.pioneersoft.sportmasterbot.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String login;

    private String password;

    private String name;

    private String phone;

    private String email;
}
