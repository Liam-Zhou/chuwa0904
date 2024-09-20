package com.hw4.java8newfeature;

import java.util.Arrays;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        // Variable outside the lambda
        int multiplier = 2;
        // Or final int multiplier = 2;
        // List of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using the unchanged variable inside a lambda
        numbers.forEach(number -> {
            int result = number * multiplier;  // Allowed, because multiplier is effectively final
            System.out.println(result);
        });
        // multiplier = 3;
    }
}
