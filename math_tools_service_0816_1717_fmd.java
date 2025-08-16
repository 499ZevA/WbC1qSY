// 代码生成时间: 2025-08-16 17:17:51
package com.example.mathtools;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import javax.validation.constraints.NotNull;

@Controller("/math")
public class MathToolsService {

    // Perform addition of two numbers
    @Get("/add/{a}/{b}")
    public double add(@PathVariable @NotNull double a, @PathVariable @NotNull double b) {
        return a + b;
    }

    // Perform subtraction of two numbers
    @Get("/sub/{a}/{b}")
    public double subtract(@PathVariable @NotNull double a, @PathVariable @NotNull double b) {
        return a - b;
    }

    // Perform multiplication of two numbers
    @Get("/mul/{a}/{b}")
    public double multiply(@PathVariable @NotNull double a, @PathVariable @NotNull double b) {
        return a * b;
    }

    // Perform division of two numbers with error handling
    @Get("/div/{a}/{b}")
    public double divide(@PathVariable @NotNull double a, @PathVariable @NotNull double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    // Power of two numbers
    @Get("/pow/{a}/{b}")
    public double power(@PathVariable @NotNull double a, @PathVariable @NotNull double b) {
        return Math.pow(a, b);
    }

    // Main method for standalone testing
    public static void main(String[] args) {
        // This code is for demonstration purposes and should be removed in production
        MathToolsService service = new MathToolsService();
        System.out.println("Addition: 2 + 3 = " + service.add(2, 3));
        System.out.println("Subtraction: 5 - 2 = " + service.subtract(5, 2));
        System.out.println("Multiplication: 4 * 3 = " + service.multiply(4, 3));
        System.out.println("Division: 6 / 2 = " + service.divide(6, 2));
        System.out.println("Power: 2^3 = " + service.power(2, 3));
    }
}