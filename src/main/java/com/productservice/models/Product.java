package com.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseProduct{

    private String description;
    private  Double price;
    @ManyToOne
    @JoinColumn
    private Category category;

}
