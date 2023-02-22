package org.example.pageObjects;

import org.example.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitUtils wait;
    Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.actions=new Actions(driver);

        //static method of PageFactory class is used to initialize web elements
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);

    }

}