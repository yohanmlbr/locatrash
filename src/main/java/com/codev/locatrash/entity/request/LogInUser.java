package com.codev.locatrash.entity.request;

import lombok.Data;

@Data
public class LogInUser {

    private String email;
    private String password;

    public LogInUser(String email, String password){
        this.email=email;
        this.password=password;
    }

    public LogInUser(SignUpUser suu){
        this.email=suu.getEmail();
        this.password=suu.getPassword();
    }

}
