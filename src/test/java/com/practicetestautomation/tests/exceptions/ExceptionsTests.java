package com.practicetestautomation.tests.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {

    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        logger = Logger.getLogger(ExceptionsTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running Tests in " + browser);
        switch (browser.toLowerCase()) {
            case  "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + " is missing, so running tests in Chrome by default");
                driver = new ChromeDriver();
                break;
        }

        //Implicit Wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Open page
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test
    public void noSuchElementExceptionTest(){
        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

        // Verify Row 2 input field is displayed
        // WebElement row2InputField = driver.findElement(By.xpath("//div[@id='row2']/input"));
        Assert.assertTrue(row2InputField.isDisplayed(), "Row 2 input field is displayed");
    }

    @Test
    public void timeoutExceptionTest(){
        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        // Click Add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

        // Verify Row 2 input field is displayed
        // WebElement row2InputField = driver.findElement(By.xpath("//div[@id='row2']/input"));
        Assert.assertTrue(row2InputField.isDisplayed(), "Row 2 input field is displayed");
    }

    @Test
    public void elementNotInteractableExceptionTest(){
        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        row2InputField.sendKeys("Sushi");

        // Click Save button
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@name='Save']"));
        saveButton.click();

        // Verify text saved
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Row 2 was saved";

        Assert.assertEquals(actualMessage, expectedMessage, "Message is not expected");
    }

    @Test
    public void invalidElementStateExceptionTest(){
        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit_btn")));
        editButton.click();

        WebElement row1InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row1']/input")));
        row1InputField.clear();
        row1InputField.sendKeys("Sushi");

        // Click Save button
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row1']/button[@name='Save']"));
        saveButton.click();

        // Verify text saved
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Row 1 was saved";

        Assert.assertEquals(actualMessage, expectedMessage, "Message is not expected");
    }

    @Test
    public void staleElementReferenceExceptionTest(){
        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        // Verify instruction text element is no longer displayed
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))), "Instruction text is still displayed");
    }


}
