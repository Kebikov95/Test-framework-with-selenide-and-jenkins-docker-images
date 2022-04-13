package product.microservices;

import framework.client.HttpClient;
import framework.response.HttpResponse;
import org.json.simple.JSONObject;
import product.responses.UserBody;
import product.responses.UsersBody;

import static helpers.JsonRepresentation.convertFromJson;

public class UsersMicroservice extends BaseMicroservice {

    public UsersMicroservice(HttpClient httpClient) {
        super(httpClient);
    }

    public HttpResponse<UserBody> getUser() {
        String uri = "api/users/2";
        return getUserResponse(uri);
    }

    public HttpResponse<UserBody> getNotFoundUser() {
        String uri = "api/users/23";
        return getUserResponse(uri);
    }

    private HttpResponse<UserBody> getUserResponse(String uri) {
        HttpResponse<JSONObject> httpResponse = httpClient.get(uri);
        UserBody userBody = convertFromJson(httpResponse.getBody(), UserBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), userBody);
    }

    public HttpResponse<UsersBody> getUsersList() {
        String uri = "api/users?page=2";
        HttpResponse<JSONObject> httpResponse = httpClient.get(uri);
        UsersBody usersBody = convertFromJson(httpResponse.getBody(), UsersBody.class);
        return new HttpResponse<>(httpResponse.getStatusCode(), httpResponse.getHeaders(), usersBody);
    }
}
