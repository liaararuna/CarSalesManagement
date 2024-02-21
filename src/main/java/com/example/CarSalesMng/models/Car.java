package com.example.CarSalesMng.models;

import com.example.CarSalesMng.models.dto.CarDTO;
import jakarta.persistence.*;
import com.example.CarSalesMng.enums.CarStatus;

import java.util.List;

@Entity
public class Car {
    @Id
    private int vin;
    private String licensePlate;
    private int numberOfDoors;
    private String color;
    private int releaseYear;
    @ManyToOne
    private Model model;
    private String fuelType;

    @Enumerated(EnumType.STRING) //- Para fazer com que o enum seja salvo com string.
    private CarStatus carStatus;
    @ManyToOne
    private Seller seller;

    //TODO: Incluir posteriormente com .NET.
    //private Buyer buyer;
    private int idTransaction;

    public Car(int vin, String licensePlate, int numberOfDoors, String color, int releaseYear, Model model, String fuelType, CarStatus status, Seller seller) {
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.numberOfDoors = numberOfDoors;
        this.color = color;
        this.releaseYear = releaseYear;
        this.model = model;
        this.fuelType = fuelType;
        this.carStatus = status;
        this.seller = seller;
    }

    public Car() { }

    public int getVin() {
        return vin;
    }
    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Model getModel() {
        return this.model;
    }

    public void setModelId(Model model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public CarStatus getCarStatus() {
        return this.carStatus;
    }

    public void setCarStatus(CarStatus status) {
        this.carStatus = status;
    }

    public Seller getSeller() { return this.seller; }


}
