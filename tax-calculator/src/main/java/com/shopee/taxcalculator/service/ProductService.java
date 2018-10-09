package com.shopee.taxcalculator.service;

import com.shopee.taxcalculator.exception.ResourceNotFoundException;
import com.shopee.taxcalculator.model.Product;
import com.shopee.taxcalculator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    public Product updateProduct(Product newProduct, Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        if (product.getName()!= null && product.getName()!=newProduct.getName()) product.setName(newProduct.getName());
        if (product.getTaxCode()!=null && product.getTaxCode()!= newProduct.getTaxCode()) product.setTaxCode(newProduct.getTaxCode());
        if (product.getPrice()!=null && product.getPrice()!= newProduct.getPrice()) product.setPrice(newProduct.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }


}
