package com.shopee.taxcalculator.controller;

import com.shopee.taxcalculator.model.TaxCalculator;
import com.shopee.taxcalculator.service.TaxCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaxCalculatorController {
    @Autowired
    private TaxCalculatorService taxCalculatorService;

    @GetMapping("/tax_calculators")
    public TaxCalculator getTaxCalculator() {
        return taxCalculatorService.getTaxCalculator();
    }
}
