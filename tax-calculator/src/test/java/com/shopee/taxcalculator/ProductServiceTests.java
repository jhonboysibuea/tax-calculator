package com.shopee.taxcalculator;

import com.shopee.taxcalculator.exception.ResourceNotFoundException;
import com.shopee.taxcalculator.model.Product;
import com.shopee.taxcalculator.repository.ProductRepository;
import com.shopee.taxcalculator.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Test
	public void getAllProductsTestTest() {
		Product product = new Product(1l, "Game", 3, 100d);
		List<Product> listProduct = new ArrayList<>();
		listProduct.add(product);

		Mockito.when(productRepository.findAll()).thenReturn(listProduct);
		assertThat(productService.getAllProducts()).isEqualTo(listProduct);
	}

	@Test
	public void createProductTestTest() {
		Product product = new Product(1l, "Game", 3, 100d);
		Mockito.when(productRepository.save(product)).thenReturn(product);
		assertThat(productService.createProduct(product)).isEqualTo(product);
	}

	@Test
	public void getProductByIdTest() {
		Product product = new Product(1l, "Game", 3, 100d);
		Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
		assertThat(productService.getProductById(product.getId())).isEqualTo(product);
	}

	@Test
	public void updateProductTest(){
		Product product = new Product(1l, "Game", 3, 100d);
		Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
		product.setPrice(150d);
		Mockito.when(productRepository.save(product)).thenReturn(product);
		assertThat(productService.updateProduct(product, 1l).equals(product));
	}

	@Test
	public void deleteProductTest(){
		Product product = new Product(1l, "Game", 3, 100d);
		Mockito.when(productRepository.getOne(product.getId())).thenReturn(product);
		Mockito.when(productRepository.existsById(product.getId())).thenReturn(false);
		assertFalse(productRepository.existsById(product.getId()));
	}
}
