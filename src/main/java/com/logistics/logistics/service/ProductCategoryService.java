package com.logistics.logistics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.logistics.model.ProductCategory;
import com.logistics.logistics.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	private final ProductCategoryRepository repository;

	public ProductCategoryService(ProductCategoryRepository repository) {
		this.repository = repository;
	}

	// ------------------------

	public ProductCategory createProductCategory(ProductCategory pc) {
		return repository.save(pc);
	}

	public ProductCategory getProductCategoryById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<ProductCategory> getAllProductCategories() {
		return repository.findAll();
	}

	public ProductCategory deleteProductCategory(Long id) {

		if (repository.existsById(id) == false) {
			return null;
		}

		ProductCategory deletedPC = getProductCategoryById(id);
		repository.deleteById(id);

		return deletedPC;
	}
	
	public ProductCategory updateProductCategory(Long id, ProductCategory updatedPC) {
		
		ProductCategory pc = repository.findById(id).orElse(null);
		if (pc == null) {
			return null;
		}
		
		pc.setName(updatedPC.getName());
		
		repository.save(pc);
		return pc;
	}

}
