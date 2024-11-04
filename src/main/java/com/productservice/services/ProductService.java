package com.productservice.services;

import com.productservice.models.Product;

public interface ProductService {

    Product getProductById(Long id);

    Product getProductsByLimit(Long limit);

}
