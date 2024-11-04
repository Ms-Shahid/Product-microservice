package com.productservice.dtos;

import com.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;

}
