package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.CarRepository;
import com.example.CarSalesMng.models.Car;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarByVin(int vin) {
        return this.carRepository.findById(vin).orElse(null);
    }

    public Car addCar(Car car) {
        if(car == null) {
            throw new NotImplementedException();
        }
        return this.carRepository.save(car);
    }


    public Car update(Car newCar) {
        if(newCar == null) {
            throw new NotImplementedException();
        }
        return this.carRepository.save(newCar);
    }
}
