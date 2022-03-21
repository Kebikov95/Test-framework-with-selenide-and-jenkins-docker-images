import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseTest {

    private static WebDriverManager manager;
    private WebDriver driver;
    private static final Logger log = getLogger(BaseTest.class);

    @BeforeEach
    void setupTest() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), new ChromeOptions());
    }

    @AfterEach
    void teardown() {
        log.info("Close driver...");
        if (driver != null) {
            driver.close();
            driver = null;
        }
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