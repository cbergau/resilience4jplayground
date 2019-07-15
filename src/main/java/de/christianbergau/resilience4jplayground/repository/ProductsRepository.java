package de.christianbergau.resilience4jplayground.repository;

import java.io.IOException;

public interface ProductsRepository {
    public String searchForProducts(String searchFor) throws IOException, InterruptedException;
}
