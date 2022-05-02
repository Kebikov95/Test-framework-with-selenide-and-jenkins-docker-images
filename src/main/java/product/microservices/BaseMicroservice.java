package product.microservices;

import api.client.HttpClient;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

public class BaseMicroservice {

    protected static final Logger log = getLogger(BaseMicroservice.class);

    protected HttpClient httpClient;

    public BaseMicroservice(HttpClient httpClient) {
        Objects.requireNonNull(httpClient, "Http client cannot be null");
        this.httpClient = httpClient;
    }
}
