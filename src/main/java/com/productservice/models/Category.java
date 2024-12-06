package com.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseProduct{

    private String description;
    @OneToMany(mappedBy = "category") // @OneToMany(mappedBy = "foreign-key-variable")
    private List<Product> productList;


}
