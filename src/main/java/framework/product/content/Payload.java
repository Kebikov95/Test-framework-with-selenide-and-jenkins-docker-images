package framework.product.content;

import static java.lang.String.format;

public class Payload {

    private Payload() {
    }

    public static String getAddPlaceBody() {
        return "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String getAddAddressBody() {
        return "{\n" +
                "    \"place_id\": \"%s\",\n" +
                "    \"address\": \"%s\",\n" +
                "    \"key\": \"qaclick123\"\n" +
                "}";
    }

    public static String getAddBookBody() {
        return "{\n" +
                "   \"name\":\"Learn Appium Automation with Java\",\n" +
                "   \"isbn\":\"bcd\",\n" +
                "   \"aisle\":\"22123397\",\n" +
                "   \"author\":\"John foe\"\n" +
                "}";
    }

    public static String getAddBookBody(String isbn, String value) {
        return format("{\n" +
                "   \"name\":\"Learn Appium Automation with Java\",\n" +
                "   \"%s\":\"%s\",\n" +
                "   \"aisle\":\"22123397\",\n" +
                "   \"author\":\"John foe\"\n" +
                "}", isbn, value);
    }
}
