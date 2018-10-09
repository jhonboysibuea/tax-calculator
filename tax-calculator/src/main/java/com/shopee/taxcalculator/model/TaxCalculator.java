package com.shopee.taxcalculator.model;

import java.util.List;

public class TaxCalculator {
    private Double priceSubtotal;
    private Double taxSubtotal;
    private Double grandTotal;
    private List<Detail> listDetail;

    public TaxCalculator(List<Detail> listDetail){
        this.listDetail = listDetail;
    }

    public List<Detail> getListLineItem() {
        return listDetail;
    }

    public void setListLineItem(List<Detail> listLineItem) {
        this.listDetail = listLineItem;
    }

    public Double getPriceSubtotal() {
        return listDetail.stream()
                .map(Detail::getProductPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Double getTaxSubtotal() {
        return listDetail.stream()
                .map(Detail::getTax)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Double getGrandTotal() {
        return listDetail.stream()
                .map(Detail::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
