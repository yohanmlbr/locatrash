package com.codev.locatrash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public RessourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Can't find ressource %s whose %s 's value is '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}