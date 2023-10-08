package com.qa.rediff.TestCases;

import Utilities.ExcelCode;
import Utilities.Utils;
import com.qa.rediff.Pages.AccountPage;
import com.qa.rediff.Pages.LandingPage;
import com.qa.rediff.Pages.LoginPage;
import com.qa.rediff.TestBase.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    public WebDriver driver;
    public LandingPage landingpage;
    public LoginPage loginpage;

    public AccountPage accountpage;

    public Alert alert;

    public LoginTest() throws Exception {
        super();
    }
    @BeforeMethod
    public void setup(){
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
        landingpage = new LandingPage(driver);
        loginpage = landingpage.navigateToLoginPage();
    }

    @Test(priority = 1, dataProvider = "Rediff", dataProviderClass = ExcelCode.class)
    public void verifyLoginWithValidCredentials(String excelEmail, String excelPassword){
        accountpage = loginpage.navigateToAccountPage(excelEmail, excelPassword);
        Assert.assertTrue(accountpage.statusOfInboxButton());
    }

    @Test(priority = 2)
    public void verifyLoginWithValidEmailInvalidPassword(){
        loginpage.navigateToAccountPage(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
        Assert.assertTrue(loginpage.retrieveLoginWarningMessage().contains(dataprop.getProperty("loginWarning")));
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailValidPassword(){
        loginpage.navigateToAccountPage(Utils.generateEmailWithDateTimeStamp(), prop.getProperty("validPassword"));
        Assert.assertTrue(loginpage.retrieveTemporaryIssueWarningMessage().contains(dataprop.getProperty("temporaryIssueWarning")));
    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidCredentials(){
        loginpage.navigateToAccountPage(Utils.generateEmailWithDateTimeStamp(), dataprop.getProperty("invalidPassword"));
        Assert.assertTrue(loginpage.retrieveTemporaryIssueWarningMessage().contains(dataprop.getProperty("temporaryIssueWarning")));
    }

    @Test(priority = 5)
    public void verifyLoginWithNoCredentials(){
        alert = loginpage.clickOnSignInButton();
        Assert.assertTrue(alert.getText().contains(dataprop.getProperty("alertWarning")));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
