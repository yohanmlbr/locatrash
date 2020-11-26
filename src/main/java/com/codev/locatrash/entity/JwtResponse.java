package com.codev.locatrash.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String name;
    private final long id;


    public JwtResponse(String jwttoken, long id, String name) {
        this.jwttoken = jwttoken;
        this.name = name;
        this.id = id;
    }

    public String getToken() {
        return this.jwttoken;
    }
}