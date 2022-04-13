package api.reqres;

import framework.enums.HttpStatusCode;
import framework.response.HttpResponse;
import jdk.jfr.Description;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import product.microservices.MethodsMicroservice;
import product.microservices.UsersMicroservice;
import product.responses.PersonBody;
import product.responses.UserBody;
import product.responses.UsersBody;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReqResTests extends BaseTest {

    @Test
    @Description("check GET method for getting user list")
    void checkGetUserList() {
        UsersMicroservice userList = new UsersMicroservice(httpClient);
        HttpResponse<UsersBody> usersBodyHttpResponse = userList.getUsersList();
        assertEquals(HttpStatusCode.OK.getValue(), usersBodyHttpResponse.getStatusCode());
        int expectedDataSize = 6;
        int actualDataSize = usersBodyHttpResponse
                .getBody()
                .getData()
                .size();
        assertEquals(expectedDataSize, actualDataSize);
        log.info("User's data size: {}", actualDataSize);
    }

    @Test
    @Description("check GET method for getting single user")
    void checkGetSingleUser() {
        UsersMicroservice userList = new UsersMicroservice(httpClient);
        HttpResponse<UserBody> userBodyHttpResponse = userList.getUser();
        assertEquals(HttpStatusCode.OK.getValue(), userBodyHttpResponse.getStatusCode());
        String expectedAvatarLink = "https://reqres.in/img/faces/2-image.jpg";
        String actualAvatarLink = userBodyHttpResponse
                .getBody()
                .getData()
                .getAvatar();
        assertEquals(expectedAvatarLink, actualAvatarLink);
        log.info("User avatar link: {}", actualAvatarLink);
    }

    @Test
    @Description("check GET method for verify not found user")
    void checkNotFoundUser() {
        UsersMicroservice userList = new UsersMicroservice(httpClient);
        HttpResponse<UserBody> userBodyHttpResponse = userList.getNotFoundUser();
        assertEquals(HttpStatusCode.NOT_FOUND.getValue(), userBodyHttpResponse.getStatusCode());
        log.info("Status code for not found user: {}", userBodyHttpResponse.getStatusCode());
    }

    @Test
    @Description("check POST method for creating person")
    void checkPostMethod() throws ParseException {
        MethodsMicroservice postMethod = new MethodsMicroservice(httpClient);
        HttpResponse<PersonBody> personBodyHttpResponse = postMethod.post();
        log.info(personBodyHttpResponse.getBody());
    }
}
