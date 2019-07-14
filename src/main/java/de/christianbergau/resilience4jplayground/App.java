package de.christianbergau.resilience4jplayground;

import de.christianbergau.resilience4jplayground.presenterimpl.StdOutSearchProductsPresenter;
import de.christianbergau.resilience4jplayground.repository.HttpProductsRepository;
import de.christianbergau.resilience4jplayground.repository.ProductsRepository;
import de.christianbergau.resilience4jplayground.usecase.searchproducts.SearchProductsInteractor;
import de.christianbergau.resilience4jplayground.usecase.searchproducts.SearchProductsPresenter;

import java.net.http.HttpClient;

public class App {
    public static void main(String... args) {
        SearchProductsPresenter presenter = new StdOutSearchProductsPresenter();
        ProductsRepository repository = new HttpProductsRepository(HttpClient.newHttpClient());
        SearchProductsInteractor interactor = new SearchProductsInteractor(presenter, repository);
        interactor.searchFor("Shoes");
    }
}
