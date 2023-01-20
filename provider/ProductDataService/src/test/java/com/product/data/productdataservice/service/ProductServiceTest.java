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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        final Product expectedProduct = new Product(1L, "sofa", BigDecimal.valueOf(10.50));

        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepositoryMock.save(expectedProduct)).thenReturn(expectedProduct);

        final ProductDto product = productService.updateAProduct(new ProductDto(1L, "sofa", BigDecimal.valueOf(10.50)));

        assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(10.50));
    }

    @Test
    void givenAProductNotRegistered_updateAProduct_shouldThrowsException() {
        assertThatThrownBy(() -> productService.updateAProduct(new ProductDto(1L, "sofa", BigDecimal.valueOf(10.50))))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("product not found");
    }
}