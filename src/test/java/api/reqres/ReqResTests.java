package api.reqres;

import framework.enums.HttpStatusCode;
import framework.response.HttpResponse;
import helpers.ResourcesUtils;
import jdk.jfr.Description;
import org.assertj.core.util.Strings;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import product.microservices.MethodsMicroservice;
import product.microservices.LoginAndRegistrationMicroservice;
import product.microservices.UsersMicroservice;
import product.responses.PersonBody;
import product.responses.UserBody;
import product.responses.UsersBody;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
    void checkPostMethod() throws ParseException, IOException {
        MethodsMicroservice methods = new MethodsMicroservice(httpClient);
        HttpResponse<PersonBody> personBodyHttpResponse = methods.post();
        log.info(personBodyHttpResponse.getBody());
        log.info(personBodyHttpResponse.getStatusCode());
        assertEquals(HttpStatusCode.CREATED.getValue(), personBodyHttpResponse.getStatusCode());
        assertEquals("morpheus", personBodyHttpResponse.getBody().getName());
    }

    @Test
    @Description("check PUT method for creating person")
    void checkPutMethod() throws ParseException, IOException {
        MethodsMicroservice methods = new MethodsMicroservice(httpClient);
        HttpResponse<JSONObject> response = methods.put();
        JSONObject body = response.getBody();
        log.info("Status code: {}", response.getStatusCode());
        assertEquals(HttpStatusCode.OK.getValue(), response.getStatusCode());
        log.info("The body after PUT method: {}", body);
        LocalDate dateTime = LocalDate.now();
        assertTrue(body.get("updatedAt").toString().contains(dateTime.toString()));
    }

    @Test
    @Description("check DELETE method for creating person")
    void checkPatchMethod() {
        MethodsMicroservice methods = new MethodsMicroservice(httpClient);
        HttpResponse<JSONObject> response = methods.delete();
        log.info("Status code: {}", response.getStatusCode());
        assertEquals(HttpStatusCode.NO_CONTENT.getValue(), response.getStatusCode());
    }

    @Test
    @Description("check successful registration")
    void checkSuccessfulRegistration() throws IOException, ParseException {
        LoginAndRegistrationMicroservice registration = new LoginAndRegistrationMicroservice(httpClient);
        JSONObject validUserJson = ResourcesUtils.readAsJSONObject(Path.of("json/validUser.json"));
        HttpResponse<JSONObject> response = registration.register(validUserJson);
        String token = response.getBody().get("token").toString();
        log.info("Status code: {}", response.getStatusCode());
        log.info("Token: {}", token);
        assertEquals(HttpStatusCode.OK.getValue(), response.getStatusCode());
        assertFalse(Strings.isNullOrEmpty(token));
    }

    @Test
    @Description("check unsuccessful registration")
    void checkUnsuccessfulRegistration() throws IOException, ParseException {
        LoginAndRegistrationMicroservice registration = new LoginAndRegistrationMicroservice(httpClient);
        JSONObject validUserJson = ResourcesUtils.readAsJSONObject(Path.of("json/unvalidUser.json"));
        HttpResponse<JSONObject> response = registration.register(validUserJson);
        String errorMessage = response.getBody().get("error").toString();
        log.info("Status code: {}", response.getStatusCode());
        log.info("Error message: {}", response.getBody());
        assertEquals(HttpStatusCode.BAD_REQUEST.getValue(), response.getStatusCode());
        assertEquals("Missing password", errorMessage);
    }

    @Test
    @Description("check successful login")
    void checkSuccessfulLogin() throws IOException, ParseException {
        LoginAndRegistrationMicroservice login = new LoginAndRegistrationMicroservice(httpClient);
        JSONObject validUserJson = ResourcesUtils.readAsJSONObject(Path.of("json/validUser.json"));
        HttpResponse<JSONObject> response = login.login(validUserJson);
        String token = response.getBody().get("token").toString();
        log.info("Status code: {}", response.getStatusCode());
        log.info("Token: {}", token);
        assertEquals(HttpStatusCode.OK.getValue(), response.getStatusCode());
        assertFalse(Strings.isNullOrEmpty(token));
    }

    @Test
    @Description("check unsuccessful login")
    void checkUnsuccessfulLogin() throws IOException, ParseException {
        LoginAndRegistrationMicroservice registration = new LoginAndRegistrationMicroservice(httpClient);
        JSONObject validUserJson = ResourcesUtils.readAsJSONObject(Path.of("json/unvalidUser.json"));
        HttpResponse<JSONObject> response = registration.login(validUserJson);
        String errorMessage = response.getBody().get("error").toString();
        log.info("Status code: {}", response.getStatusCode());
        log.info("Error message: {}", response.getBody());
        assertEquals(HttpStatusCode.BAD_REQUEST.getValue(), response.getStatusCode());
        assertEquals("Missing password", errorMessage);
    }
}