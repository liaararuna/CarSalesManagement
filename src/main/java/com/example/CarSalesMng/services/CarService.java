package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.CarRepository;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.dto.CarDTO;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> getAllCars() {
        List<Car> carList = carRepository.findAll();

        List<CarDTO> carDTOList = new ArrayList<>();

        for(Car car : carList) {
            CarDTO carDTO = new CarDTO(car.getVin(),
                    car.getLicensePlate(),
                    car.getNumberOfDoors(),
                    car.getColor(),
                    car.getReleaseYear(),
                    car.getModel(),
                    car.getFuelType(),
                    car.getCarStatus(),
                    car.getSeller());
            carDTOList.add(carDTO);
        }

        return carDTOList;
    }

    public CarDTO getCarByVin(int vin) {
        Car otherCar = this.carRepository.findById(vin).orElse(null);

        CarDTO otherCarDTO = new CarDTO(otherCar.getVin(),
                otherCar.getLicensePlate(),
                otherCar.getNumberOfDoors(),
                otherCar.getColor(),
                otherCar.getReleaseYear(),
                otherCar.getModel(),
                otherCar.getFuelType(),
                otherCar.getCarStatus(),
                otherCar.getSeller());
        return otherCarDTO;
    }

    public CarDTO addCar(CarDTO carDTO) {
        Car newCar = this.carRepository.save(new Car(carDTO.getVin(),
                carDTO.getLicensePlate(),
                carDTO.getNumberOfDoors(),
                carDTO.getColor(),
                carDTO.getReleaseYear(),
                carDTO.getModel(),
                carDTO.getFuelType(),
                carDTO.getCarStatus(),
                carDTO.getSeller())
        );

        CarDTO newCarDTO = new CarDTO(newCar.getVin(),
                newCar.getLicensePlate(),
                newCar.getNumberOfDoors(),
                newCar.getColor(),
                newCar.getReleaseYear(),
                newCar.getModel(),
                newCar.getFuelType(),
                newCar.getCarStatus(),
                newCar.getSeller()
        );

        return newCarDTO;
    }


    public CarDTO update(CarDTO carDTO) {
        Car updatedCar = this.carRepository.save(new Car(
                carDTO.getVin(),
                carDTO.getLicensePlate(),
                carDTO.getNumberOfDoors(),
                carDTO.getColor(),
                carDTO.getReleaseYear(),
                carDTO.getModel(),
                carDTO.getFuelType(),
                carDTO.getCarStatus(),
                carDTO.getSeller()));

        return new CarDTO(
                updatedCar.getVin(),
                updatedCar.getLicensePlate(),
                updatedCar.getNumberOfDoors(),
                updatedCar.getColor(),
                updatedCar.getReleaseYear(),
                updatedCar.getModel(),
                updatedCar.getFuelType(),
                updatedCar.getCarStatus(),
                updatedCar.getSeller()
        );
    }
}
