package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.BrandRepository;
import com.example.CarSalesMng.data.CarRepository;
import com.example.CarSalesMng.data.ModelRepository;
import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.exceptions.EntityAlreadyExistsException;
import com.example.CarSalesMng.exceptions.EntityDoesntExistException;
import com.example.CarSalesMng.exceptions.TableIsEmptyException;
import com.example.CarSalesMng.models.Brand;
import com.example.CarSalesMng.models.Buyer;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Model;
import com.example.CarSalesMng.models.dto.BrandDTO;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.models.dto.ModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService{
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<CarDTO> getAllCars() {
        List<Car> carList = this.carRepository.findAll();

        if(carList == null) {
            //TODO: throw new TableIsEmptyException();
            throw new IllegalArgumentException("TableIsEmptyException");
        }

        List<CarDTO> carDTOList = new ArrayList<>();

        for(Car car : carList) {
            carDTOList.add(new CarDTO(car));
        }

        return carDTOList;
    }

    public CarDTO getCarByVin(int vin) {
        Car otherCar = this.carRepository.findById(vin).orElse(null);

        if(otherCar == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        return new CarDTO(otherCar);
    }

    public CarDTO addCar(CarDTO carDTO) {
        //Validação se o carro já existe no banco de dados.
        Car car = this.carRepository.findById(carDTO.getVin()).orElse(null);

        if(car != null) {
            //TODO: throw new EntityAlreadyExistsException();
            throw new IllegalArgumentException("EntityAlreadyExistsException");
        }

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

        return new CarDTO(newCar);
    }


    public CarDTO updateCar(CarDTO carDTO) {
        Car updatedCar = this.carRepository.save(new Car(
                carDTO.getVin(),
                carDTO.getLicensePlate(),
                carDTO.getNumberOfDoors(),
                carDTO.getColor(),
                carDTO.getReleaseYear(),
                carDTO.getModel(),
                carDTO.getFuelType(),
                carDTO.getCarStatus(),
                carDTO.getSeller())
        );

        return new CarDTO(updatedCar);
    }

    /*
    public CarDTO updateCarStatus(int vin, CarStatus carStatus) {
        Car car = this.carRepository.findById(vin).orElse(null);

        car.setCarStatus(carStatus);

        Car otherCar = this.carRepository.save(car);

        CarDTO carDTO = new CarDTO(otherCar);

        return carDTO;
    }*/

    public List<ModelDTO> getAllModels()  {
        List<Model> modelList = this.modelRepository.findAll();

        if(modelList == null) {
            //TODO: throw new TableIsEmptyException("Model", "getAllModels()");
            throw new IllegalArgumentException("TableIsEmptyException");
        }

        List<ModelDTO> modelDTOList = new ArrayList<>();

        for(Model model : modelList) {
            modelDTOList.add(new ModelDTO(model));
        }

        return modelDTOList;
    }

    public ModelDTO getModel(int id) {
        Model model = this.modelRepository.findById(id).orElse(null);

        if(model == null) {
            //TODO: throw new EntityDoesntExistException("Model", "getModel");
            throw new IllegalArgumentException();
        }

        return new ModelDTO(model);
    }

    public ModelDTO addModel(ModelDTO modelDTO) {
        if(modelDTO == null) {
            throw new IllegalArgumentException("Invalid data.");
        }

        Model model = this.modelRepository.findById(modelDTO.getId()).orElse(null);

        if(model != null) {
            //TODO: throw new EntityAlreadyExistsException("Model", "addModel()");
            throw new IllegalArgumentException();
        }

        //Validar se a brand do model já está registrada.
        Brand brand = this.brandRepository.findById(modelDTO.getBrand().getId()).orElse(null);

        if(brand == null) {
            this.brandRepository.save(modelDTO.getBrand());
        }

        ModelDTO newModelDTO = new ModelDTO(this.modelRepository.save(
                new Model(
                        model.getId(),
                        modelDTO.getName(),
                        modelDTO.getBrand())));

        return newModelDTO;
    }

    public ModelDTO updateModel(ModelDTO modelDTO) {
        Model model = this.modelRepository.findById(modelDTO.getId()).orElse(null);

        if(model == null) {
            ////////TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException();
        }

        Model updatedModel = new Model(modelDTO.getId(),
                modelDTO.getName(),
                modelDTO.getBrand()
        );

        return new ModelDTO(this.modelRepository.save(updatedModel));
    }

    public void deleteModel(ModelDTO modelDTO) {
        //Checar se o modelDTO está na base de dados.
        Model model = this.modelRepository.findById(modelDTO.getId()).orElse(null);

        if(model == null) {
            ////////TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException();
        }

        this.modelRepository.delete(new Model(modelDTO.getId(), modelDTO.getName(), modelDTO.getBrand()));
    }


    public List<BrandDTO> getAllBrands() {
        List<Brand> brandList = this.brandRepository.findAll();

        if(brandList == null) {
            //TODO: throw new TableIsEmptyException();
            throw new IllegalArgumentException();
        }

        List<BrandDTO> brandDTOList = new ArrayList<>();

        for(Brand brand : brandList) {
            brandDTOList.add(new BrandDTO(brand));
        }

        return brandDTOList;
    }

    public BrandDTO getBrandById(int id) {
        Brand otherBrand = this.brandRepository.findById(id).get();

        if(otherBrand == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException();
        }

        return new BrandDTO(otherBrand);
    }

    public BrandDTO addBrand(BrandDTO brandDTO) {
        if(brandDTO == null) {
            ////////////////TODO:
            throw new IllegalArgumentException("Invalid data");
        }

        Brand brand = this.brandRepository.findById(brandDTO.getId()).get();

        if(brand != null) {
            //TODO: throw new EntityAlreadyExistsException();
            throw new IllegalArgumentException();
        }

        brand = this.brandRepository.save(new Brand(brandDTO.getId(), brandDTO.getName()));

        BrandDTO addedBrandDTO = new BrandDTO(brand);

        return addedBrandDTO;
    }

    public BrandDTO updateBrand(BrandDTO brandDTO) {
        Brand brand = this.brandRepository.findById(brandDTO.getId()).get();

        if(brand == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException();
        }

        Brand updatedBrand = new Brand(brandDTO.getId(),
                brandDTO.getName());

        return new BrandDTO(this.brandRepository.save(updatedBrand));
    }

    public void deleteBrand(BrandDTO brandDTO) {
        Brand brand = this.brandRepository.findById(brandDTO.getId()).get();

        if(brand == null) {
            ////////TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException();
        }

        this.brandRepository.deleteById(brandDTO.getId());
    }

    public List<CarDTO> getCarsByStatus(CarStatus carStatus) {
        List<CarDTO> carsList = this.getAllCars();

        List<CarDTO> carDTOByStatusList = new ArrayList<>();

        for(CarDTO carDTO : carsList) {
            if(carDTO.getCarStatus().equals(carStatus)) {
                carDTOByStatusList.add(carDTO);
            }
        }

        return carDTOByStatusList;
    }

}
