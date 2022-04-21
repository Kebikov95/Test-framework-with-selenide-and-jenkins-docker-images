package framework.configuration;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import helpers.ResourcesUtils;

import java.io.IOException;
import java.nio.file.Path;

public class UriManager {

    private static final String JSON;
    private static final Object DOCUMENT;

    static {
        try {
            JSON  = ResourcesUtils.readAsString(Path.of("json/uri.json"));
            DOCUMENT = Configuration.defaultConfiguration().jsonProvider().parse(JSON);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getJsonUri() {
        return JsonPath.read(DOCUMENT, "$.reqres.uri.json");
    }

    public static String getLoginUri() {
        return JsonPath.read(DOCUMENT, "$.reqres.uri.login");
    }

    public static String getRegisterUri() {
        return JsonPath.read(DOCUMENT, "$.reqres.uri.register");
    }

    public static String getUserUri() {
        return JsonPath.read(DOCUMENT, "$.reqres.uri.user");
    }

    public static String getUserPageUri() {
        return JsonPath.read(DOCUMENT, "$.reqres.uri.userPage");
    }

    public static String getUsersUri() {
        return JsonPath.read(DOCUMENT, "$.reqres.uri.users");
    }
}
