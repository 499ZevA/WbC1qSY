// 代码生成时间: 2025-09-17 04:17:24
package com.example.mathtools;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import javax.validation.constraints.NotNull;

@Controller("/math")
public class MathToolsController {

    // Adds two numbers
    @Get("/add/{a}/{b}")
    public double add(@PathVariable @NotNull Double a, @PathVariable @NotNull Double b) {
        return a + b;
    }

    // Subtracts one number from another
    @Get("/sub/{a}/{b}")
    public double subtract(@PathVariable @NotNull Double a, @PathVariable @NotNull Double b) {
        return a - b;
    }

    // Multiplies two numbers
    @Get("/mul/{a}/{b}")
    public double multiply(@PathVariable @NotNull Double a, @PathVariable @NotNull Double b) {
        return a * b;
    }

    // Divides one number by another, handles division by zero
    @Get("/div/{a}/{b}")
    public double divide(@PathVariable @NotNull Double a, @PathVariable @NotNull Double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    // Calculates the power of a number
    @Get("/pow/{base}/{exponent}")
    public double power(@PathVariable @NotNull Double base, @PathVariable @NotNull Double exponent) {
        return Math.pow(base, exponent);
    }

    /*
     * Additional mathematical operations can be added here.
     * Each method should handle its respective errors and exceptions appropriately.
     */
}
