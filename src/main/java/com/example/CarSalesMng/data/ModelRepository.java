package com.example.CarSalesMng.data;

import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    List<Car> findCarByModel(Model model);
}
