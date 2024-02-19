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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
