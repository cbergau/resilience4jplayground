package de.christianbergau.resilience4jplayground;

public class FailingBackendService {
    public String doSomething() {
        throw new RuntimeException();
    }
}
