package de.christianbergau.resilience4jplayground.repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpProductsRepository implements ProductsRepository {
    private HttpClient client;

    public HttpProductsRepository(HttpClient client) {
        this.client = client;
    }

    @Override
    public String searchForProducts(String searchFor) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/test"))
                .build();

        HttpResponse<String> response;

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // normally map the response to a list of entities here
        return response.body();
    }
}
