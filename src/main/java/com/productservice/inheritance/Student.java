package com.productservice.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "single_stud")
@DiscriminatorValue(value = "1")
public class Student extends User{
    private String batch;
    private String course;
}
