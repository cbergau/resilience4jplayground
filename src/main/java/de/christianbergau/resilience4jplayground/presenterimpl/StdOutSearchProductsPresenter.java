package de.christianbergau.resilience4jplayground.presenterimpl;

import de.christianbergau.resilience4jplayground.usecase.searchproducts.SearchProductsPresenter;
import de.christianbergau.resilience4jplayground.usecase.searchproducts.SearchProductsResponse;

public class StdOutSearchProductsPresenter implements SearchProductsPresenter {
    @Override
    public void present(SearchProductsResponse response) {
        System.out.println(response);
    }
}
