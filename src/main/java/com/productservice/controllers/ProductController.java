package com.productservice.controllers;

import com.productservice.exceptions.ProductNotFoundException;
import com.productservice.models.Product;
import com.productservice.services.ProductService;
//import com.productservice.services.TokenService;
import org.hibernate.cache.spi.access.UnknownAccessTypeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    //private TokenService tokenService;

    public ProductController(ProductService productService
                            ){
        this.productService = productService;
        //this.tokenService = tokenService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);

        ResponseEntity<Product> productResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return product;
    }

    @GetMapping("/validate/{id}")
    public Product validateTokenAndGetProduct( @RequestHeader("Token") String token,
                                               @PathVariable("id") Long id) throws ProductNotFoundException {

//        if(!tokenService.validateToken(token)){
//            System.out.println("Token -> " + token);
//            throw new UnknownAccessTypeException("User is not authorized");
//        }
        Product product = productService.getProductById(id);
        ResponseEntity<Product> productResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return productResponseEntity.getBody();
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

    //selfProduct - HQL based
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }



}
