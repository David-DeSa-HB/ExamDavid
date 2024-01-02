package fr.dawid.test.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientService {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String get(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();

        return send(httpRequest, url);
    }

    public String post(String url, String body) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build();

        return send(httpRequest, url);
    }

    private String send(HttpRequest httpRequest, String url) {
        String content = null;
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            content = response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error while calling : " + url);
            System.out.println(e.getMessage());
        }
        return content;
    }
}
