package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.SellerRepository;
import com.example.CarSalesMng.models.Seller;
import com.example.CarSalesMng.models.dto.SellerDTO;
import org.apache.commons.lang3.NotImplementedException;
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

        if(seller == null) { return null; }

        return new SellerDTO(
                seller.getId(),
                seller.getName(),
                seller.getNif(),
                seller.getAddress(),
                seller.getPhoneNumber()
        );
    }

    public List<SellerDTO> getAllSellers() {
        List<Seller> sellerList = this.sellerRepository.findAll();

        if(sellerList == null) { return null; }

        List<SellerDTO> sellerDTOList = new ArrayList<>();

        for(Seller seller : sellerList) {
            SellerDTO sellerDTO = new SellerDTO(
                    seller.getId(),
                    seller.getName(),
                    seller.getNif(),
                    seller.getAddress(),
                    seller.getPhoneNumber()
            );
            sellerDTOList.add(sellerDTO);
        }
        return sellerDTOList;
    }

    public SellerDTO add(SellerDTO newSellerDTO) {
        Seller newSeller = new Seller(
                newSellerDTO.getId(),
                newSellerDTO.getName(),
                newSellerDTO.getNif(),
                newSellerDTO.getAddress(),
                newSellerDTO.getPhoneNumber()
        );

        this.sellerRepository.save(newSeller);

        return newSellerDTO;
    }

    public SellerDTO update(SellerDTO updatedSellerDTO) {
        Seller newSeller = new Seller(
                updatedSellerDTO.getId(),
                updatedSellerDTO.getName(),
                updatedSellerDTO.getNif(),
                updatedSellerDTO.getAddress(),
                updatedSellerDTO.getPhoneNumber()
        );

        this.sellerRepository.save(newSeller);

        return updatedSellerDTO;
    }
}
