package Coding;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

class CompletableApi {
    private static final String PRODUCTS_API = "https://jsonplaceholder.typicode.com/albums";
    private static final String REVIEWS_API = "https://jsonplaceholder.typicode.com/comments";
    private static final String INVENTORY_API = "https://jsonplaceholder.typicode.com/photos";

    private static CompletableFuture<String> fetchData(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<String> productFuture = fetchData(client, PRODUCTS_API)
                .exceptionally(ex -> {
                    System.out.println("Error fetching products: " + ex.getMessage());
                    return "Default Products Data";  
                });
        CompletableFuture<String> reviewFuture = fetchData(client, REVIEWS_API)
                .exceptionally(ex -> {
                    System.out.println("Error fetching reviews: " + ex.getMessage());
                    return "Default Reviews Data"; 
                });
        CompletableFuture<String> inventoryFuture = fetchData(client, INVENTORY_API)
                .exceptionally(ex -> {
                    System.out.println("Error fetching reviews: " + ex.getMessage());
                    return "Default Inventory Data"; 
                });;

        CompletableFuture<Void> combinedFuture = CompletableFuture
                .allOf(productFuture, reviewFuture,inventoryFuture)
                .thenRun(() -> {
                   try{
                       String products = productFuture.get();
                       String reviews = reviewFuture.get();
                       String inventory = inventoryFuture.get();

                       // Simulate further processing of the combined data
                       System.out.println("Merged Data:");
                       System.out.println("Products: " + products.substring(0, 100) + "...");  // Truncate for brevity
                       System.out.println("Reviews: " + reviews.substring(0, 100) + "...");
                       System.out.println("Inventory: " + inventory.substring(0, 100) + "...");

                   }catch (Exception e) {
                       e.printStackTrace();
                   }
                });
        combinedFuture.join();
    }
}
