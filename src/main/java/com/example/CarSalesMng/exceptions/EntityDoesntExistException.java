package com.example.CarSalesMng.exceptions;

public class EntityDoesntExistException extends Exception {
    private String classOrigin;
    private String methodOrigin;

    public EntityDoesntExistException(String classOrigin, String methodOrigin)
    {
        this.classOrigin = classOrigin;
        this.methodOrigin = methodOrigin;
    }

    @Override
    public String getMessage() {
        return "The entity that you are trying to find doesn't exist (origin class: " + this.classOrigin + ", origin method: " + this.methodOrigin + ").";
    }

}
