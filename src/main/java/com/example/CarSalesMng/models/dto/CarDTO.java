package com.example.CarSalesMng.models.dto;

import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Model;
import com.example.CarSalesMng.models.Seller;
import org.springframework.hateoas.RepresentationModel;

public class CarDTO extends RepresentationModel<CarDTO> {
    private int vin;
    private String licensePlate;
    private int numberOfDoors;
    private String color;
    private int releaseYear;
    private Model model;
    private String fuelType;
    private CarStatus carStatus;
    private Seller seller;

    public CarDTO(int vin, String licensePlate, int numberOfDoors, String color, int releaseYear, Model model, String fuelType, CarStatus status, Seller seller) {
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

    public CarDTO(Car car) {
        this.vin = car.getVin();
        this.licensePlate = car.getLicensePlate();
        this.numberOfDoors = car.getNumberOfDoors();
        this.color = car.getColor();
        this.releaseYear = car.getReleaseYear();
        this.model = car.getModel();
        this.fuelType = car.getFuelType();
        this.carStatus = car.getCarStatus();
        this.seller = car.getSeller();
    }

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

    public void setModel(Model model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
