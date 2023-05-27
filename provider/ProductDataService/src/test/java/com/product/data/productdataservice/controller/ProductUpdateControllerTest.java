package com.product.data.productdataservice.controller;

import com.product.data.productdataservice.controller.dto.ProductDto;
import com.product.data.productdataservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProductUpdateController.class})
class ProductUpdateControllerTest {

  @MockBean
  public ProductService productServiceMock;

  @Autowired
  private ProductUpdateController productUpdateController;

  @Test
  void updateProduct_shouldUpdateAnExistingProduct() {
    ProductDto expectedUpdatedProduct = ProductDto.builder()
        .productId(1L)
        .name("sofa")
        .price(BigDecimal.valueOf(15.20))
        .build();

    when(productServiceMock.updateAProduct(expectedUpdatedProduct)).thenReturn(Optional.of(expectedUpdatedProduct));

    ProductDto productUpdated = productUpdateController.updateProduct(expectedUpdatedProduct).getBody();

    assertThat(productUpdated).isEqualTo(expectedUpdatedProduct);
  }

  @Test
  void givenAProductNotRegistered_updateProduct_shouldThrowsException() {
    ProductDto productDto = ProductDto.builder()
        .productId(3L)
        .name("sofa")
        .price(BigDecimal.valueOf(15.20))
        .build();

    assertThatThrownBy(() -> productUpdateController.updateProduct(productDto))
        .isInstanceOf(ResponseStatusException.class)
        .hasMessage("404 NOT_FOUND \"product does not exist!\"");
  }
}
