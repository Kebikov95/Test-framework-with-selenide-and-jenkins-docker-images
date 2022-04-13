package api.reqres;

import framework.client.HttpClient;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

abstract class BaseTest {

    protected static final Logger log = getLogger(ui.BaseTest.class);

    private final String BASE_URL = "https://reqres.in/";
    protected final HttpClient httpClient = new HttpClient(BASE_URL);
}
