package com.productservice.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "single_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User{

    private String company;
    private double avgRating;
}
