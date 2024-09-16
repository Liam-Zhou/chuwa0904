package chuwa0904.Coding;

import java.util.concurrent.CompletableFuture;

public class CalSum {


    public static void main(String[] args) {
        int num1 = 100;
        int num2 = 200;
        // calculate the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return num1 + num2;
        });

        //  calculate the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return num1 * num2;
        });

        // print the results
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));

    }
}
