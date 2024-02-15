package com.example.CarSalesMng.models.dto;


import org.springframework.hateoas.RepresentationModel;

public class CustomerDTO extends RepresentationModel<CustomerDTO> {
    private int id;
    private String name;

    public CustomerDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
