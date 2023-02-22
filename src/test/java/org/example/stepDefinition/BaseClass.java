package org.example.stepDefinition;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.example.utilities.ReadConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.ILoggerFactory;
import org.apache.log4j.PropertyConfigurator;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public abstract class BaseClass {

    ReadConfig readConfig = new ReadConfig();
//getting properties from config file
    public String URL=readConfig.getApplicationURL();
    public String username = readConfig.getUsername();
    public String pwd =readConfig.getPswd();
    public String browser =readConfig.getBrowser();
    public WebDriver driver;
    public Logger lg;
    public static JavascriptExecutor js;

    //open browser and navigate to url
    public void setup()
    {
        lg=  Logger.getLogger("BaseClass");
        PropertyConfigurator.configure("log4j.properties");

        if (browser.equals("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions opt = new ChromeOptions();
            opt.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
            opt.addArguments("--disable-infobars");
            driver= new ChromeDriver(opt);
        }
        else if(browser.equals("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }
        else if(browser.equals("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver= new EdgeDriver();
        }
        else if(browser.equals("IE"))
        {
            WebDriverManager.iedriver().setup();
            driver= new InternetExplorerDriver();
        }

        //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;




    }
//close browser
    public void tearDown()
    {
        driver.quit();
    }
//capture screenshot

    public void captureScreenshot(WebDriver driver, String name) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src= ts.getScreenshotAs(OutputType.FILE);
        //File trg=new File(".\\screenshots\\home1.png");
        File trg = new File(System.getProperty("user.dir")+"\\screenshots\\"+ name+".png");
        FileUtils.copyFile(src, trg);
        System.out.println("screenshot taken!");


    }
}
