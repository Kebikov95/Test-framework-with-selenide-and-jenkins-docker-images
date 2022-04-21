package product.microservices;

import framework.client.HttpClient;
import framework.response.HttpResponse;
import helpers.JsonRepresentation;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import product.bo.pojo.PersonPojo;
import product.responses.PersonBody;

import java.util.HashMap;
import java.util.Map;

import static helpers.JsonRepresentation.convertFromJson;
import static java.lang.String.format;

public class MethodsMicroservice extends BaseMicroservice {

    public MethodsMicroservice(HttpClient httpClient) {
        super(httpClient);
    }

    public HttpResponse<PersonBody> post() throws ParseException {
        String uri = "api/users";
        Map<String, String> queryParams = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        PersonPojo person = new PersonPojo("morpheus", "leader");
        JSONObject personJson = JsonRepresentation.convertToJson(person);
        HttpResponse<JSONObject> httpResponse = httpClient.post(uri, queryParams, headers, personJson);
        PersonBody morpheusBody = convertFromJson(httpResponse.getBody(), PersonBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), morpheusBody);
    }

    public HttpResponse<JSONObject> put() throws ParseException {
        String uri = "api/users";
        PersonPojo person = new PersonPojo("morpheus", "leader");
        JSONObject personJson = JsonRepresentation.convertToJson(person);
        HttpResponse<JSONObject> httpResponse = httpClient.put(uri, personJson);
        JSONObject morpheusBody = convertFromJson(httpResponse.getBody(), JSONObject.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), morpheusBody);
    }

    public HttpResponse<JSONObject> delete(int id) {
        String uri = format("api/users/%s", id);
        HttpResponse<JSONObject> httpResponse = httpClient.delete(uri);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), httpResponse.getBody());
    }
}
