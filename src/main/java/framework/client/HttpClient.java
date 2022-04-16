package framework.client;

import framework.response.HttpResponse;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

public class HttpClient {

    private static final String NOT_NULL_URL_MESSAGE = "Relative URL cannot be null.";
    private static final String NOT_NULL_QUERY_PARAMETERS_MESSAGE = "Query parameters cannot be null.";
    private static final String NOT_NULL_HEADERS_MESSAGE = "Request headers cannot be null.";
    private static final String NOT_NULL_REQUEST_BODY_MASSAGE = "Request body cannot be null";
    private static final Logger log = getLogger(HttpClient.class);

    private final Map<String, String> defaultQueryParameters = new HashMap<>();
    private final Map<String, String> defaultHeaders = new HashMap<>();

    public HttpClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public HttpResponse<JSONObject> get(String relativeUrl, Map<String, String> queryParameters,
                                        Map<String, String> requestHeaders) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        Objects.requireNonNull(queryParameters, NOT_NULL_QUERY_PARAMETERS_MESSAGE);
        Objects.requireNonNull(requestHeaders, NOT_NULL_HEADERS_MESSAGE);
        RequestSpecification request = RestAssured.given();
        request.queryParams(queryParameters);
        request.headers(requestHeaders);
        Response response = request.get(relativeUrl);
        return convertHttpResponse(response);
    }

    public HttpResponse<JSONObject> get(String relativeUrl, Map<String, String> queryParameters) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        Objects.requireNonNull(queryParameters, NOT_NULL_QUERY_PARAMETERS_MESSAGE);
        return get(relativeUrl, queryParameters, defaultHeaders);
    }

    public HttpResponse<JSONObject> get(String relativeUrl) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        return get(relativeUrl, defaultQueryParameters, defaultHeaders);
    }

    public HttpResponse<JSONObject> post(String relativeUrl, JSONObject requestBody) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        return post(relativeUrl, defaultQueryParameters, defaultHeaders, requestBody);
    }

    public HttpResponse<JSONObject> post(String relativeUrl, Map<String, String> queryParameters, JSONObject requestBody) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        Objects.requireNonNull(queryParameters, NOT_NULL_QUERY_PARAMETERS_MESSAGE);
        return post(relativeUrl, queryParameters, defaultHeaders, requestBody);
    }

    public HttpResponse<JSONObject> post(String relativeUrl, Map<String, String> queryParameters,
                                         Map<String, String> requestHeaders, JSONObject requestBody) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        Objects.requireNonNull(queryParameters, NOT_NULL_QUERY_PARAMETERS_MESSAGE);
        Objects.requireNonNull(requestBody, NOT_NULL_REQUEST_BODY_MASSAGE);
        Objects.requireNonNull(requestHeaders, NOT_NULL_HEADERS_MESSAGE);
        RequestSpecification request = RestAssured.given();
        request.queryParams(queryParameters);
        request.headers(requestHeaders);
        request.body(requestBody);
        Response response = request.post(relativeUrl);
        return convertHttpResponse(response);
    }

    public HttpResponse<JSONObject> put(String relativeUrl, Map<String, String> requestHeaders, JSONObject requestBody) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        Objects.requireNonNull(requestHeaders, NOT_NULL_HEADERS_MESSAGE);
        Objects.requireNonNull(requestBody, NOT_NULL_REQUEST_BODY_MASSAGE);
        RequestSpecification request = RestAssured.given();
        request.headers(requestHeaders);
        request.body(requestBody);
        Response response = request.put(relativeUrl);
        return convertHttpResponse(response);
    }

    public HttpResponse<JSONObject> put(String relativeUrl, JSONObject requestBody) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        Objects.requireNonNull(requestBody, NOT_NULL_REQUEST_BODY_MASSAGE);
        return put(relativeUrl, defaultHeaders, requestBody);
    }

    public HttpResponse<JSONObject> delete(String relativeUrl, Map<String, String> requestHeaders) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        Objects.requireNonNull(requestHeaders, NOT_NULL_HEADERS_MESSAGE);
        RequestSpecification request = RestAssured.given();
        request.headers(requestHeaders);
        Response response = request.delete(relativeUrl);
        return convertHttpResponse(response);
    }

    public HttpResponse<JSONObject> delete(String relativeUrl) {
        Objects.requireNonNull(relativeUrl, NOT_NULL_URL_MESSAGE);
        return delete(relativeUrl, defaultHeaders);
    }

    private HttpResponse<JSONObject> convertHttpResponse(Response response) {
        JSONObject body;
        try {
            body = response.body().as(JSONObject.class);
        } catch (IllegalStateException e) {
            log.debug("The body has been empty.");
            body = new JSONObject();
        }
        int responseCode = response.getStatusCode();
        Map<String, String> headers = convertHeaders(response.headers());
        log.debug("The response status code: {},\nThe response body: {}", responseCode, body);
        return new HttpResponse<>(responseCode, headers, body);
    }

    private Map<String, String> convertHeaders(Headers headers) {
        Map<String, String> headersMap = new HashMap<>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue());
        }
        log.debug("The convert headers: {}", headersMap);
        return headersMap;
    }
}
