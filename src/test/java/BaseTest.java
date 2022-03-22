import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.apache.logging.log4j.LogManager.getLogger;

public class BaseTest {

    private static WebDriverManager wdm;
    private final ThreadLocal<WebDriver> instances = new ThreadLocal<>();
    protected static WebDriver wrappedDriver;
    protected static final Logger log = getLogger(BaseTest.class);

    protected WebDriver getDriver() {
        log.info("Getting instance of browser");
        if (instances.get() == null)
            instances.set(wdm.create());
        return instances.get();
    }

    @BeforeAll
    static void setupClass() {
        wdm = WebDriverManager.chromedriver().browserInDocker()
                .enableRecording()
                .enableVnc()
                .dockerShmSize("2g")
                .dockerTmpfsSize("1g")
                .dockerRecordingOutput("records");
    }

    @BeforeEach
    void setupTest() {
        log.info("Setup driver...");
        wrappedDriver = this.getDriver();
        wrappedDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wrappedDriver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        log.info("Close driver...");
        wrappedDriver = this.getDriver();
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
}