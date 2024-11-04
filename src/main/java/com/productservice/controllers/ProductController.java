package com.productservice.controllers;

import com.productservice.models.Product;
import com.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //get a product
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){

        return productService.getProductById(id);
    }

    //get product by limit
    @GetMapping("?limit={limit}")
    public Product getProductsByLimit(@PathVariable("limit") Long limit){

        return productService.getProductsByLimit(limit);
    }


}
