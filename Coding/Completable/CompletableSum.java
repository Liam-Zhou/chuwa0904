package Coding;
import java.util.concurrent.CompletableFuture;

class CompletableExample {
    public static void main(String[] args) {
        int a = 5;
        int b = 8;
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> { return a + b;});

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {return a * b;});

        sumFuture.thenAccept(sum -> System.out.println("sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("product: " + product));
        
    }


}
