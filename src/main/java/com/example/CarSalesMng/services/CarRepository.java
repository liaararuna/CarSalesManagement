package com.example.CarSalesMng.services;

import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.models.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CarRepository extends JpaRepository<Car,Integer> {
    public Collection<CarDTO> getAllCars();
    public CarDTO getCar();
    public CarDTO addCar();
    public CarDTO updateCar();
    public Collection<CustomerDTO> getAllCustomers();
    public CustomerDTO getCustomer();
    public CustomerDTO addCustomer();
    public CustomerDTO updateCustomer();
    public void SellCar(CarDTO carDTO);
    public void ChangeCarStatus(String status, CarDTO carDTO);

}
