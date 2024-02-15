package com.example.CarSalesMng.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Brand {

    @Id
    private int id;
    private String name;

    public Brand (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand() {
    }
}
