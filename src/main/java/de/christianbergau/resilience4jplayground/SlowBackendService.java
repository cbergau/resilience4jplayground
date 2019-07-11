package de.christianbergau.resilience4jplayground;

public class SlowBackendService {
    public String doSomething() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "A";
    }
}
