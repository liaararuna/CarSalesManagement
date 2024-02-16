package com.example.CarSalesMng.controllers;

import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.data.CarRepository;
import com.example.CarSalesMng.services.CarService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CarRestController {
    @Autowired
    private CarService carService;

    @GetMapping(value= "/cars", produces = "application/json")
    public CollectionModel<CarDTO> getAllCars() {
        List<Car> carList = carService.getAllCars();

        List<CarDTO> carDTOList = new ArrayList<>();
        for(Car car : carList) {
            CarDTO carDTO = new CarDTO(car.getVin(), car.getLicensePlate(), car.getNumberOfDoors(), car.getColor(), car.getReleaseYear(), car.getModelId(), car.getFuelType(), car.getStatus());
            carDTO.add(linkTo(methodOn(CarRestController.class).getCar(carDTO.getVin())).withSelfRel());
            carDTOList.add(carDTO);
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllCars()).withSelfRel();
        CollectionModel<CarDTO> resp = CollectionModel.of(carDTOList, link);
        return resp;
    }

    @GetMapping(value = "/cars/{vin}", produces = "application/json")
    public ResponseEntity<CarDTO> getCar(@PathVariable("vin") int vin) {
        Car car = carService.getCarByVin(vin);

        if(car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CarDTO carDTO = new CarDTO(car.getVin(), car.getLicensePlate(), car.getNumberOfDoors(), car.getColor(), car.getReleaseYear(), car.getModelId(), car.getFuelType(), car.getStatus());
        carDTO.add(linkTo(methodOn(CarRestController.class).getAllCars()).withSelfRel());

        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/car", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        //Salvar num novo objeto Car usando os parâmetros do carDTO enviado como parâmetro.
        Car newCar =  carService.addCar(new Car(carDTO.getVin(), carDTO.getLicensePlate(), carDTO.getNumberOfDoors(), carDTO.getColor(), carDTO.getReleaseYear(), carDTO.getModelId(), carDTO.getFuelType(), carDTO.getStatus()));
        CarDTO resp = new CarDTO(newCar.getVin(), newCar.getLicensePlate(), newCar.getNumberOfDoors(), newCar.getColor(), newCar.getReleaseYear(), newCar.getModelId(), newCar.getFuelType(), newCar.getStatus());
        resp.add(linkTo(methodOn(CarRestController.class).getCar(newCar.getVin())).withSelfRel());
        resp.add(linkTo(methodOn(CarRestController.class).getAllCars()).withRel("see_all_cars"));
        //resp.add(linkTo(methodOn(CarRestController.class).updateCar(newCar.getVin(), carDTO)).withRel("update"));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    @PutMapping(value = "/cars/{vin}", consumes = "application/json")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("vin") int vin,
                            @RequestBody CarDTO carDTO) {
        if(carDTO.getVin() != vin) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        carDTO.setVin(vin);
        Car updatedCar = carService.update(new Car(carDTO.getVin(), carDTO.getLicensePlate(), carDTO.getNumberOfDoors(), carDTO.getColor(), carDTO.getReleaseYear(), carDTO.getModelId(), carDTO.getFuelType(), carDTO.getStatus()));
        CarDTO carDTO2 = new CarDTO(updatedCar.getVin(), updatedCar.getLicensePlate(), updatedCar.getNumberOfDoors(), updatedCar.getColor(), updatedCar.getReleaseYear(), updatedCar.getModelId(), updatedCar.getFuelType(), updatedCar.getStatus());

        return new ResponseEntity<>(carDTO2, HttpStatus.OK);
    }
}
