package helpers;

import org.apache.logging.log4j.Logger;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;

public class Files {

    private static final Logger log = getLogger(Files.class);

    private Files() {
        throw new AssertionError(format("Creation of instance of %s is prohibited.", Files.class));
    }

    public static JsonPath readAsJson(Path path) throws IOException {
        String str = readAsString(path);
        return new JsonPath(str);
    }

    public static JSONObject readAsJSONObject(Path path) throws IOException, ParseException {
        String str = readAsString(path);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(str);
    }

    public static String readAsString(Path path) throws IOException {
        log.debug("Get file from resources by path {}", path);
        InputStream inputStream = getFileFromResourcesAsStream(path);
        return convertStreamToString(inputStream);
    }

    private static InputStream getFileFromResourcesAsStream(Path path) {
        ClassLoader classLoader = Files.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path.toString());

        if (inputStream == null)
            throw new IllegalArgumentException("The filed not found by path: " + path);

        return inputStream;
    }

    private static String convertStreamToString(InputStream inputStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int count;
            while ((count = reader.read()) != -1) {
                textBuilder.append((char) count);
            }
        }
        return textBuilder.toString();
    }
}
