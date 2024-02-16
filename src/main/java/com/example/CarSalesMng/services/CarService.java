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
                    car.getModelId(),
                    car.getFuelType(),
                    car.getCarStatus());
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
                otherCar.getModelId(),
                otherCar.getFuelType(),
                otherCar.getCarStatus());
        return otherCarDTO;
    }

    public CarDTO addCar(CarDTO carDTO) {
        Car newCar = this.carRepository.save(new Car(carDTO.getVin(),
                carDTO.getLicensePlate(),
                carDTO.getNumberOfDoors(),
                carDTO.getColor(),
                carDTO.getReleaseYear(),
                carDTO.getModelId(),
                carDTO.getFuelType(),
                carDTO.getCarStatus())
        );

        CarDTO newCarDTO = new CarDTO(newCar.getVin(),
                newCar.getLicensePlate(),
                newCar.getNumberOfDoors(),
                newCar.getColor(),
                newCar.getModelId(),
                newCar.getReleaseYear(),
                newCar.getFuelType(),
                newCar.getCarStatus()
        );

        return newCarDTO;
    }


    public CarDTO update(CarDTO carDTO) {
        Car updatedCar = this.carRepository.save(new Car(carDTO.getVin(), carDTO.getLicensePlate(), carDTO.getNumberOfDoors(), carDTO.getColor(), carDTO.getReleaseYear(), carDTO.getModelId(), carDTO.getFuelType(), carDTO.getCarStatus()));
        CarDTO newCarDTO = new CarDTO(updatedCar.getVin(), updatedCar.getLicensePlate(), updatedCar.getNumberOfDoors(), updatedCar.getColor(), updatedCar.getModelId(), updatedCar.getReleaseYear(), updatedCar.getFuelType(), updatedCar.getCarStatus());
        return newCarDTO;
    }
}
