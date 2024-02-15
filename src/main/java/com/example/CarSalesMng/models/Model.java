package com.example.CarSalesMng.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Model {
    @Id
    private int id;
    private String name;
    private int brandId;

    public Model(int id, String name, int brandId) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
