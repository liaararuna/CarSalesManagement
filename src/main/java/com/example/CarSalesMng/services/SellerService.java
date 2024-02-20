package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.SellerRepository;
import com.example.CarSalesMng.enums.CarStatus;
import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Seller;
import com.example.CarSalesMng.models.dto.CarDTO;
import com.example.CarSalesMng.models.dto.SellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public SellerDTO getSellerById(int id) {
        Seller seller = this.sellerRepository.findById(id).orElse(null);

        if(seller == null) {
            ///TODO: ACRESCENTAR EXCEÇÃO
            return null;
        }

        return new SellerDTO(seller);
    }

    public List<SellerDTO> getAllSellers() {
        List<Seller> sellerList = this.sellerRepository.findAll();

        if(sellerList == null) {
            ///TODO: ACRESCENTAR EXCEÇÃO
            return null;
        }

        List<SellerDTO> sellerDTOList = new ArrayList<>();

        for(Seller seller : sellerList) {
            SellerDTO sellerDTO = new SellerDTO(seller);
            sellerDTOList.add(sellerDTO);
        }

        return sellerDTOList;
    }

    public SellerDTO add(SellerDTO sellerDTO) {
        //Validação se o seller já existe no banco de dados.
        Seller seller = this.sellerRepository.findById(sellerDTO.getId()).orElse(null);

        if(seller != null) {
            ////////////////TODO: já existe esse carro no banco de dados.
        }

        //Criação de um objeto do tipo Seller para ser salvo no banco de dados.
        Seller newSeller = new Seller(sellerDTO.getId(),
                sellerDTO.getName(),
                sellerDTO.getNif(),
                sellerDTO.getAddress(),
                sellerDTO.getPhoneNumber(),
                new ArrayList<>(sellerDTO.getCarList())
        );

        return new SellerDTO(newSeller);
    }

    public SellerDTO update(SellerDTO updatedSellerDTO) {

        Seller newSeller = new Seller(
                updatedSellerDTO.getId(),
                updatedSellerDTO.getName(),
                updatedSellerDTO.getNif(),
                updatedSellerDTO.getAddress(),
                updatedSellerDTO.getPhoneNumber(),
                updatedSellerDTO.getCarList()
        );

        this.sellerRepository.save(newSeller);

        return updatedSellerDTO;
    }

    public SellerDTO deleteById(int id) {
        Seller seller = this.sellerRepository.findById(id).get();

        if(seller == null) {
            //throw new EntityDoesnExist();
        }

        this.sellerRepository.deleteById(id);
        return new SellerDTO(seller);
    }

}
