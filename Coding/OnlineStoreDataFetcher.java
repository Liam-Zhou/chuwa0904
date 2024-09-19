import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcher {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        // Fetch data from three APIs asynchronously with exception handling
        CompletableFuture<String> productsFuture = fetchDataFromAPI("https://jsonplaceholder.typicode.com/users")
                .exceptionally(e -> {
                    System.err.println("Error fetching products data: " + e.getMessage());
                    e.printStackTrace();  // Print stack trace to see detailed error
                    return "Default Products Data";
                });

        CompletableFuture<String> reviewsFuture = fetchDataFromAPI("https://jsonplaceholder.typicode.com/users")
                .exceptionally(e -> {
                    System.err.println("Error fetching reviews data: " + e.getMessage());
                    e.printStackTrace();  // Print stack trace to see detailed error
                    return "Default Reviews Data";
                });

        CompletableFuture<String> inventoryFuture = fetchDataFromAPI("https://jsonplaceholder.typicode.com/error") // Incorrect URL for testing
                .exceptionally(e -> {
                    System.err.println("Error fetching inventory data: " + e.getMessage());
                    e.printStackTrace();  // Print stack trace to see detailed error
                    return "Default Inventory Data";
                });

        // Combine the results when all tasks are completed
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // Process the combined result
        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                // Simulating merging data for further processing
                System.out.println("Products Data: " + products);
                System.out.println("Reviews Data: " + reviews);
                System.out.println("Inventory Data: " + inventory);

                // Further processing can be done here...

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join(); // Wait until all futures are done
    }

    private static CompletableFuture<String> fetchDataFromAPI(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Fetch data asynchronously
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(e -> {
                    // Print stack trace to the error stream
                    System.err.println("Exception occurred while fetching data from URL: " + url);
                    e.printStackTrace();  // Print stack trace for debugging
                    // Return a default error message
                    return "Error fetching data from " + url;
                });
    }
}
