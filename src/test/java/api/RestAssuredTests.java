package api;

import api.content.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

class RestAssuredTests extends BaseTest {

    String placeId = "be4991b3bc9b4d0a718ac563c49ed483";
    String address = "Gomel";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
    }

    @Test
    void basePostRequestTest() {
        String response =
                given()
                    .queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body(Payload.getAddPlaceBody())
                .when()
                    .post("maps/api/place/add/json")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("scope", equalTo("APP"))
                        .header("server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        log.info("Response: {}", response);
        JsonPath json = new JsonPath(response);
        log.info("status: {}", json.getString("place_id"));
    }

    @Test
    void basePutRequestTest() {
        String response =
                given()
                        .queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(format(Payload.getAddAddressBody(), placeId, address))
                .when()
                        .put("maps/api/place/update/json")
                .then()
                        .assertThat().statusCode(200)
                        .body("msg", equalTo("Address successfull updated"))
                .extract().response().asString();

        log.info("Response: {}", response);
    }

    @Test
    void baseGetRequestTest() {
        String response =
                given()
                        .queryParam("key", "qaclick123")
                        .queryParam("place_id", placeId)
                .when()
                        .get("maps/api/place/get/json")
                .then()
                        .assertThat().statusCode(200)
                        .body("address", equalTo(address))
                .extract().response().asString();

        log.info("Response: {}", response);
        JsonPath json = new JsonPath(response);
        log.info("Address: {}", json.getString("address"));
    }
}
