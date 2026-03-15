package com.logistics.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.logistics.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
