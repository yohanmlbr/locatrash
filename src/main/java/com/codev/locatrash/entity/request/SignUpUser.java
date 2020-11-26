package com.codev.locatrash.entity.request;

import lombok.Data;

@Data
public class SignUpUser {

    private String email;
    private String password;
    private String name;
    private String surname;
}
