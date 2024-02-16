package com.example.CarSalesMng.models.dto;

import org.springframework.hateoas.RepresentationModel;

public class SellerDTO extends RepresentationModel<SellerDTO> {
    private int id;
    private String name;
    private String nif;
    private String address;
    private String phoneNumber;

    public SellerDTO(int id, String name, String nif, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public SellerDTO() { }

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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
