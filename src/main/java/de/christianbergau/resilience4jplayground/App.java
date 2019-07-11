package de.christianbergau.resilience4jplayground;

import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;

import java.util.function.Supplier;

public class App {
    public static void main(String... args) {
        FailingBackendService service = new FailingBackendService();

        Supplier<String> decoratedSupplier = Decorators.ofSupplier(service::doSomething)
                .withRetry(Retry.ofDefaults("failingBackendService"))
                .decorate();

        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "Hello from Recovery")
                .get();

        System.out.println(result);
    }
}
