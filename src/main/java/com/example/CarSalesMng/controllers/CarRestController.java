package com.example.CarSalesMng.controllers;

import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.services.CarService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CarRestController {
    @Autowired
    private CarService carService;

    @GetMapping(value= "/cars", produces = "application/json")
    public CollectionModel<CarDTO> getAllCars() {
        List<CarDTO> carDTOList = carService.getAllCars();

        if(carDTOList == null) { throw new NotImplementedException(); }

        for(CarDTO carDTO : carDTOList) {
            carDTO.add(linkTo(methodOn(CarRestController.class).getCar(carDTO.getVin())).withSelfRel());
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllCars()).withSelfRel();
        CollectionModel<CarDTO> resp = CollectionModel.of(carDTOList, link);
        return resp;
    }

    @GetMapping(value = "/cars/{vin}", produces = "application/json")
    public ResponseEntity<CarDTO> getCar(@PathVariable("vin") int vin) {
        CarDTO carDTO = carService.getCarByVin(vin);

        if(carDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carDTO.add(linkTo(methodOn(CarRestController.class).getAllCars()).withSelfRel());

        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/car", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        if(carDTO == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        CarDTO resp = this.carService.addCar(carDTO);

        resp.add(linkTo(methodOn(CarRestController.class).getCar(carDTO.getVin())).withSelfRel());
        resp.add(linkTo(methodOn(CarRestController.class).getAllCars()).withRel("see_all_cars"));
        resp.add(linkTo(methodOn(CarRestController.class).updateCar(carDTO.getVin(), carDTO)).withRel("update"));

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    @PutMapping(value = "/cars/{vin}", consumes = "application/json")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("vin") int vin,
                            @RequestBody CarDTO carDTO) {

        if(carDTO.getVin() != vin) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        carDTO.setVin(vin);
        CarDTO carDTO2 = carService.update(carDTO);

        return new ResponseEntity<>(carDTO2, HttpStatus.OK);
    }

    @PutMapping(value = "/cars/{vin}", consumes = "application/json")
    public ResponseEntity<CarDTO> updateCarStatus(@PathVariable("vin") int vin,
                                                  @RequestParam("carStatus") CarStatus carStatus) {
        CarDTO otherCarDTO = this.carService.getCarByVin(vin);

        if(otherCarDTO == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        if (carStatus == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CarDTO updatedCarStatus = this.carService.updateCarStatus(vin, carStatus);

        if(updatedCarStatus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCarStatus, HttpStatus.OK);
    }
}
