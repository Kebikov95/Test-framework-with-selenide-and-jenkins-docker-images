package product.microservices;

import framework.client.HttpClient;
import framework.response.HttpResponse;
import helpers.ResourcesUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import product.responses.PersonBody;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static helpers.JsonRepresentation.convertFromJson;

public class MethodsMicroservice extends BaseMicroservice {

    public MethodsMicroservice(HttpClient httpClient) {
        super(httpClient);
    }

    public HttpResponse<PersonBody> post() throws ParseException, IOException {
        String uri = "api/users";
        Map<String, String> queryParams = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        JSONObject morpheusJson = ResourcesUtils.readAsJSONObject(Path.of("json/person.json"));
        HttpResponse<JSONObject> httpResponse = httpClient.post(uri, queryParams, headers, morpheusJson);
        PersonBody morpheusBody = convertFromJson(httpResponse.getBody(), PersonBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), morpheusBody);
    }

    public HttpResponse<JSONObject> put() throws ParseException, IOException {
        String uri = "api/users";
        JSONObject morpheusJson = ResourcesUtils.readAsJSONObject(Path.of("json/person.json"));
        HttpResponse<JSONObject> httpResponse = httpClient.put(uri, morpheusJson);
        JSONObject morpheusBody = convertFromJson(httpResponse.getBody(), JSONObject.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), morpheusBody);
    }

    public HttpResponse<JSONObject> delete() {
        String uri = "api/users/2";
        HttpResponse<JSONObject> httpResponse = httpClient.delete(uri);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), httpResponse.getBody());
    }
}
