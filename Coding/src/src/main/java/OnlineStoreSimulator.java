import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreSimulator {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static CompletableFuture<JSONArray> fetchData(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com" + endpoint))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(JSONArray::new);
    }

    public static void main(String[] args) {
        CompletableFuture<JSONArray> products = fetchData("/posts");
        CompletableFuture<JSONArray> reviews = fetchData("/comments");
        CompletableFuture<JSONArray> inventory = fetchData("/todos");

        CompletableFuture.allOf(products, reviews, inventory).thenRun(() -> {
            try {
                JSONArray productData = products.get();
                System.out.println("Products:");
                for (int i = 0; i < productData.length() && i < 5; i++) {
                    JSONObject product = productData.getJSONObject(i);
                    System.out.println("ID: " + product.getInt("id") + ", Title: " + product.getString("title"));
                }

                JSONArray reviewData = reviews.get();
                System.out.println("\nReviews:");
                for (int i = 0; i < reviewData.length() && i < 5; i++) {
                    JSONObject review = reviewData.getJSONObject(i);
                    System.out.println("ID: " + review.getInt("id") + ", Review: " + review.getString("body"));
                }
                JSONArray inventoryData = inventory.get();
                System.out.println("\nInventory:");
                for (int i = 0; i < inventoryData.length() && i < 5; i++) {
                    JSONObject item = inventoryData.getJSONObject(i);
                    System.out.println("ID: " + item.getInt("id") + ", In stock: " + !item.getBoolean("completed"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}
