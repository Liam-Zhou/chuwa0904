import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiFetcherExample {

    // Logger to log exception information
    private static final Logger logger = Logger.getLogger(ApiFetcherExample.class.getName());

    // Fetch products (here we'll simulate GitHub repos as "products")
    public static CompletableFuture<String> fetchProducts() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/albums"))  // Replace with a valid GitHub username
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(e -> {
                    logger.log(Level.SEVERE, "Error fetching products: ", e);  // Log the exception
                    return "Default Product Data";  // Return default value
                });
    }

    // Fetch reviews (simulated as comments on a repo)
    public static CompletableFuture<String> fetchReviews() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/comments"))  // Fake API for comments
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(e -> {
                    logger.log(Level.SEVERE, "Error fetching reviews: ", e);  // Log the exception
                    return "Default Review Data";  // Return default value
                });
    }

    // Fetch inventory (simulated as users from a public fake API)
    public static CompletableFuture<String> fetchInventory() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))  // Fake API for users
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(e -> {
                    logger.log(Level.SEVERE, "Error fetching inventory: ", e);  // Log the exception
                    return "Default Inventory Data";  // Return default value
                });
    }

    public static void main(String[] args) {
        // Fetch data from three APIs asynchronously
        CompletableFuture<String> productFuture = fetchProducts();
        CompletableFuture<String> reviewFuture = fetchReviews();
        CompletableFuture<String> inventoryFuture = fetchInventory();

        // Combine the fetched data when all are completed
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture);

        combinedFuture.thenRun(() -> {
            try {
                String products = productFuture.get();
                String reviews = reviewFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("Combined Data:");
                System.out.println("Products: " + products);
                System.out.println("Reviews: " + reviews);
                System.out.println("Inventory: " + inventory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();  // Block main thread until all tasks complete
    }
}
