package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{
    WebDriver driver;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    //CheckBox button selector
    private By itemCheckBox= By.xpath(".//input[@type='checkbox']");
    //delete button selector
    private By itemDeleteButton= By.xpath(".//button[@class='destroy']");
    //input rename field selector
    private By inputForRename= By.xpath(".//input[@class='edit']");

    @FindBy(xpath = "//button[@class='clear-completed']")
    WebElement clearButton;

    @FindBy(css = "input.new-todo")
    @CacheLookup
    private WebElement textInput;

    public String getItemsCount() {
        return itemsCount.getText();
    }

    @FindBy(xpath = "//footer//span//strong")
    private  WebElement itemsCount;


    public List<WebElement> getToDoList() {
        return toDoList;
    }

    @FindBy(xpath = "//section//ul//li")
    private List<WebElement> toDoList;


    public void writeItem(String item) {
        textInput.clear();
        textInput.sendKeys(item);
    }

    public void AddItemToList() {
        textInput.sendKeys(Keys.ENTER);
    }

    public List<String> getItemsList() {
        List<String> result = new ArrayList<String>();
        for (WebElement item : this.toDoList) {
            String text = (item.getText());
            result.add(text);
        }
        return result;
    }
    public String getItemByIndex(int i) {
        return  this.toDoList.get(i).getText();
    }

    public void markItemsComplete() {
        for (WebElement item : this.toDoList) {
            WebElement buttonElement = item.findElement(this.itemCheckBox);
            buttonElement.click();

        }

    }
    public void markItemCompleteByIndex(int i) {

        this.toDoList.get(i).findElement(this.itemCheckBox).click();
    }
    public void deleteListItems() {
        for (WebElement item : this.toDoList) {
            actions.moveToElement(item).build().perform();
            WebElement buttonElement = item.findElement(itemDeleteButton);
            buttonElement.click();

        }


    }
    public void deleteItemByIndex(int i) {

            actions.moveToElement(this.toDoList.get(i)).build().perform();
            WebElement buttonElement = this.toDoList.get(i).findElement(itemDeleteButton);
            buttonElement.click();


    }
    public void renameItemByIndex(int i,String text) {

        actions.doubleClick(this.toDoList.get(i)).build().perform();
        this.toDoList.get(i).findElement(inputForRename).clear();
        actions.doubleClick(this.toDoList.get(i)).build().perform();
        this.toDoList.get(i).findElement(inputForRename).sendKeys(text);
        this.toDoList.get(i).findElement(inputForRename).sendKeys(Keys.ENTER);


    }
    public void clearCompleted()
    {
        clearButton.click();
    }

}
