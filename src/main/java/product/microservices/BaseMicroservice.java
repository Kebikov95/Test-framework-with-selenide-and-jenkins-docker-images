package product.microservices;

import framework.client.HttpClient;

import java.util.Objects;

public class BaseMicroservice {

    protected HttpClient httpClient;

    public BaseMicroservice(HttpClient httpClient) {
        Objects.requireNonNull(httpClient, "Http client cannot be null");
        this.httpClient = httpClient;
    }
}
