package ui;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SeleniumTests extends BaseTest {

    @Test
    @Description("Base test for testing header on selenium.dev page")
    void checkHeaderOnSeleniumPage() {
        log.info("The base test has been started...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wrappedDriver.get("https://www.selenium.dev/");
        WebElement headerElement  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        String header = headerElement.getText();
        log.info("Header is '{}'", header);
        assertEquals("Selenium automates browsers. That's it!", header,
                format("The message hasn't been equal '%s'", header));
    }

    @ParameterizedTest
    @ValueSource(strings = {"selenium", "jenkins", "api"})
    @Description("Test to check the content of the request in all description lines")
    void checkRequestQueryToContent(String query) {
        log.info("The base test has been started...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wrappedDriver.get("https://www.google.com/");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        input.click();
        input.sendKeys(query);
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='UUbT9']//input[@name='btnK']")));
        searchButton.click();

        List<WebElement> paragraphList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-content-feature='1']")));
        String firstParagraph = paragraphList.get(0).getText().toLowerCase();
        boolean isFirstParagraphContainsQuery = firstParagraph.contains(query);
        Assertions.assertTrue(isFirstParagraphContainsQuery,
                format("The paragraph '%s' doesn't contain query '%s'.", firstParagraph, query));
    }
}
