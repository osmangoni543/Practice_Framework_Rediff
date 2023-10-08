package com.qa.rediff.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "login1")
    private WebElement emailTextBox;

    @FindBy(id = "password")
    private WebElement passwordTextBox;

    @FindBy(name = "proceed")
    private WebElement signInButton;

    @FindBy(id = "div_login_error")
    private WebElement loginWarningMessage;

    @FindBy(id = "div_login_error")
    private WebElement temporaryIssueWarningMessage;

    public void enterEmailAddress(String emailText){
        emailTextBox.sendKeys(emailText);
    }

    public void enterPassword(String passwordText){
        passwordTextBox.sendKeys(passwordText);
    }

    public Alert clickOnSignInButton(){
        signInButton.click();
        Alert alert = driver.switchTo().alert();
        return alert;
    }

    public String retrieveLoginWarningMessage(){
        String lwarning = loginWarningMessage.getText();
        return lwarning;
    }

    public String retrieveTemporaryIssueWarningMessage(){
        String twarning = temporaryIssueWarningMessage.getText();
        return twarning;
    }

    public AccountPage navigateToAccountPage(String EmailText, String PasswordText){
        emailTextBox.sendKeys(EmailText);
        passwordTextBox.sendKeys(PasswordText);
        signInButton.click();
        return new AccountPage(driver);
    }

}
