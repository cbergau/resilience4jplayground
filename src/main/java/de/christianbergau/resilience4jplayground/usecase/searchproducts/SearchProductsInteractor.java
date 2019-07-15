package de.christianbergau.resilience4jplayground.usecase.searchproducts;

import de.christianbergau.resilience4jplayground.repository.ProductsRepository;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;

import java.io.IOException;
import java.util.function.Supplier;

public class SearchProductsInteractor {
    private SearchProductsPresenter presenter;
    private ProductsRepository repository;

    public SearchProductsInteractor(SearchProductsPresenter presenter, ProductsRepository repository) {
        this.presenter = presenter;
        this.repository = repository;
    }

    public void searchFor(String searchTerm) {
        /*
         This is the tricky, a little ugly, part. We have to convert the checked exception to a non-checked exception,
         because java can not handle checked exceptions in Supplier.

         If there is no checked exception, then "() -> repository.searchForProducts(searchTerm)" would be enough
         */
        Supplier<String> repositorySupplier = () -> {
            try {
                return repository.searchForProducts(searchTerm);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        };

        Supplier<String> decoratedSupplier = Decorators.ofSupplier(repositorySupplier)
                .withRetry(Retry.ofDefaults("backendService"))
                .withCircuitBreaker(CircuitBreaker.ofDefaults("backendService"))
                .withBulkhead(Bulkhead.ofDefaults("backendService"))
                .decorate();

        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "Hello from Recovery").get();

        System.out.println(result);
    }
}
