package com.cts.cda.models;

import lombok.Data;

@Data
public class LoginModel {
    private String usernameOrEmail;
    private String password;
}
