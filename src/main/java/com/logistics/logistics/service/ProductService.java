package com.logistics.logistics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.logistics.model.Product;
import com.logistics.logistics.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	// -------------------

	public Product getProductById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	public Product createProduct(Product product) {

		if (product.getId() != null) {
			throw new IllegalArgumentException("A new product cannot have an id");
		}

		return repository.save(product);
	}

	public boolean deleteProduct(Long id) {
		if (!repository.existsById(id)) {
			return false;
		}

		repository.deleteById(id);
		return true;
	}

	public Product updateProduct(Long id, Product updatedProduct) {

		Product product = repository.findById(id).orElse(null);

		if (product == null) {
			return null;
		}

		product.setName(updatedProduct.getName());
		product.setPrice(updatedProduct.getPrice());

		repository.save(product);
		return product;

	}

}
