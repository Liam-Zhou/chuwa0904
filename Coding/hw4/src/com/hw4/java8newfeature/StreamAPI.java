package com.hw4.java8newfeature;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(0, 3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().sorted().filter(integer -> integer != 0).map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);
    }
}
