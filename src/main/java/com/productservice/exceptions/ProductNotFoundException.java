package com.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductNotFoundException extends Exception{

    private Long id;
    private String message;

    public ProductNotFoundException(Long id, String message){
        super(message);
        this.id = id;
    }

}
