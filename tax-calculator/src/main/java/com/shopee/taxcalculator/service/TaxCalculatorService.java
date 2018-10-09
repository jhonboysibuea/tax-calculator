package com.shopee.taxcalculator.service;

import com.shopee.taxcalculator.model.Detail;
import com.shopee.taxcalculator.model.Product;
import com.shopee.taxcalculator.model.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxCalculatorService {
    @Autowired
    private ProductService productService;

    @Autowired
    private DetailService detailService;

    public TaxCalculator getTaxCalculator() {
        List<Product> listProduct = productService.getAllProducts();
        List<Detail> listDetail = new ArrayList<Detail>();
        for (Product product: listProduct) listDetail.add(detailService.getDetailByProductId(product.getId()));
        return new TaxCalculator(listDetail);
    }
}
