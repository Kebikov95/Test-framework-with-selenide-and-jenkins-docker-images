package api.httpbin;

import framework.api.client.HttpResponse;
import framework.helpers.Files;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import product.http.response.JsonResponseFormatBody;
import product.microservices.ResponseFormats;

import java.io.IOException;
import java.nio.file.Paths;

import static framework.helpers.JsonRepresentation.convertToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpBinTests extends BaseTest {

    @Test
    void checkJsonSerialization() throws IOException, ParseException {
        ResponseFormats responseFormats = new ResponseFormats(httpClient);
        HttpResponse<JsonResponseFormatBody> response = responseFormats.returnJson();

        JSONObject expectedJson = Files.readAsJSONObject(Paths.get("json/jsonResponse.json"));
        JsonResponseFormatBody jsonResponseFormatBody = response.getBody();
        JSONObject jsonAfterSerialization = convertToJson(jsonResponseFormatBody);
        log.info("\nExp. json: {}\nAct. json: {}", expectedJson, jsonAfterSerialization);
        assertEquals(expectedJson, jsonAfterSerialization);
    }
}
