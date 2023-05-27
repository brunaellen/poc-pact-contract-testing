package com.product.data.productdataservice.service;

import com.product.data.productdataservice.controller.dto.ProductDto;
import com.product.data.productdataservice.model.Product;
import com.product.data.productdataservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepositoryMock;

    @Autowired
    private ProductService productService;

    @Test
    void updateAProduct_shouldUpdateAnExistingProduct() {
        final Product existingProduct = new Product(1L, "sofa", BigDecimal.valueOf(5.00));
        final Product savedProduct = new Product(1L, "sofa", BigDecimal.valueOf(15.20));

        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepositoryMock.save(savedProduct)).thenReturn(savedProduct);

        final Optional<ProductDto> productDto = productService.updateAProduct(ProductDto.builder()
            .productId(1L)
            .name("sofa")
            .price(BigDecimal.valueOf(15.20))
            .build());

        assertThat(productDto).isPresent();
        assertThat(productDto.get().getPrice()).isEqualTo(savedProduct.getPrice());
    }

    @Test
    void givenAProductNotRegistered_updateAProduct_shouldReturnAnEmptyProduct() {
        Optional<ProductDto> productDto = productService.updateAProduct(ProductDto.builder()
            .productId(4L)
            .name("sofa")
            .price(BigDecimal.valueOf(15.20))
            .build());

        assertThat(productDto).isNotPresent();
    }
}
