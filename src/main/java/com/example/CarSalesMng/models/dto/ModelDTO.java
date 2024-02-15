package com.example.CarSalesMng.models.dto;

import org.springframework.hateoas.RepresentationModel;

public class ModelDTO extends RepresentationModel<ModelDTO> {
    private int id;
    private String name;
    private int brandId;

    public ModelDTO(int id, String name, int brandId) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
    }

    public ModelDTO() { }

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
