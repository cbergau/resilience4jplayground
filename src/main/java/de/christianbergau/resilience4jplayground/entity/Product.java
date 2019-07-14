package de.christianbergau.resilience4jplayground.entity;

public class Product {
    private int id;
    private String title;
    private String description;

    public Product(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
