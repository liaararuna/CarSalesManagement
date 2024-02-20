package com.example.CarSalesMng.models.dto;

import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Seller;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class SellerDTO extends RepresentationModel<SellerDTO> {
    private int id;
    private String name;
    private String nif;
    private String address;
    private String phoneNumber;
    private List<Car> carList;

    public SellerDTO(int id, String name, String nif, String address, String phoneNumber, List<Car> carList) {
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.carList = new ArrayList<>(carList);
    }

    public SellerDTO(int id, String name, String nif, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.carList = new ArrayList<>();
    }

    public SellerDTO(Seller seller) {
        this.id = seller.getId();
        this.name = seller.getName();
        this.nif = seller.getNif();
        this.address = seller.getAddress();
        this.phoneNumber = seller.getPhoneNumber();
        this.carList = new ArrayList<>(seller.getCarList());
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

    public List<Car> getCarList() {
        return new ArrayList<>(this.carList);
    }

    public void setCarList(List<Car> carLisT) {
        this.carList = new ArrayList<>(carList);
    }

    public static SellerDTO toDTO(Seller seller) {
        return new SellerDTO(
                seller.getId(),
                seller.getName(),
                seller.getNif(),
                seller.getAddress(),
                seller.getPhoneNumber(),
                new ArrayList<>(seller.getCarList())
        );
    }
}
