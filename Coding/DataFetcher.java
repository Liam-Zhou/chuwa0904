package chuwa0904.Coding;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataFetcher {

    // Logger instance to capture logs for any issues
    private static final Logger log = Logger.getLogger(DataFetcher.class.getName());

    // Fetch and limit display to only 2 products
    public static CompletableFuture<String> getProductData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.escuelajs.co/api/v1/products"))
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(DataFetcher::extractTwoItems)  // Call method to extract only 2 items
                .exceptionally(ex -> {
                    log.log(Level.SEVERE, "Failed to fetch product data: ", ex);
                    return "Fallback Product Data";  // Provide fallback data in case of error
                });
    }

    // Method to extract only the first two items from the JSON array
    private static String extractTwoItems(String jsonResponse) {
        // Find the index of the first two items in the JSON array
        int firstBracket = jsonResponse.indexOf("[");
        int secondItemEnd = findNthIndexOf(jsonResponse, '}', 2);

        if (firstBracket != -1 && secondItemEnd != -1) {
            return jsonResponse.substring(firstBracket, secondItemEnd + 1);  // Extract the first two items
        }
        return jsonResponse;  // Return full response if unable to find the two items
    }

    // Helper method to find the nth occurrence of a character
    private static int findNthIndexOf(String str, char ch, int n) {
        int pos = str.indexOf(ch);
        while (--n > 0 && pos != -1) {
            pos = str.indexOf(ch, pos + 1);
        }
        return pos;
    }
    

    // Simulated method to fetch review information (Placeholder comments)
    public static CompletableFuture<String> getCategoryData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.escuelajs.co/api/v1/categories"))  // Placeholder API for categories
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(DataFetcher::extractTwoItems)  // Call method to extract only 2 items
                .exceptionally(ex -> {
                    log.log(Level.SEVERE, "Failed to fetch review data: ", ex);
                    return "Fallback Review Data";  // Return default data in case of failure
                });
    }

    // Simulated method to fetch user information (Placeholder users)
    public static CompletableFuture<String> getUserData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.escuelajs.co/api/v1/users"))  // Placeholder API for inventory
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(DataFetcher::extractTwoItems)  // Call method to extract only 2 items
                .exceptionally(ex -> {
                    log.log(Level.SEVERE, "Failed to fetch inventory data: ", ex);
                    return "Fallback Inventory Data";  // Provide fallback data on failure
                });
    }

    public static void main(String[] args) {
        // Initiate fetching of product, review, and inventory data asynchronously
        CompletableFuture<String> productCF = getProductData();
        CompletableFuture<String>categoryCF = getCategoryData();
        CompletableFuture<String> userCF = getUserData();

        // Combine all the futures and process once all are complete
        CompletableFuture<Void> combinedTask = CompletableFuture.allOf(productCF, categoryCF, userCF);

        combinedTask.thenRun(() -> {
            try {
                String products = productCF.get();
                String categories = categoryCF.get();
                String user = userCF.get();

                System.out.println("Aggregated Data Output:");
                System.out.println("Products: ------>" + products);
                System.out.println("Categories: ----->" + categories);
                System.out.println("User: ----->" + user);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Error processing the combined data", e);
            }
        }).join();  // Ensure main thread waits for the async tasks to finish
    }
}
