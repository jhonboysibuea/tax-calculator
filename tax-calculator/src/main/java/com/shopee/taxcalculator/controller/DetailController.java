package com.shopee.taxcalculator.controller;

import com.shopee.taxcalculator.model.Detail;
import com.shopee.taxcalculator.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/detail/{productId}")
    public Detail getDetailByProductId(@PathVariable Long productId) {
        return detailService.getDetailByProductId(productId);
    }
}
