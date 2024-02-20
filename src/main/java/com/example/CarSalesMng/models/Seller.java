package com.example.CarSalesMng.models;

import com.example.CarSalesMng.models.dto.SellerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller {
    @Id
    private int id;
    private String name;
    private String nif;
    private String address;
    private String phoneNumber;

    @OneToMany
    private List<Car> carList;

    public Seller(int id, String name, String nif, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.carList = new ArrayList<>();
    }

    public Seller(int id, String name, String nif, String address, String phoneNumber, List<Car> carList) {
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.carList = new ArrayList<>(carList);
    }

    public Seller() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return this.name; }

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

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
