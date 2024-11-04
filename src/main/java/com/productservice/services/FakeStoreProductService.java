package com.productservice.services;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.models.Category;
import com.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate; //a way used to call 3rd party api

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        System.out.println("Product details -> " + product);
        return product;
    }

    @Override
    public Product getProductsByLimit(Long limit) {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products?limit=" + limit,
                FakeStoreProductDto.class);

        Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        System.out.println("Product details -> " + product);
        return product;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {

        if (fakeStoreProductDto == null) {
            return null;
        }

        Product product = new Product();
        // Map fields from FakeStoreProductDto to Product
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDesc(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        // Create and set the Category object
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

}

//DTO's -> is the contract between 2 services, when we want to get the response, create a DTO