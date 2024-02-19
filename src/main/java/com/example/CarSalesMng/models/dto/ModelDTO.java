package com.example.CarSalesMng.models.dto;

import com.example.CarSalesMng.models.Brand;
import com.example.CarSalesMng.models.Model;
import org.springframework.hateoas.RepresentationModel;

public class ModelDTO extends RepresentationModel<ModelDTO> {
    private int id;
    private String name;
    private Brand brand;

    public ModelDTO(int id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public ModelDTO(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.brand = model.getBrand();
    }

    public ModelDTO() {}

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

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
