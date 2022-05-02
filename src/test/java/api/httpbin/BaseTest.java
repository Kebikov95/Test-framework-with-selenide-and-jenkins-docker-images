package api.httpbin;

import api.client.HttpClient;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

abstract class BaseTest {

    protected static final Logger log = getLogger(ui.BaseTest.class);

    private final String BASE_URL = "https://httpbin.org/";
    protected final HttpClient httpClient = new HttpClient(BASE_URL);
}
