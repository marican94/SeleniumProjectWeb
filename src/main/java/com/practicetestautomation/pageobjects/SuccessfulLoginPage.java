package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulLoginPage extends BasePage {
    private By logOutButtonLocator = By.linkText("Log out");

    public SuccessfulLoginPage (WebDriver driver){
        super(driver);
    }


    public boolean isLogoutButtonDisplayed(){
        return isDisplayed(logOutButtonLocator);
    }
}
