package com.example.CarSalesMng.controllers;

import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.exceptions.EntityAlreadyExistsException;
import com.example.CarSalesMng.exceptions.EntityDoesntExistException;
import com.example.CarSalesMng.exceptions.TableIsEmptyException;
import com.example.CarSalesMng.models.Model;
import com.example.CarSalesMng.models.dto.BrandDTO;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.models.dto.ModelDTO;
import com.example.CarSalesMng.services.CarService;
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
        List<CarDTO> carDTOList = this.carService.getAllCars();

        for(CarDTO carDTO : carDTOList) {
            carDTO.add(linkTo(methodOn(CarRestController.class).getCar(carDTO.getVin())).withSelfRel());
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllCars()).withSelfRel();
        CollectionModel<CarDTO> resp = CollectionModel.of(carDTOList, link);
        return resp;
    }

    @GetMapping(value = "/cars/{vin}", produces = "application/json")
    public ResponseEntity<CarDTO> getCar(@PathVariable("vin") int vin) {
        CarDTO carDTO = this.carService.getCarByVin(vin);

        carDTO.add(linkTo(methodOn(CarRestController.class).getAllCars()).withSelfRel());
        carDTO.add(linkTo(methodOn(CarRestController.class).getCar(vin)).withSelfRel());

        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/{status}", consumes = "application/json", produces = "application/json")
    public List<CarDTO> findCarByStatus(@PathVariable("status") CarStatus carStatus) {
        return this.carService.findCarByStatus(carStatus);
    }

    @GetMapping(value = "/cars/{model}", consumes = "application/json", produces = "application/json")
    public List<CarDTO> findCarByModel(@PathVariable("model") Model model) {
        return this.carService.findCarByModel(model);
    }

    @GetMapping(value = "/cars/buyer/{buyerId}", consumes = "application/json", produces = "application/json")
    public List<CarDTO> findCarByBuyerId(@PathVariable("id") int buyerId) {
        return this.carService.findCarByBuyerId(buyerId);
    }

    @PostMapping(value = "/car", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        if(carDTO == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        CarDTO newCarDTO = this.carService.addCar(carDTO);

        newCarDTO.add(linkTo(methodOn(CarRestController.class).getCar(carDTO.getVin())).withSelfRel());
        newCarDTO.add(linkTo(methodOn(CarRestController.class).getAllCars()).withRel("see_all_cars"));
        newCarDTO.add(linkTo(methodOn(CarRestController.class).updateCar(carDTO.getVin(), carDTO)).withRel("update"));

        return new ResponseEntity<>(newCarDTO, HttpStatus.OK);
    }


    @PutMapping(value = "/cars/{vin}", consumes = "application/json")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("vin") int vin,
                            @RequestBody CarDTO carDTO) {

        carDTO.setVin(vin);
        CarDTO updatedCarDTO = this.carService.updateCar(carDTO);

        return new ResponseEntity<>(updatedCarDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/model/{id}", produces = "application/json")
    public ResponseEntity<ModelDTO> getModel(@PathVariable("id") int id) {
        ModelDTO modelDTO = this.carService.getModel(id);

        modelDTO.add(linkTo(methodOn(CarRestController.class).getAllModels()).withSelfRel());

        return new ResponseEntity<>(modelDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/model", produces = "application/json")
    public CollectionModel<ModelDTO> getAllModels()  {
        List<ModelDTO> modelDTOList = this.carService.getAllModels();

        for(ModelDTO modelDTO : modelDTOList) {
            modelDTO.add(linkTo(methodOn(CarRestController.class).getModel(modelDTO.getId())).withSelfRel());
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllModels()).withSelfRel();
        CollectionModel<ModelDTO> resp = CollectionModel.of(modelDTOList, link);

        return resp;
    }

    @PostMapping(value = "/car/model", produces = "application/json")
    public ResponseEntity<ModelDTO> addModel(@RequestBody ModelDTO modelDTO) {
        ModelDTO modelDTO1 = this.carService.addModel(modelDTO);

        modelDTO1.add(linkTo(methodOn(CarRestController.class).getModel(modelDTO.getId())).withSelfRel());
        modelDTO1.add(linkTo(methodOn(CarRestController.class).getAllModels()).withRel("see_all_models"));
        modelDTO1.add(linkTo(methodOn(CarRestController.class).updateModel(modelDTO.getId(),modelDTO)).withRel("update"));
        modelDTO1.add(linkTo(methodOn(CarRestController.class).deleteModel(modelDTO.getId())).withRel("delete"));

        return new ResponseEntity<>(modelDTO1, HttpStatus.OK) ;
    }

    @PutMapping(value = "/car/model/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ModelDTO> updateModel(@PathVariable("id") int id,
                                                @RequestBody ModelDTO modelDTO) {
        modelDTO.setId(id);
        ModelDTO updatedModelDTO = this.carService.updateModel(modelDTO);

        return new ResponseEntity<>(updatedModelDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/car/model/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ModelDTO> deleteModel(@PathVariable("id") int id) {
        ModelDTO modelDTO = this.carService.getModel(id);

        this.carService.deleteModel(modelDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/cars/brand/{id}", produces = "application/json")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable("id") int id) {
        BrandDTO brandDTO = this.carService.getBrandById(id);

        brandDTO.add(linkTo(methodOn(CarRestController.class).getAllBrands()).withSelfRel());

        return new ResponseEntity<>(brandDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/brands", produces = "application/json")
    public CollectionModel<BrandDTO> getAllBrands() {
        List<BrandDTO> brandDTOList = this.carService.getAllBrands();

        for(BrandDTO brandDTO : brandDTOList) {
            brandDTO.add(linkTo(methodOn(CarRestController.class).getBrand(brandDTO.getId())).withSelfRel());
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllBrands()).withSelfRel();
        CollectionModel<BrandDTO> resp = CollectionModel.of(brandDTOList, link);

        return resp;
    }

    @PostMapping(value = "/car/brand", produces = "application/json")
    public ResponseEntity<BrandDTO> addBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO addedBrand = this.carService.addBrand(brandDTO);

        addedBrand.add(linkTo(methodOn(CarRestController.class).getBrand(brandDTO.getId())).withSelfRel());
        addedBrand.add(linkTo(methodOn(CarRestController.class).getAllBrands()).withRel("brands"));
        addedBrand.add(linkTo(methodOn(CarRestController.class).updateBrand(brandDTO.getId(),brandDTO)).withRel("update"));
        addedBrand.add(linkTo(methodOn(CarRestController.class).deleteBrand(brandDTO.getId())).withRel("delete"));

        return new ResponseEntity<>(addedBrand, HttpStatus.OK) ;
    }

    @PutMapping(value = "/car/brand/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable("id") int id,
                                                @RequestBody BrandDTO brandDTO) {
        brandDTO.setId(id);
        BrandDTO updatedBrandDTO = this.carService.updateBrand(brandDTO);

        return new ResponseEntity<>(updatedBrandDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/car/brand/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<BrandDTO> deleteBrand(@PathVariable("id") int id) {
        BrandDTO brandDTO = this.carService.getBrandById(id);

        if(brandDTO == null) {
            return new ResponseEntity<>(brandDTO, HttpStatus.NOT_FOUND);
        }
        this.carService.deleteBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
