package com.example.CarSalesMng.models;

import jakarta.persistence.Entity;

@Entity
public class Brand {

    private int id;
    private String name;

    public Brand (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand() {
    }
}
