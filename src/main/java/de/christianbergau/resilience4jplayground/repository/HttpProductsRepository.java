package de.christianbergau.resilience4jplayground.repository;

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
    public String searchForProducts(String searchFor) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/test"))
                .build();

        HttpResponse<String> response;

        //@TODO Is this the right approach? Converting Exceptions like that seems not right.
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
