package com.example.CarSalesMng.data;

import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.dto.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {

    CarDTO updateCarStatus(int vin, CarStatus carStatus);
}
