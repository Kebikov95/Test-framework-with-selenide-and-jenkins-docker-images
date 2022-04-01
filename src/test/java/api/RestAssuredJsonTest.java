package api;

import api.helpers.Files;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredJsonTest extends BaseTest {

    private JsonPath json;

    @BeforeEach
    void setup() throws IOException {
        json = Files.readJson("json/courses.json");
    }

    @Test
    void checkCourseSize() {
        int expectedSize = 3;
        int actualSize = json.getInt("courses.size()");
        assertEquals(expectedSize, actualSize);
        log.info("Actual 'course size' is '{}'", actualSize);
    }

    @Test
    void checkTotalAmount() {
        int expectedTotalAmount = 910;
        int actualTotalAmount = json.getInt("dashboard.purchaseAmount");
        assertEquals(expectedTotalAmount, actualTotalAmount);
        log.info("Actual 'total amount' is '{}'", actualTotalAmount);
    }

    @Test
    void checkFirstCourseTitle() {
        String expectedTitle = "Selenium Python";
        String actualTitle = json.getString("courses[0].title");
        assertEquals(expectedTitle, actualTitle);
        log.info("Actual 'first title of course' is '{}'", actualTitle);
    }

    @Test
    void checkSumOfPrices() {
        int expectedSum = 135;
        int actualSum = 0;
        int coursesSize = json.getInt("courses.size()");

        for (int i = 0; i < coursesSize; i++)
            actualSum += json.getInt(format("courses[%s].price", i));

        assertEquals(expectedSum, actualSum);
        log.info("The sum of 'all prices' is '{}$'", actualSum);
    }

    @Test
    void checkSumForAllNotRpaCourses() {
        int actualSum = 0;
        int rpaPrices = 45;
        int rpaCopies = 10;
        int expectedSum = json.getInt("dashboard.purchaseAmount") - (rpaPrices * rpaCopies);
        int coursesSize = json.getInt("courses.size()");

        for (int i = 0; i < coursesSize; i++) {
            String title = json.getString(format("courses[%s].title", i));

            if (!title.equalsIgnoreCase("rpa")) {
                int price = json.getInt(format("courses[%s].price", i));
                int copies = json.getInt(format("courses[%s].copies", i));
                actualSum += price * copies;
            }
        }

        assertEquals(expectedSum, actualSum);
        log.info("The total amount of 'not RPA courses' is '{}$'", actualSum);
    }

    @Test
    void checkSumOfPricesForAllCopies() {
        int expectedSum = json.getInt("dashboard.purchaseAmount");
        int actualSum = 0;
        int coursesSize = json.getInt("courses.size()");

        for (int i = 0; i < coursesSize; i++) {
            int price = json.getInt(format("courses[%s].price", i));
            int copies = json.getInt(format("courses[%s].copies", i));
            actualSum += price * copies;
        }

        assertEquals(expectedSum, actualSum);
        log.info("The sum of 'all prices by copies' is '{}$'", actualSum);
    }
}
