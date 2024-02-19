package com.example.CarSalesMng.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Model {
    @Id
    private int id;
    private String name;
    @ManyToOne
    private Brand brand;

    public Model(int id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public Model() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrandId(Brand brand) {
        this.brand = brand;
    }
}
