package api;

import api.content.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

class RestAssuredLibraryTests extends BaseTest {

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://216.10.245.166";
    }

    @ParameterizedTest
    @MethodSource("isbnListProvider")
    void addBook(String isbn, String value) {
        String response =
                given()
                        .header("Content-Type", "application/json")
                        .body(Payload.getAddBookBody(isbn, value))
                .when()
                        .post("/Library/Addbook.php")
                .then()
                        .assertThat().statusCode(200)
                .extract().response().asString();
        String id = new JsonPath(response).get("ID");

        log.info("Id: {}", id);
    }

    static Stream<Arguments> isbnListProvider() {
        return Stream.of(
                Arguments.of("isbn", "13125"),
                Arguments.of("isbn", "13126")
        );
    }
}
