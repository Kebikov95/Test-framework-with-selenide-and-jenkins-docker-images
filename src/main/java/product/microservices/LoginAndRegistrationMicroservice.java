package product.microservices;

import framework.client.HttpClient;
import framework.configuration.UriManager;
import framework.response.HttpResponse;
import helpers.JsonRepresentation;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import product.bo.pojo.UserPojo;

import java.util.HashMap;
import java.util.Map;

public class LoginAndRegistrationMicroservice extends BaseMicroservice {

    private final Map<String, String> queryParams = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public LoginAndRegistrationMicroservice(HttpClient httpClient) {
        super(httpClient);
        headers.put("Content-Type", "application/json");
    }

    public HttpResponse<JSONObject> register(UserPojo user) throws ParseException {
        String uri = UriManager.getRegisterUri();
        JSONObject userBody = JsonRepresentation.convertToJson(user);
        HttpResponse<JSONObject> httpResponse = httpClient.post(uri, queryParams, headers, userBody);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), httpResponse.getBody());
    }

    public HttpResponse<JSONObject> login(UserPojo user) throws ParseException {
        String uri = UriManager.getLoginUri();
        JSONObject userBody = JsonRepresentation.convertToJson(user);
        HttpResponse<JSONObject> httpResponse = httpClient.post(uri, queryParams, headers, userBody);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), httpResponse.getBody());
    }
}
