package com.qa.rediff.TestBase;

import Utilities.Utils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Arrays;

public class TestBase {

    public Properties prop;
    public Properties dataprop;
    public FileInputStream ip;
    public ChromeOptions options;
    public WebDriver driver;

    public TestBase() throws Exception{
        prop = new Properties();
        ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Config\\config.properties");
        prop.load(ip);

        dataprop = new Properties();
        ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Config\\testData.properties");
        dataprop.load(ip);
    }

    public WebDriver initializeBrowserAndOpenApplication(String browserName){
        if(browserName.equals("chrome")){
            options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        }
        else if(browserName.equals("firefox")){
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if(browserName.equals("edge")){
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGELOAD_TIME));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIME));
        driver.get(prop.getProperty("url"));
        return driver;
    }


}
