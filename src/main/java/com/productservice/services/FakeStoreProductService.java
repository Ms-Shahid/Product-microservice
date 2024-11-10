package com.productservice.services;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.exceptions.ProductNotFoundException;
import com.productservice.models.Category;
import com.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate; //a way used to call 3rd party api

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

        if( fakeStoreProductDto == null ){
            throw new ProductNotFoundException(100L, "Product Not Found with id" + id);
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product getProductsByLimit(Long limit) {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products?limit=" + limit,
                FakeStoreProductDto.class);

        Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        System.out.println("Product details -> " + product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtosList =
                restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtosList){
            productList.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }

        return productList;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());

        //custom PUT call, as we need to return a valid response to user.
        RequestCallback requestCallback =
                restTemplate.httpEntityCallback( fakeStoreProductDto, FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate
                .execute("https://fakestoreapi.com/products/" + id,
                        HttpMethod.PUT, requestCallback, responseExtractor)
                .getBody();

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto1);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());

        //Below is the RestTemplate, implementation
//        RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
//        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters(), this.logger);
//        return this.execute(url, HttpMethod.PATCH, requestCallback, responseExtractor, (Object[])uriVariables);

        //custom Patch call
        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDtoResponseBody = restTemplate
                .execute("https://fakestoreapi.com/products/" + id,
                            HttpMethod.PATCH, requestCallback, responseExtractor)
                .getBody();

        if( fakeStoreProductDtoResponseBody == null )
            throw new ProductNotFoundException(1L, "Product Not found with id " + id);

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseBody);
    }

    @Override
    public Product deleteProductById(Long id) {

        //this.execute(url, HttpMethod.DELETE, (RequestCallback)null, (ResponseExtractor)null, (Object[])uriVariables);
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .execute("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, null);

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {

        if (fakeStoreProductDto == null) {
            return null;
        }

        Product product = new Product();
        // Map fields from FakeStoreProductDto to Product
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        // Create and set the Category object
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

}
