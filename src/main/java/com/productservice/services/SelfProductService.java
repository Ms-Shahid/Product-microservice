package com.productservice.services;

import com.productservice.exceptions.ProductNotFoundException;
import com.productservice.models.Category;
import com.productservice.models.Product;
import com.productservice.repo.CategoryRepo;
import com.productservice.repo.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customName")
@Primary
public class SelfProductService implements ProductService{

    ProductRepo productRepo;
    CategoryRepo categoryRepo;
    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product getProductsByLimit(Long limit) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        if( category == null){
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        }
        return productRepo.save(product);
    }
}
