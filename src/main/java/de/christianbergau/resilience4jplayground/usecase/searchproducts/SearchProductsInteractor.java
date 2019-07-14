package de.christianbergau.resilience4jplayground.usecase.searchproducts;

import de.christianbergau.resilience4jplayground.repository.ProductsRepository;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;

import java.util.function.Supplier;

public class SearchProductsInteractor {
    private SearchProductsPresenter presenter;
    private ProductsRepository repository;

    public SearchProductsInteractor(SearchProductsPresenter presenter, ProductsRepository repository) {
        this.presenter = presenter;
        this.repository = repository;
    }

    public void searchFor(String searchTerm) {
        Supplier<String> decoratedSupplier = Decorators.ofSupplier(() -> repository.searchForProducts(searchTerm))
                .withRetry(Retry.ofDefaults("backendService"))
                .withCircuitBreaker(CircuitBreaker.ofDefaults("backendService"))
                .withBulkhead(Bulkhead.ofDefaults("backendService"))
                .decorate();

        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "Hello from Recovery").get();

        System.out.println(result);
    }
}
