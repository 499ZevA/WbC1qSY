// 代码生成时间: 2025-08-26 15:25:54
package com.example.mathtools;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.exceptions.HttpStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Controller("/math")
public class MathToolService {

    // Method to add two numbers
    @Get("/add")
    public BigDecimal add(
        @NotNull @Min(0) BigDecimal number1,
        @NotNull @Min(0) BigDecimal number2
    ) {
        return number1.add(number2);
    }

    // Method to subtract two numbers
    @Get("/subtract")
    public BigDecimal subtract(
        @NotNull @Min(0) BigDecimal number1,
        @NotNull @Min(0) BigDecimal number2
    ) {
        return number1.subtract(number2);
    }

    // Method to multiply two numbers
    @Get("/multiply")
    public BigDecimal multiply(
        @NotNull @Min(0) BigDecimal number1,
        @NotNull @Min(0) BigDecimal number2
    ) {
        return number1.multiply(number2);
    }

    // Method to divide two numbers
    @Get("/divide")
    public BigDecimal divide(
        @NotNull BigDecimal number1,
        @NotNull @Min(1) BigDecimal number2
    ) {
        if (number2.compareTo(BigDecimal.ZERO) == 0) {
            throw new HttpStatusException(400, "Cannot divide by zero");
        }
        return number1.divide(number2);
    }

    // Method to calculate the power of a number
    @Get("/power")
    public BigDecimal power(
        @NotNull @Min(0) BigDecimal base,
        @NotNull @Min(0) BigDecimal exponent
    ) {
        return base.pow(exponent.intValue());
    }

    // Method to calculate the square root of a number
    @Get("/sqrt")
    public BigDecimal sqrt(
        @NotNull @Min(0) BigDecimal number
    ) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new HttpStatusException(400, "Cannot calculate square root of a negative number");
        }
        return number.sqrt();
    }
}
