import java.util.concurrent.CompletableFuture;

public class MyCompletableFuture{
    public static void main(String[] args) {
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return 5 + 10;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return 5 * 10;
        });


        sumFuture.thenAcceptBoth(productFuture, (sum, product) -> {
            System.out.println("Sum: " + sum);
            System.out.println("Product: " + product);
        }).join();
    }
}