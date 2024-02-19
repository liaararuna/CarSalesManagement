package com.example.CarSalesMng.models.dto;

import com.example.CarSalesMng.data.BrandRepository;
import com.example.CarSalesMng.models.Brand;
import com.example.CarSalesMng.models.Model;
import org.springframework.hateoas.RepresentationModel;

public class BrandDTO extends RepresentationModel<BrandDTO> {
    private int id;
    private String name;

    public BrandDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BrandDTO(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }

    public BrandDTO() {}

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

}
