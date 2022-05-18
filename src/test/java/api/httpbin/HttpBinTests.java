package api.httpbin;

import api.response.HttpResponse;
import helpers.ResourcesUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import product.responses.JsonResponseFormatBody;
import product.microservices.ResponseFormatsMicroservice;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static helpers.JsonRepresentation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpBinTests extends BaseTest {

    @Test
    void checkJsonSerialization() throws IOException, ParseException {
        ResponseFormatsMicroservice responseFormats = new ResponseFormatsMicroservice(httpClient);
        HttpResponse<JsonResponseFormatBody> response = responseFormats.returnJson();

        JSONObject expectedJson = ResourcesUtils.readAsJSONObject(Paths.get("json/jsonResponse.json"));
        JsonResponseFormatBody jsonResponseFormatBody = response.getBody();
        JSONObject actualJson = convertToJson(jsonResponseFormatBody);
        log.info("\nExp. json: {}\nAct. json: {}", expectedJson, actualJson);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    void checkGetMethod() {
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("company", "Godel");
        queryParameters.put("city", "Gomel");
        HttpResponse<JSONObject> response = httpClient.get("get",  queryParameters);

        String args = response.getBody().get("args").toString();
        log.info("Response args: {}", args);
        assertEquals(queryParameters.toString(), args);
    }

    @Test
    void checkPostMethod() throws IOException, ParseException {
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("company", "Godel");
        queryParameters.put("city", "Gomel");
        JSONObject requestBody = ResourcesUtils.readAsJSONObject(Paths.get("json/course.json"));
        HttpResponse<JSONObject> response = httpClient.post("post", queryParameters, requestBody);
        String form = response.getBody().get("form").toString();
        String expectedCourse = "Selenium Python";
        log.info("Response body: {}", form);
        assertTrue(form.contains(expectedCourse));
    }
}
