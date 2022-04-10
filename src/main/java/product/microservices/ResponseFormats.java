package product.microservices;

import framework.api.client.HttpClient;
import framework.api.client.HttpResponse;
import org.json.simple.JSONObject;
import product.http.response.JsonResponseFormatBody;

import static framework.helpers.JsonRepresentation.convertFromJson;

public class ResponseFormats extends BaseMicroservice {

    public ResponseFormats(HttpClient httpClient) {
        super(httpClient);
    }

    public HttpResponse<JsonResponseFormatBody> returnJson() {
        String uri = "json";
        HttpResponse<JSONObject> httpResponse = httpClient.get(uri);
        JsonResponseFormatBody jsonResponseFormatBody = convertFromJson(httpResponse.getBody(),
                JsonResponseFormatBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(),
                jsonResponseFormatBody);
    }
}
