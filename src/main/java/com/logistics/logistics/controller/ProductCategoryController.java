package com.logistics.logistics.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.logistics.model.ProductCategory;
import com.logistics.logistics.service.ProductCategoryService;

@RestController
@RequestMapping("/api/productCategories")
public class ProductCategoryController {

	private final ProductCategoryService service;

	public ProductCategoryController(ProductCategoryService service) {
		this.service = service;
	}

	// --------------------------------

	@PostMapping
	public ProductCategory createProductCategory(@RequestBody ProductCategory pc) {

		return service.createProductCategory(pc);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) {

		ProductCategory pc = service.getProductCategoryById(id);
		if (pc == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pc);
	}

	@GetMapping
	public List<ProductCategory> getAllProductCategories() {
		return service.getAllProductCategories();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable Long id) {

		ProductCategory deletedPC = service.deleteProductCategory(id);

		if (deletedPC == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(deletedPC);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable Long id,
			@RequestBody ProductCategory updatedProductCategory) {

		ProductCategory pc = service.updateProductCategory(id, updatedProductCategory);

		if (pc == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pc);

	}

}
