package com.example.CarSalesMng.data;

import com.example.CarSalesMng.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
