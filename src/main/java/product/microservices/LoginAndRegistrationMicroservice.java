package product.microservices;

import framework.client.HttpClient;
import framework.response.HttpResponse;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginAndRegistrationMicroservice extends BaseMicroservice {

    private final Map<String, String> queryParams = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public LoginAndRegistrationMicroservice(HttpClient httpClient) {
        super(httpClient);
        headers.put("Content-Type", "application/json");
    }

    public HttpResponse<JSONObject> register(JSONObject userCredentialJson) {
        String uri = "api/register";
        HttpResponse<JSONObject> httpResponse = httpClient.post(uri, queryParams, headers, userCredentialJson);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), httpResponse.getBody());
    }

    public HttpResponse<JSONObject> login(JSONObject userCredentialJson) {
        String uri = "api/login";
        HttpResponse<JSONObject> httpResponse = httpClient.post(uri, queryParams, headers, userCredentialJson);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), httpResponse.getBody());
    }
}
