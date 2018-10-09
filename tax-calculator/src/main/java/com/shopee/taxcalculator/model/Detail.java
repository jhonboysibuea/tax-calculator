package com.shopee.taxcalculator.model;

import java.util.*;

public class Detail {
    private Product product;
    private String productName;
    private Integer productTaxCode;
    private String type;
    private String refundable;
    private Double productPrice;
    private Double tax;
    private Double amount;

    public Detail(Product product){
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        return product.getName();
    }

    public Integer getProductTaxCode() {
        return product.getTaxCode();
    }

    public String getType() {
        Map<Integer, String> mapType = new HashMap<Integer, String>(){{
            put(1, "Food & Beverage");
            put(2, "Tobacco");
            put(3, "Entertainment");
        }};
        return mapType.get(product.getTaxCode());
    }

    public String getRefundable() {
        Map<Integer, String> mapRefundable = new HashMap<Integer, String>(){{
            put(1, "Yes");
            put(2, "No");
            put(3, "No");
        }};
        return mapRefundable.get(product.getTaxCode());
    }

    public Double getProductPrice() {
        return product.getPrice();
    }

    public Double getTax() {
        double tmpTax = 0;
        switch (product.getTaxCode()){
            case 1:
                tmpTax = 0.1 * product.getPrice();
                break;
            case 2:
                tmpTax = 10 + (0.02 * product.getPrice());
                break;
            default:
                tmpTax = (product.getPrice()>0 && product.getPrice()<100) ? 0 : 0.01 * (product.getPrice() - 100);
                break;
        }
        return tmpTax;
    }

    public Double getAmount() {
        return product.getPrice() + getTax();
    }
}
