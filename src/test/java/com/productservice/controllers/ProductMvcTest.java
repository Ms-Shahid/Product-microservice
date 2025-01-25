package com.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.models.Product;
import com.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static java.nio.file.Paths.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductMvcTest {

    @MockBean
    ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductController productController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getProductById_ValidId_ReturnsProduct() throws Exception{

        //Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setTitle("Test Product");
        product.setId(productId);
        product.setPrice(100.0);
        when(productService.getProductById(productId)).thenReturn(product);

        //Act & Assert
        mockMvc.perform(
                get("/product/{id}", String.valueOf(productId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .andExpect(status().isOK())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.id").value(productId))
                        .andExpect(jsonPath("$.name").value("Test Product"))
                        .andExpect(jsonPath("$.price").value(100.0))
        );

        verify(productService, times(1)).getProductById(productId);

    }

}
