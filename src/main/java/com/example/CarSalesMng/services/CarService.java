package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.BrandRepository;
import com.example.CarSalesMng.data.CarRepository;
import com.example.CarSalesMng.data.ModelRepository;
import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.Brand;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Model;
import com.example.CarSalesMng.models.dto.BrandDTO;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.models.dto.ModelDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService{
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    /**
     * Retrieve a paginated list of all cars.
     *
     * @param page The page number.
     * @param size The page size.
     * @return A paginated list of {@link CarDTO}.
     * @throws IllegalArgumentException If the table is empty.
     */
    public Page<CarDTO> getAllCars(int page, int size) {
        Page<Car> carPage = this.carRepository.findAll(PageRequest.of(page,size));

        if(!carPage.hasContent()) {
            //TODO: throw new TableIsEmptyException();
            throw new IllegalArgumentException("TableIsEmptyException");
        }

        Page<CarDTO> carDTOPage = new PageImpl<>(carPage.getContent().stream()
                .map(CarDTO::new)
                .collect(Collectors.toList()), carPage.getPageable(), carPage.getTotalElements());

        return carDTOPage;
    }

    /**
     * Retrieve car information based on the Vehicle Identification Number (VIN).
     *
     * @param vin The Vehicle Identification Number.
     * @return The {@link CarDTO} representing the car with the specified VIN.
     * @throws IllegalArgumentException If the entity does not exist.
     */
    public CarDTO getCarByVin(int vin) {
        Car otherCar = this.carRepository.findById(vin).orElse(null);

        if(otherCar == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        return new CarDTO(otherCar);
    }

    /**
     * Add a new car to the database.
     *
     * @param carDTO The {@link CarDTO} representing the new car.
     * @return The {@link CarDTO} representing the added car.
     * @throws IllegalArgumentException If the entity already exists.
     */
    @Transactional
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

    /**
     * Update car information in the database.
     *
     * @param carDTO The {@link CarDTO} representing the updated car information.
     * @return The {@link CarDTO} representing the updated car.
     * @throws IllegalArgumentException If the entity does not exist.
     */
    @Transactional
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

    /**
     * Retrieve a paginated list of all models.
     *
     * @param page The page number.
     * @param size The page size.
     * @return A paginated list of {@link ModelDTO}.
     * @throws IllegalArgumentException If the table is empty.
     */
    public Page<ModelDTO> getAllModels(int page, int size)  {
        Page<Model> modelPage = this.modelRepository.findAll(PageRequest.of(page,size));

        if(!modelPage.hasContent()) {
            //TODO: throw new TableIsEmptyException();
            throw new IllegalArgumentException("TableIsEmptyException");
        }

        Page<ModelDTO> modelDTOPage = new PageImpl<>(modelPage.getContent().stream()
                .map(ModelDTO::new)
                .collect(Collectors.toList()), modelPage.getPageable(), modelPage.getTotalElements());

        return modelDTOPage;
    }

    /**
     * Retrieve model information based on the model ID.
     *
     * @param id The model ID.
     * @return The {@link ModelDTO} representing the model with the specified ID.
     * @throws IllegalArgumentException If the entity does not exist.
     */
    public ModelDTO getModel(int id) {
        Model model = this.modelRepository.findById(id).orElse(null);

        if(model == null) {
            //TODO: throw new EntityDoesntExistException("Model", "getModel");
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        return new ModelDTO(model);
    }

    /**
     * Add a new model to the database.
     *
     * @param modelDTO The {@link ModelDTO} representing the new model.
     * @return The {@link ModelDTO} representing the added model.
     * @throws IllegalArgumentException If the entity already exists.
     */
    @Transactional
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

    /**
     * Update model information in the database.
     *
     * @param modelDTO The {@link ModelDTO} representing the updated model information.
     * @return The {@link ModelDTO} representing the updated model.
     * @throws IllegalArgumentException If the entity does not exist.
     */
    @Transactional
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

    /**
     * Delete a model from the database.
     *
     * @param modelDTO The {@link ModelDTO} representing the model to be deleted.
     * @throws IllegalArgumentException If the entity does not exist.
     */
    public void deleteModel(ModelDTO modelDTO) {
        //Checar se o modelDTO está na base de dados.
        Model model = this.modelRepository.findById(modelDTO.getId()).orElse(null);

        if(model == null) {
            ////////TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException();
        }

        this.modelRepository.delete(new Model(modelDTO.getId(), modelDTO.getName(), modelDTO.getBrand()));
    }

    /**
     * Retrieve a paginated list of all brands.
     *
     * @param page The page number.
     * @param size The page size.
     * @return A paginated list of {@link BrandDTO}.
     * @throws IllegalArgumentException If the table is empty.
     */
    public Page<BrandDTO> getAllBrands(int page, int size) {
        //PageRequest.of(page,size) -> define o objeto PageRequest passando como parâmetro o número da página e o seu tamanho.
        //O findAll da classe Pageable retorna como padrão um Page<T>, mas pode retornar também a Slice<T> ou a List<T>. A vantagem de retornar um Page<T> é que além de retornar uma lista de objetos, ainda permite saber o número total de páginas.
        Page<Brand> brandPage = this.brandRepository.findAll(PageRequest.of(page,size));

        if(!brandPage.hasContent()) {
            //TODO: throw new TableIsEmptyException();
            throw new IllegalArgumentException("TableIsEmptyException");
        }

        //Transforms a paginated list of Brands into a paginated list of BrandDTOs.
        Page<BrandDTO> brandDTOPage = new PageImpl<>(brandPage.getContent()
                //Get the content of the page (the list of Brands) and converts it into a stream (sequence of elements that can be processed in a functional style)
                .stream()
                //Create a new BrandDTO object for each Brand in the stream
                .map(BrandDTO::new)
                //Collects the BrandDTO objects into a list
                .collect(Collectors.toList()),
                //Represents the information about the requested page, like page number, page size, sort order, etc.
                brandPage.getPageable(),
                //Retrieves the total number of elements available across all pages.
                brandPage.getTotalElements());

        return brandDTOPage;
    }

    /**
     * Retrieve brand information based on the brand ID.
     *
     * @param id The brand ID.
     * @return The {@link BrandDTO} representing the brand with the specified ID.
     * @throws IllegalArgumentException If the entity does not exist.
     */
    public BrandDTO getBrandById(int id) {
        Brand otherBrand = this.brandRepository.findById(id).get();

        if(otherBrand == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        return new BrandDTO(otherBrand);
    }

    /**
     * Add a new brand to the database.
     *
     * @param brandDTO The {@link BrandDTO} representing the new brand.
     * @return The {@link BrandDTO} representing the added brand.
     * @throws IllegalArgumentException If the entity already exists.
     */
    @Transactional
    public BrandDTO addBrand(BrandDTO brandDTO) {
        if(brandDTO == null) {
            throw new IllegalArgumentException("Invalid data");
        }

        Brand brand = this.brandRepository.findById(brandDTO.getId()).get();

        if(brand != null) {
            //TODO: throw new EntityAlreadyExistsException();
            throw new IllegalArgumentException("EntityAlreadyExistsException");
        }

        brand = this.brandRepository.save(new Brand(brandDTO.getId(), brandDTO.getName()));

        BrandDTO addedBrandDTO = new BrandDTO(brand);

        return addedBrandDTO;
    }

    /**
     * Update brand information in the database.
     *
     * @param brandDTO The {@link BrandDTO} representing the updated brand information.
     * @return The {@link BrandDTO} representing the updated brand.
     * @throws IllegalArgumentException If the entity does not exist.
     */
    @Transactional
    public BrandDTO updateBrand(BrandDTO brandDTO) {
        Brand brand = this.brandRepository.findById(brandDTO.getId()).get();

        if(brand == null) {
            //TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        Brand updatedBrand = new Brand(brandDTO.getId(),
                brandDTO.getName());

        return new BrandDTO(this.brandRepository.save(updatedBrand));
    }

    /**
     * Delete a brand
     * @param brandDTO BrandDTO to be deleted.
     */
    public void deleteBrand(BrandDTO brandDTO) {
        Brand brand = this.brandRepository.findById(brandDTO.getId()).get();

        if(brand == null) {
            ////////TODO: throw new EntityDoesntExistException();
            throw new IllegalArgumentException("EntityDoesntExistException");
        }

        this.brandRepository.deleteById(brandDTO.getId());
    }


    /**
     * Returns a list of cars according to a status.
     * @param status Transaction id
     * @return List of cars according to a status.
     */
    public Page<CarDTO> findCarByStatus(CarStatus status, int page, int size, String sort) {
        Page<Car> carPage = this.carRepository.findCarByCarStatus(status, PageRequest.of(page, size, Sort.by(sort)));

        if(!carPage.hasContent()) {
            throw new IllegalArgumentException("There's no car with these status");
        }

        Page<CarDTO> carDTOPage = new PageImpl<>(carPage.getContent()
                //Get the content of the page (the list of Cars) and converts it into a stream (sequence of elements that can be processed in a functional style)
                .stream()
                //Create a new CarDTO object for each Car in the stream
                .map(CarDTO::new)
                //Collects the CarDTO objects into a list
                .collect(Collectors.toList()),
                //Represents the information about the requested page, like page number, page size, sort order, etc.
                carPage.getPageable(),
                //Retrieves the total number of elements available across all pages.
                carPage.getTotalElements());

        return carDTOPage;
    }

    /**
     * Returns a list of cars according to a model.
     * @param model Transaction id
     * @return List of cars according to a model.
     */
    public Page<CarDTO> findCarByModel(Model model, int page, int size) {
        Page<Car> carPage = this.carRepository.findCarByModel(model, PageRequest.of(page, size));

        if(!carPage.hasContent()) {
            throw new IllegalArgumentException("There's no car with these model");
        }

        Page<CarDTO> carDTOPage = new PageImpl<>(carPage.getContent()
                //Get the content of the page (the list of Cars) and converts it into a stream (sequence of elements that can be processed in a functional style)
                .stream()
                //Create a new CarDTO object for each Car in the stream
                .map(CarDTO::new)
                //Collects the CarDTO objects into a list
                .collect(Collectors.toList()),
                //Represents the information about the requested page, like page number, page size, sort order, etc.
                carPage.getPageable(),
                //Retrieves the total number of elements available across all pages.
                carPage.getTotalElements());

        return carDTOPage;
    }

    /*TODO:
    public List<CarDTO> findCarByBuyerId(int buyerId) {
        List<CarDTO> carsList = this.carRepository.findCarByBuyerId(buyerId);

        if(carsList == null) {
            throw new IllegalArgumentException("There's no car for this buyer");
        }

        return carsList;
    }*/


    /**
     * Returns a list of cars according to a transaction id.
     * @param idTransaction Transaction id
     * @return List of cars according to a transaction id.
     */
    public List<CarDTO> findCarByIdTransaction(int idTransaction) {
        List<Car> carsList = this.carRepository.findCarByIdTransaction(idTransaction);

        if(carsList == null) {
            throw new IllegalArgumentException("There's no car with these model");
        }

        List<CarDTO> carDTOListByIdTransaction = new ArrayList<>();

        for(Car c : carsList) {
            CarDTO carDTO = new CarDTO(c);
            carDTOListByIdTransaction.add(carDTO);
        }

        return carDTOListByIdTransaction;
    }
}
