package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private By usernameInputLocator = By.id("username");
    private By passwordInputLocator = By.id("password");
    private By submitButtonLocator = By.id("submit");
    private By errorMessageLocator = By.id("error");

    public LoginPage (WebDriver driver){
        super(driver);
    }

    public void enterUsername(String username){
        driver.findElement(usernameInputLocator).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    public void clickSubmitButton(){
        driver.findElement(submitButtonLocator).click();
    }

    public SuccessfulLoginPage executeLogin(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickSubmitButton();
        return new SuccessfulLoginPage(driver);
    }

    public String getErrorMessage(){
        WebElement errorMessageElement = waitForElement(errorMessageLocator);
        return errorMessageElement.getText();
    }
}
