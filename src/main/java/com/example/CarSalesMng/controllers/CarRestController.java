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
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CarRestController {
    @Autowired
    private CarService carService;

    @GetMapping(value= "/cars", produces = "application/json")
    public CollectionModel<CarDTO> getAllCars(@RequestParam Optional<Integer> page,
                                              @RequestParam Optional<Integer> size) {
        int _page = page.orElse(0);
        int _size = size.orElse(10);

        Page<CarDTO> carDTOList = this.carService.getAllCars(_page, _size);

        for(CarDTO carDTO : carDTOList) {
            carDTO.add(linkTo(methodOn(CarRestController.class).getCar(carDTO.getVin())).withSelfRel());
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllCars(Optional.of(_page),Optional.of(_size))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!carDTOList.isLast()) {
            Link _link = linkTo(methodOn(CarRestController.class).getAllCars(Optional.of(_page + 1), size)).withRel("next");
            links.add(_link);
        }
        if(!carDTOList.isFirst()) {
            Link _link = linkTo(methodOn(CarRestController.class).getAllCars(Optional.of(_page - 1), size)).withRel("previous");
            links.add(_link);
        }

        return CollectionModel.of(carDTOList, link);
    }

    @GetMapping(value = "/cars/{vin}", produces = "application/json")
    public ResponseEntity<CarDTO> getCar(@PathVariable("vin") int vin) {
        CarDTO carDTO = this.carService.getCarByVin(vin);

        carDTO.add(linkTo(methodOn(CarRestController.class).getAllCars(Optional.of(1),Optional.of(10))).withSelfRel());
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

    /*
    @GetMapping(value = "/cars/buyer/{buyerId}", consumes = "application/json", produces = "application/json")
    public List<CarDTO> findCarByBuyerId(@PathVariable("id") int buyerId) {
        return this.carService.findCarByBuyerId(buyerId);
    }*/

    @PostMapping(value = "/car", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        if(carDTO == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        CarDTO newCarDTO = this.carService.addCar(carDTO);

        newCarDTO.add(linkTo(methodOn(CarRestController.class).getCar(carDTO.getVin())).withSelfRel());
        newCarDTO.add(linkTo(methodOn(CarRestController.class).getAllCars(Optional.of(1),Optional.of(10))).withRel("see_all_cars"));
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

        modelDTO.add(linkTo(methodOn(CarRestController.class).getAllModels(Optional.of(1),Optional.of(10))).withSelfRel());

        return new ResponseEntity<>(modelDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/model", produces = "application/json")
    public CollectionModel<ModelDTO> getAllModels(@RequestParam Optional<Integer> page,
                                                  @RequestParam Optional<Integer> size)  {
        int _page = page.orElse(0);
        int _size = size.orElse(10);

        Page<ModelDTO> modelDTOPage = this.carService.getAllModels(_page, _size);

        for(ModelDTO modelDTO : modelDTOPage) {
            modelDTO.add(linkTo(methodOn(CarRestController.class).getModel(modelDTO.getId())).withSelfRel());
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllModels(Optional.of(_page),Optional.of(_size))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!modelDTOPage.isLast()) {
            Link _link = linkTo(methodOn(CarRestController.class).getAllModels(Optional.of(_page + 1), size)).withRel("next");
            links.add(_link);
        }
        if(!modelDTOPage.isFirst()) {
            Link _link = linkTo(methodOn(CarRestController.class).getAllModels(Optional.of(_page - 1), size)).withRel("previous");
            links.add(_link);
        }

        return CollectionModel.of(modelDTOPage, link);
    }

    @PostMapping(value = "/car/model", produces = "application/json")
    public ResponseEntity<ModelDTO> addModel(@RequestBody ModelDTO modelDTO) {
        ModelDTO modelDTO1 = this.carService.addModel(modelDTO);

        modelDTO1.add(linkTo(methodOn(CarRestController.class).getModel(modelDTO.getId())).withSelfRel());
        modelDTO1.add(linkTo(methodOn(CarRestController.class).getAllModels(Optional.of(1),Optional.of(10))).withRel("see_all_models"));
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

        brandDTO.add(linkTo(methodOn(CarRestController.class).getAllBrands(Optional.of(1),Optional.of(10))).withSelfRel());

        return new ResponseEntity<>(brandDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/brands", produces = "application/json")
    public CollectionModel<BrandDTO> getAllBrands(@RequestParam Optional<Integer> page,
                                                  @RequestParam Optional<Integer> size) {
        int _page = page.orElse(0);
        int _size = size.orElse(10);

        Page<BrandDTO> brandDTOPage = this.carService.getAllBrands(_page, _size);

        for(BrandDTO brandDTO : brandDTOPage) {
            brandDTO.add(linkTo(methodOn(CarRestController.class).getBrand(brandDTO.getId())).withSelfRel());
        }

        Link link = linkTo(methodOn(CarRestController.class).getAllBrands(Optional.of(_page),Optional.of(_size))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!brandDTOPage.isLast()) {
            Link _link = linkTo(methodOn(CarRestController.class).getAllBrands(Optional.of(_page + 1), size)).withRel("next");
            links.add(_link);
        }
        if(!brandDTOPage.isFirst()) {
            Link _link = linkTo(methodOn(CarRestController.class).getAllBrands(Optional.of(_page - 1), size)).withRel("previous");
            links.add(_link);
        }

        return CollectionModel.of(brandDTOPage, link);
    }

    @PostMapping(value = "/car/brand", produces = "application/json")
    public ResponseEntity<BrandDTO> addBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO addedBrand = this.carService.addBrand(brandDTO);

        addedBrand.add(linkTo(methodOn(CarRestController.class).getBrand(brandDTO.getId())).withSelfRel());
        addedBrand.add(linkTo(methodOn(CarRestController.class).getAllBrands(Optional.of(1),Optional.of(10))).withRel("brands"));
        addedBrand.add(linkTo(methodOn(CarRestController.class).updateBrand(brandDTO.getId(),brandDTO)).withRel("update_brand"));
        addedBrand.add(linkTo(methodOn(CarRestController.class).deleteBrand(brandDTO.getId())).withRel("delete_brand"));

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
