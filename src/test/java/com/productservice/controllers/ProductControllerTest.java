//package com.productservice.controllers;
//
//import com.productservice.exceptions.ProductNotFoundException;
//import com.productservice.models.Product;
//import com.productservice.services.ProductService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest()//properties = {"springdoc.api-docs.enabled=false"}) // Disables Swagger for tests
//
//class ProductControllerTest {
//
//    @Autowired
//    ProductController productController;
//
//    @MockBean()
//    ProductService productService;
//
//    @Test
//    void getProductById() throws ProductNotFoundException {
//
//        //Arrange
//        long productId = 1L;
//        Product product = new Product();
//        product.setId(1L);
//        product.setTitle("Sample test product");
//
//        //Act
//        when(productService.getProductById(1L)).thenReturn(product); //Mocked product service without any db call
//
//        Product prod = productController.getProductById(productId);
//
//        //Assertion
//        Assertions.assertEquals("Sample test product", prod.getTitle());
//    }
//
//    @Test
//    void getProductByIdHandleException() throws ProductNotFoundException {
//
//        long productId = 1L;
//        Product product = new Product();
//        product.setId(1L);
//        product.setTitle("Sample Test product");
//
//
//        when(productService.getProductById(1L)).thenThrow(ProductNotFoundException.class).getClass();
//
//        Assertions.assertThrows(
//                ProductNotFoundException.class,
//                () -> productController.getProductById(productId)
//        );
//    }
//
//}