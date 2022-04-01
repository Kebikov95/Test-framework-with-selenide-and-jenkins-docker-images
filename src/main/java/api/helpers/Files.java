package api.helpers;

import org.apache.logging.log4j.Logger;

import java.io.*;
import io.restassured.path.json.JsonPath;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.apache.logging.log4j.LogManager.getLogger;

public class Files {

    private static final Logger log = getLogger(Files.class);

    private Files() {
    }

    public static JsonPath readJson(String pathToFile) throws IOException {
        String str = readString(pathToFile);
        return new JsonPath(str);
    }

    public static String readString(String pathToFile) throws IOException {
        log.debug("Get file from resources by path {}", pathToFile);
        InputStream inputStream = getFileFromResourcesAsStream(pathToFile);
        return convertStreamToString(inputStream);
    }

    private static InputStream getFileFromResourcesAsStream(String pathToFile) {
        ClassLoader classLoader = Files.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(pathToFile);

        if (inputStream == null)
            throw new IllegalArgumentException("The filed not found by path: " + pathToFile);

        return inputStream;
    }

    private static String convertStreamToString(InputStream inputStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }
}
