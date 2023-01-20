package com.product.data.productdataservice.controller.dto;

import lombok.Value;

import java.math.BigDecimal;


@Value
public class ProductDto {

    Long productId;

    String name;

    BigDecimal price;
}
