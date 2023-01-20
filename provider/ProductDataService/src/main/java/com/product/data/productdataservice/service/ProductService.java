package com.product.data.productdataservice.service;

import com.product.data.productdataservice.controller.dto.ProductDto;
import com.product.data.productdataservice.model.Product;
import com.product.data.productdataservice.repository.ProductRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Value
public class ProductService {

    ProductRepository productRepository;

    public ProductDto updateAProduct(final ProductDto product) {
        final Optional<Product> existingProduct = getExistingProduct(product.getProductId());

        if(existingProduct.isPresent()) {
            final Product productUpdated = productRepository.save(new Product(product.getProductId(), product.getName(), product.getPrice()));
            return new ProductDto(productUpdated.getId(), productUpdated.getName(), product.getPrice());
        } else {
            throw new RuntimeException("product not found");
        }
    }

    private Optional<Product> getExistingProduct(long productId) {
        return productRepository.findById(productId);
    }
}