package com.practicetestautomation.tests;

import com.practicetestautomation.tests.exceptions.ExceptionsTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger;

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
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }
}
