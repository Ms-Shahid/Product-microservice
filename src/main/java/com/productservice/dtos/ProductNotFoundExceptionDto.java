package com.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductNotFoundExceptionDto {

    Long errorCode;
    String message;
}
