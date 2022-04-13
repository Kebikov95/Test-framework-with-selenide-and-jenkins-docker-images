package product.microservices;

import framework.client.HttpClient;
import framework.response.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import product.bo.pojo.PersonPojo;
import product.responses.PersonBody;

import static helpers.JsonRepresentation.convertFromJson;
import static helpers.JsonRepresentation.convertToJson;

public class MethodsMicroservice extends BaseMicroservice {

    public MethodsMicroservice(HttpClient httpClient) {
        super(httpClient);
    }

    public HttpResponse<PersonBody> post() throws ParseException {
        String uri = "api/users";
        PersonPojo morpheus = new PersonPojo("morpheus", "leader");
        JSONObject morpheusJson = convertToJson(morpheus);
        HttpResponse<JSONObject> httpResponse = httpClient.post(uri, morpheusJson);
        PersonBody morpheusBody = convertFromJson(httpResponse.getBody(), PersonBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), morpheusBody);
    }
}
