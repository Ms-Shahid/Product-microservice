package com.productservice.services;

import com.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    Product getProductsByLimit(Long limit);

    List<Product> getAllProducts();
}
