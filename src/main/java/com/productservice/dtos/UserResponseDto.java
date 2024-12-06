package com.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {

    private String name;
    private String email;
    private List<RolesDto> rolesDtos;
}
