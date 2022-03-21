import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    private static WebDriverManager wdm;
    private static WebDriver wrappedDriver;
    private final ThreadLocal<WebDriver> instances = new ThreadLocal<>();
    private static final Logger log = getLogger(BaseTest.class);

    private WebDriver getDriver() {
        log.info("Getting instance of browser");
        if (instances.get() == null)
            instances.set(wdm.create());
        return instances.get();
    }

    @BeforeAll
    static void setupClass() {
        wdm = WebDriverManager.chromedriver().browserInDocker()
                .enableRecording()
                .enableVnc();
    }

    @BeforeEach
    void setupTest() {
        log.info("Setup driver...");
        wrappedDriver = this.getDriver();
        wrappedDriver.manage().window().maximize();
        wrappedDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void teardown() {
        log.info("Close driver...");
        wrappedDriver = instances.get();
        if (wrappedDriver != null) {
            wrappedDriver.close();
            instances.set(null);
        }
    }

    @AfterAll
    static void quit() {
        log.info("Close driver...");
        wdm.quit();
    }

    @Test
    @Description("Base test for testing header on selenium.dev page")
    void checkHeaderOnSeleniumPage() {
        log.info("The base test has been started...");
        wrappedDriver.get("https://www.selenium.dev/");
        WebElement headerElement = wrappedDriver.findElement(By.tagName("h1"));
        String header = headerElement.getText();
        log.info("Header is '{}'", header);
        assertEquals("Selenium automates browsers. That's it!", header,
                format("The message hasn't been equal '%s'", header));
    }

    @Test
    @Description("Test to check the content of the request in all description lines")
    void checkRequestQueryToContent() {
        log.info("The base test has been started...");
        String query = "selenium";
        wrappedDriver.get("https://www.google.com/");
        WebElement input = wrappedDriver.findElement(By.name("q"));
        input.sendKeys(query);
        WebElement searchButton = wrappedDriver.findElement(By.xpath("//div[@class='UUbT9']//input[@name='btnK']"));
        searchButton.submit();

        List<WebElement> paragraphList = wrappedDriver.findElements(By.xpath("//div[@data-content-feature='1']"));
        String firstParagraph = paragraphList.get(0).getText().toLowerCase();
        boolean isFirstParagraphContainsQuery = firstParagraph.contains(query);
        Assertions.assertTrue(isFirstParagraphContainsQuery,
                format("The paragraph '%s' doesn't contain query '%s'.", firstParagraph, query));
    }
}