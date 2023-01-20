package com.product.data.productdataservice.controller;

import com.product.data.productdataservice.controller.dto.ProductDto;
import com.product.data.productdataservice.service.ProductService;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@Value
public class ProductUpdateController {

  ProductService productService;
  @PutMapping("/update")
  public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productToUpdate) {
    final Optional<ProductDto> productDto = productService.updateAProduct(productToUpdate);

    if(productDto.isPresent()) {
      return ResponseEntity.ok(productDto.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product does not exist!");
    }

  }
}
