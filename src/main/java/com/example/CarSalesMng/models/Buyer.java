package com.example.CarSalesMng.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class Buyer {
    private int id;
    private String name;

    public Buyer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
