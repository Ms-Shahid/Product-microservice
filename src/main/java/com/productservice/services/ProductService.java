package com.productservice.services;

import com.productservice.exceptions.ProductNotFoundException;
import com.productservice.models.Product;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;

    Product getProductsByLimit(Long limit);

    List<Product> getAllProducts();

    Product replaceProduct(Long id, Product product);

    Product updateProduct(Long id, Product product);
}
