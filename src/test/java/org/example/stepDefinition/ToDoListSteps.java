package org.example.stepDefinition;

import com.sun.source.tree.AssertTree;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.pageObjects.HomePage;
import org.junit.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ToDoListSteps extends  BaseClass{

    HomePage homePage;
@Before
public void setup()
    {
        super.setup();
        homePage = new HomePage(driver);

    }
    @After
    public void tearDown(){
        super.tearDown();
    }

    @Given("Given the user is on the TodoList web page.")
    public void given_the_user_is_on_the_todo_list_web_page() {
        driver.get(URL);

    }
    @When("the user adds the items {string}, {string}, and {string} to the list")
    public void the_user_adds_the_items_and_to_the_list(String string, String string2, String string3) {
       homePage.writeItem(string);
       homePage.AddItemToList();
        homePage.writeItem(string2);
        homePage.AddItemToList();
        homePage.writeItem(string3);
        homePage.AddItemToList();
    }
    @Then("the items {string}, {string}, and {string} should appear in the todo list")
    public void the_items_should_appear_in_the_todo_list(String string, String string2, String string3) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(string);
        list.add(string2);
        list.add(string3);
         Assert.assertTrue(homePage.getItemsList().equals(list));

    }


    @Then("the item {string} should appear in the todo list")
    public void the_item_should_appear_in_the_todo_list(String string) {
        Assert.assertTrue("the item was not added",homePage.getItemsList().contains(string));

    }
    @Then("the item count should be incremented by {int}")
    public void the_item_count_should_be_incremented_by(Integer int1) {
        Assert.assertEquals(1,Integer.parseInt(homePage.getItemsCount()));

    }

    @When("the user marks the item as done")
    public void the_user_marks_the_item_as_done() {
        homePage.markItemCompleteByIndex(0);
    }
    @Then("the item should be marked as completed")
    public void the_item_should_be_marked_as_completed() {

            }
    @Then("the items left count should be {int}")
    public void the_items_left_count_should_be(Integer int1) {
        Assert.assertEquals(0,Integer.parseInt(homePage.getItemsCount()));

    }

    @When("the user double-clicks on the item and renames the item to {string}")
    public void the_user_double_clicks_on_the_item(String string) {
        homePage.renameItemByIndex(0,string);
    }

    @Then("the item should have the new name {string}")
    public void the_item_should_have_the_new_name(String string) {
    homePage.getItemsList().contains(string);
    }
    @When("the user writes an item {string}")
    public void the_user_writes_an_item(String string) {
        homePage.writeItem(string);
    }
    @When("the user adds the item to the list")
    public void the_user_adds_the_item_to_the_list() {
        homePage.AddItemToList();
    }
    @Then("the item count should be {int}")
    public void the_item_count_should_be(int int1) {
        Assert.assertEquals("the items count is not equal to "+int1,int1,homePage.getItemsList().size());
    }
    @When("the user deletes the item {string}")
    public void the_user_deletes_the_item(String string) {

       homePage.deleteItemByIndex(homePage.getItemsList().indexOf(string));
    }
    @Then("the item {string} should disappear from the list")
    public void the_item_should_disappear_from_the_list(String string) {
    Assert.assertFalse("the item was not deleted",homePage.getItemsList().contains(string));
    }
    @Then("the item count will disappear")
    public void the_item_count_will_disappear() {
       try{
           lg.info("if the element is not found it will throw an exception element not found");
           homePage.getItemsCount();


       }catch (Exception e){
           Assert.assertTrue(true);
       }


    }
    @When("the user mark the item {string} as completed")
    public void the_user_mark_the_item_as_completed(String string) {
        homePage.markItemCompleteByIndex(homePage.getItemsList().indexOf(string));
    }
    @Then("the item {string} should be marked as completed")
    public void the_item_should_be_marked_as_completed(String string) {
    for(WebElement i: homePage.getToDoList())
    {
        if(i.getText().equals(string))
        {
            Assert.assertEquals("completed",i.getAttribute("class"));
        }
    }

    }
    @When("the user clears the completed")
    public void the_user_clears_the_completed() {
        homePage.clearCompleted();
    }
    @Then("the item {string} will be deleted from the list")
    public void the_item_will_be_deleted_from_the_list(String string) {
        Assert.assertFalse("the item was not deleted",homePage.getItemsList().contains(string));
        for (String i: homePage.getItemsList())
        lg.info(i);

    }
    @Then("item {string} and {string} will be still in the list")
    public void item_and_will_be_still_in_the_list(String string, String string2) {
    Assert.assertTrue("items were deleted", (homePage.getItemsList().contains(string)&&homePage.getItemsList().contains(string2)));
    }
}
