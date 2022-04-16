package product.microservices;

import framework.client.HttpClient;
import framework.response.HttpResponse;
import org.json.simple.JSONObject;
import product.responses.JsonResponseFormatBody;

import static helpers.JsonRepresentation.convertFromJson;

public class ResponseFormatsMicroservice extends BaseMicroservice {

    public ResponseFormatsMicroservice(HttpClient httpClient) {
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