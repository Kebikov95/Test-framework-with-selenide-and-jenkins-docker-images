import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseTest {

    private WebDriver driver;
    private static final Logger log = getLogger(BaseTest.class);

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().browserInDocker();
    }

    @BeforeEach
    void setupTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("98.0");
        capabilities.setCapability("headless", true);
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", false,
                "enableVideo", false
        ));
        driver = new RemoteWebDriver(
                new URL("http://selenoid:4444/wd/hub"),
                capabilities
        );
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
