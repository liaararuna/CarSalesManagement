package com.example.CarSalesMng.data;

import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Model;
import com.example.CarSalesMng.models.dto.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    //CarDTO updateCarStatus(int vin, CarStatus carStatus);

    @Query("select c from Car c where c.carStatus = :status")
    List<Car> findCarByCarStatus(CarStatus status);

    @Query("select c from Car c where c.idTransaction = :idTransaction")
    List<Car> findCarByIdTransaction(int idTransaction);

    @Query("select c from Car c where c.model = :model")
    List<Car> findCarByModel(Model model);

    //List<CarDTO> findCarByBuyerId(int id);

}
