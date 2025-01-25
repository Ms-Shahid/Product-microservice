package com.productservice.sampletest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    /*
    * 3C -> Create, Call, Check
    * */
    @Test
    void whenAddingTwoIntegerThenReturnExpected() {

        //Create(Arrange)
        int a = 10;
        int b = 15;
        Calculator calculator = new Calculator();

        //Call(Act)
        int result = a + b;

        //Check(Assert)
        Assertions.assertEquals(30, result);

    }

    @Test
    void whenDividingTwoIntegerReturnExpected(){

        int a = 0;
        int b = 0;
        Calculator calculator = new Calculator();

        Assertions.assertThrows(ArithmeticException.class, () -> calculator.divide(a, b));

    }
}