package com.example.CarSalesMng.data;

import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.dto.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public interface CarRepository extends JpaRepository<Car,Integer> {
    //List<Car> findAll();
    //Car findById(int vin);
    //Car save(Car newCar);
    //Car updateCar(Car newCar);
    //public Collection<CustomerDTO> getAllCustomers();
    //public CustomerDTO getCustomer();
    //public CustomerDTO addCustomer();
    //public CustomerDTO updateCustomer();
    //public void SellCar(CarDTO carDTO);
    //public void ChangeCarStatus(String status, CarDTO carDTO);

}
