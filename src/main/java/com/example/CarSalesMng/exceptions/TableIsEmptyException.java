package com.example.CarSalesMng.exceptions;

public class TableIsEmptyException extends Exception {
    private String classOrigin;
    private String methodOrigin;

    public TableIsEmptyException(String classOrigin, String methodOrigin)
    {
        this.classOrigin = classOrigin;
        this.methodOrigin = methodOrigin;
    }

    @Override
    public String getMessage() {
        return "There's no data in the table (origin class: " + this.classOrigin + ", origin method: " + this.methodOrigin + ").";
    }

}
