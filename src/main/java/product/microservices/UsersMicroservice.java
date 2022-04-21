package product.microservices;

import framework.client.HttpClient;
import framework.configuration.UriManager;
import framework.response.HttpResponse;
import org.json.simple.JSONObject;
import product.responses.UserBody;
import product.responses.UsersBody;

import static helpers.JsonRepresentation.convertFromJson;
import static java.lang.String.format;

public class UsersMicroservice extends BaseMicroservice {

    public UsersMicroservice(HttpClient httpClient) {
        super(httpClient);
    }

    public HttpResponse<UserBody> getUser(int id) {
        String uri = format(UriManager.getUserUri(), id);
        return getUserResponse(uri);
    }

    private HttpResponse<UserBody> getUserResponse(String uri) {
        HttpResponse<JSONObject> httpResponse = httpClient.get(uri);
        UserBody userBody = convertFromJson(httpResponse.getBody(), UserBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), userBody);
    }

    public HttpResponse<UsersBody> getUsersList(int page) {
        String uri = format(UriManager.getUserPageUri(), page);
        HttpResponse<JSONObject> httpResponse = httpClient.get(uri);
        UsersBody usersBody = convertFromJson(httpResponse.getBody(), UsersBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), usersBody);
    }
}
