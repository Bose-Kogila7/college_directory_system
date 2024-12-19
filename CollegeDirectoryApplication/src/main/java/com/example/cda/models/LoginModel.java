package com.example.cda.models;

import lombok.Data;

@Data
public class LoginModel {
    private String usernameOrEmail;
    private String password;
}
