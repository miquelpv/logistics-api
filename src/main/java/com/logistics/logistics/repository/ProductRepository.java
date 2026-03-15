package com.logistics.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.logistics.logistics.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
