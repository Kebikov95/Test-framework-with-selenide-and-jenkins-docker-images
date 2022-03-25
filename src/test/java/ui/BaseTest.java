package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;

public class BaseTest {

    protected static final Logger log = getLogger(BaseTest.class);
    private static WebDriverManager wdm;
    private final ThreadLocal<WebDriver> instances = new ThreadLocal<>();
    protected static WebDriver wrappedDriver;

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
        wrappedDriver.manage().window().maximize();
    }

    @AfterEach
    void teardown() throws IOException {
        log.info("Make screenshot...");
        takeScreenshot();
        log.info("Close driver and delete cookies...");
        wrappedDriver = this.getDriver();
        wrappedDriver.manage().deleteAllCookies();
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

    private void takeScreenshot() throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String fileWithPath = format("%s/target/screenshots/%s.png", System.getProperty("user.dir"), timestamp);
        File DestFile = new File(format(fileWithPath, "screenshot"));
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
