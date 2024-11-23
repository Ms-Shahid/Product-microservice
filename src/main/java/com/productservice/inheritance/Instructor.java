package com.productservice.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "single_Instr")
@DiscriminatorValue(value = "3")
public class Instructor extends User{

    private String specialization;
}
