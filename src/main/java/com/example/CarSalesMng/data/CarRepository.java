package com.example.CarSalesMng.data;

import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Model;
import com.example.CarSalesMng.models.dto.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    //CarDTO updateCarStatus(int vin, CarStatus carStatus);

    List<CarDTO> findCarByCarStatus(CarStatus carStatus);

    List<CarDTO> findCarByIdTransaction(int idTransaction);

    List<CarDTO> findCarByModel(Model model);

    //List<CarDTO> findCarByBuyerId(int id);

}
