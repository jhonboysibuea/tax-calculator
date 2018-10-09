package com.shopee.taxcalculator.service;

import com.shopee.taxcalculator.exception.ResourceNotFoundException;
import com.shopee.taxcalculator.model.Detail;
import com.shopee.taxcalculator.model.Product;
import com.shopee.taxcalculator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    @Autowired
    private ProductRepository productRepository;

    public Detail getDetailByProductId(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        return new Detail(product);
    }
}
