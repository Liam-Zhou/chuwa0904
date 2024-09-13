package com.hw4.java8newfeature;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

class MyStream<T> {
    private List<T> elements;

    public MyStream(List<T> elements) {
        this.elements = elements;
    }

    // Implement the filter method
    public MyStream<T> filter(Predicate<? super T> predicate) {
        List<T> filteredElements = new ArrayList<>();
        for (T element : elements) {
            if (predicate.test(element)) {
                filteredElements.add(element);
            }
        }
        return new MyStream<>(filteredElements); // Return new stream with filtered elements
    }

    // Implement the map method
    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedElements = new ArrayList<>();
        for (T element : elements) {
            mappedElements.add(mapper.apply(element));
        }
        return new MyStream<>(mappedElements); // Return new stream with mapped elements
    }

    // Method to collect and print elements
    public void forEach(java.util.function.Consumer<? super T> action) {
        for (T element : elements) {
            action.accept(element);
        }
    }
}

public class CustomStreamExample {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Create a custom stream from the list of numbers
        MyStream<Integer> stream = new MyStream<>(numbers);

        // Filter and map using custom stream implementation
        stream.filter(n -> n % 2 == 0)               // Filter even numbers
                .map(n -> n * n)                       // Square the numbers
                .forEach(System.out::println);         // Print the squared numbers
    }
}

