package com.example.CarSalesMng.data;

import com.example.CarSalesMng.models.Car;
import com.example.CarSalesMng.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

}
