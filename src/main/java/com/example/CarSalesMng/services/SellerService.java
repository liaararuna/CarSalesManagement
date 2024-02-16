package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.SellerRepository;
import com.example.CarSalesMng.models.Seller;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public Seller getSellerById(int id) {
        return this.sellerRepository.findById(id).orElse(null);
    }

    public List<Seller> getAllSellers() {
        return this.sellerRepository.findAll();
    }

    public Seller add(Seller newSeller) {
        if(newSeller == null) {
            throw new IllegalArgumentException();
        }
        return this.sellerRepository.save(newSeller);
    }

    public Seller update(Seller updatedSeller) {
        if(updatedSeller == null) {
            throw new NotImplementedException();
        }
        return this.sellerRepository.save(updatedSeller);
    }
}
