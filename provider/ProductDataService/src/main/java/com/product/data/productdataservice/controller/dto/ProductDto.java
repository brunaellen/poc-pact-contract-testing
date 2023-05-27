package com.product.data.productdataservice.controller.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;


@Value
@Builder
@Jacksonized
public class ProductDto {

    Long productId;

    String name;

    BigDecimal price;
}
