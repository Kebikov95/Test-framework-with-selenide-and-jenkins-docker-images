import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    private WebDriver driver;
    private static WebDriverManager wdm;
    private static final Logger log = getLogger(BaseTest.class);

    @BeforeAll
    static void setupClass() {
        wdm = WebDriverManager.chromedriver().browserInDocker();
    }

    @BeforeEach
    void setupTest() {
        log.info("Setup driver...");
        driver = wdm.create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void teardown() {
        log.info("Close driver...");
        wdm.quit();
    }

    @Test
    void baseTest() {
        log.info("The base test has been started...");
        driver.get("https://www.selenium.dev/");
        WebElement headerElement = driver.findElement(By.tagName("h1"));
        String header = headerElement.getText();
        log.info("Header is '{}'", header);
        assertEquals("Selenium automates browsers. That's it!", header,
                format("The message hasn't been equal '%s'", header));
    }
}
