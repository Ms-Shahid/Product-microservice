package com.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private long id;
    private String title;
    private String desc;
    private  Double price;
    private Category category;


}
