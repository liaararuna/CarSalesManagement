package com.example.CarSalesMng.exceptions;

public class EntityAlreadyExistsException extends Exception {
    private String classOrigin;
    private String methodOrigin;

    public EntityAlreadyExistsException(String classOrigin, String methodOrigin)
    {
        this.classOrigin = classOrigin;
        this.methodOrigin = methodOrigin;
    }
    @Override
    public String getMessage() {
        return "The entity that you are trying to create already exists (origin class: " + this.classOrigin + ", origin method: " + this.methodOrigin + ").";
    }
}
