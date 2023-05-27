package com.product.data.productdataservice.service;

import com.product.data.productdataservice.controller.dto.ProductDto;
import com.product.data.productdataservice.model.Product;
import com.product.data.productdataservice.repository.ProductRepository;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Value
@Log4j2
public class ProductService {

    ProductRepository productRepository;

    public Optional<ProductDto> updateAProduct(final ProductDto product) {
        final Optional<Product> existingProduct = getExistingProduct(product.getProductId());

        if(existingProduct.isPresent()) {
            final Product productUpdated = productRepository.save(new Product(product.getProductId(), product.getName(), product.getPrice()));

            log.info("The product updated is productUpdated={}", productUpdated);

            return Optional.of(ProductDto.builder()
                .productId(productUpdated.getId())
                .name(productUpdated.getName())
                .price(product.getPrice())
                .build());
        }
        return Optional.empty();
    }

    private Optional<Product> getExistingProduct(final long productId) {
        return productRepository.findById(productId);
    }
}
