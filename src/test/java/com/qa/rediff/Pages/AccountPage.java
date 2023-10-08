package com.qa.rediff.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    public WebDriver driver;

    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "rd_active")
    private WebElement inboxButton;

    public boolean statusOfInboxButton(){
        boolean inboxDisplayStatus = inboxButton.isDisplayed();
        return inboxDisplayStatus;
    }


}
