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

import com.logistics.logistics.model.Product;
import com.logistics.logistics.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	// --------------------------------

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {

		Product product = service.getProductById(id);

		if (product == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(product);
	}

	@GetMapping
	public List<Product> getProducts() {

		return service.getAllProducts();
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {

		try {

			Product createdProduct = service.createProduct(product);
			return ResponseEntity.ok(createdProduct);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		boolean deleted = service.deleteProduct(id);

		if (deleted == false) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

		Product product = service.updateProduct(id, updatedProduct);

		if (product == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(product);

	}

}
