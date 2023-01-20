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
    ProductDto productDto = new ProductDto(1L, "sofa", BigDecimal.valueOf(10.50));

    when(productServiceMock.updateAProduct(productDto)).thenReturn(Optional.of(productDto));

    ProductDto productUpdated = productUpdateController.updateProduct(productDto).getBody();
     assertThat(productUpdated).isEqualTo(productDto);
  }

  @Test
  void givenAProductNotRegistered_updateProduct_shouldThrowsException() {
    ProductDto productDto = new ProductDto(3L, "sofa", BigDecimal.valueOf(10.50));

    assertThatThrownBy(() -> productUpdateController.updateProduct(productDto))
        .isInstanceOf(ResponseStatusException.class)
        .hasMessage("404 NOT_FOUND \"product does not exist!\"");
  }
}
