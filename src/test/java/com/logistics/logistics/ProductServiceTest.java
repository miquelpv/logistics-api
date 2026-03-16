package com.logistics.logistics;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.logistics.logistics.model.Product;
import com.logistics.logistics.repository.ProductRepository;
import com.logistics.logistics.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	ProductRepository repository;

	@InjectMocks
	ProductService service;

	@Test
	void getProductByIdShouldReturnProduct() {

		Product product = new Product();
		product.setName("Mouse");
		product.setPrice(15.00);

		when(repository.findById(1L)).thenReturn(Optional.of(product));

		Product result = service.getProductById(1L);

		assertEquals(product.getName(), result.getName());
	}

	@Test
	void getProductByIdShouldReturnNullWhenProductDoesNotExist() {

		Long inexistentId = 123L;

		when(repository.findById(inexistentId)).thenReturn(Optional.empty());

		Product result = service.getProductById(inexistentId);

		assertNull(result);
	}

	@Test
	void createProductShouldReturnProduct() {

		Product product = new Product();
		product.setName("Mouse");
		product.setPrice(15.00);

		when(repository.save(product)).thenReturn(product);

		Product result = service.createProduct(product);

		assertEquals(product.getName(), result.getName());
		assertEquals(product.getPrice(), result.getPrice());
	}

	@Test
	void createProductShouldThrowExceptionWhenIdIsIncluded() {

		Product product = new Product();
		product.setId(1L);
		product.setName("Mouse");
		product.setPrice(15.00);

		assertThrows(IllegalArgumentException.class, () -> {
			service.createProduct(product);
		});
	}

}
