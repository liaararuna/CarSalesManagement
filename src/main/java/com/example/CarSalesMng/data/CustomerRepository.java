package com.example.CarSalesMng.data;

import com.example.CarSalesMng.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
