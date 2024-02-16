package com.example.CarSalesMng.data;

import com.example.CarSalesMng.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {


}
