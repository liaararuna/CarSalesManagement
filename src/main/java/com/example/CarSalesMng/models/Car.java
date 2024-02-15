package com.example.CarSalesMng.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    private int vin;
    private String licensePlate;
    private int numberOfDoors;
    private String color;
    private int releaseYear;
    private int modelId;
    private String fuelType;
    private String status;

    public Car(int vin, String licensePlate, int numberOfDoors, String color, int releaseYear, int modelId, String fuelType, String status) {
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.numberOfDoors = numberOfDoors;
        this.color = color;
        this.releaseYear = releaseYear;
        this.modelId = modelId;
        this.fuelType = fuelType;
        this.status = status;
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

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}