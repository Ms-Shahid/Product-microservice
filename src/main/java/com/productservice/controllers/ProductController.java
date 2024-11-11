package com.productservice.controllers;

import com.productservice.dtos.ProductNotFoundExceptionDto;
import com.productservice.exceptions.ProductNotFoundException;
import com.productservice.models.Product;
import com.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("customName") ProductService productService){
        this.productService = productService;
    }

    //get a product
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> productResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return productResponseEntity;
    }


    //get product by limit
    @GetMapping("?limit={limit}")
    public Product getProductsByLimit(@PathVariable("limit") Long limit){

        return productService.getProductsByLimit(limit);
    }

    //get all products
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //implement replace all product details
    @PutMapping("/{id}")
    public Product replaceProductDetails(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    //patch product
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id, product);
    }

    //delete product
    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable("id") Long id ){
        return productService.deleteProductById(id);
    }

    //JPA
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }


}
