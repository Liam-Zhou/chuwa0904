package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

public class completeFutureFetchAPI {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public void get(String myrepo) {
        CompletableFuture<String> personalRepos = CompletableFuture.supplyAsync(() -> fetchRepos(myrepo));
        CompletableFuture<String> contributors = personalRepos.thenApplyAsync(reposJson -> {
            JSONArray reposArray = new JSONArray(reposJson);
            for (int i = 0; i < reposArray.length(); i++) {
                JSONObject repo = reposArray.getJSONObject(i);
                String repoName = repo.getString("name");
                String contributorsUrl = repo.getString("contributors_url");

                // Fetch contributors for each repository asynchronously
                CompletableFuture.supplyAsync(() -> fetchContributors(contributorsUrl))
                        .thenAccept(contributorsJson -> {
                            if (contributorsJson.startsWith("[")) {
                                JSONArray contributorsArray = new JSONArray(contributorsJson);
                                System.out.println("Contributors for repository '" + repoName + "':");
                                for (int j = 0; j < contributorsArray.length(); j++) {
                                    JSONObject contributor = contributorsArray.getJSONObject(j);
                                    System.out.println("Contributor: " + contributor.getString("login"));
                                }
                            } else if (contributorsJson.startsWith("{")) {
                                System.out.println("Received an object instead of an array for contributors. Response: " + contributorsJson);
                            } else {
                                System.out.println("Unexpected response format: " + contributorsJson);
                            }
                        }).join(); // Wait for this task to complete
            }
            return null; // Return null since the function expects Void
        });
        // Merge tasks
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(personalRepos, contributors);
        combinedFuture.join(); // Block until all the tasks are done
    }
        // Fetch repositories (Products)
    public static String fetchRepos(String userName) {
        String url = "https://api.github.com/users/" + userName + "/repos";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    // Fetch contributors for each repository (Reviews)
    public static String fetchContributors(String contributorsUrl) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(contributorsUrl))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching contributors";
        }
    }
}