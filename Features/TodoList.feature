Feature: TodoList
  As a user, I want to use the todo app to manage my tasks easily.

  Background:
    Given       Given the user is on the TodoList web page.

  @PositiveScenario
  Scenario: User adds a new item to the TodoList
    When the user writes an item "test"
    And the user adds the item to the list
    Then the item "test" should appear in the todo list
    And the item count should be incremented by 1

  @PositiveScenario
  Scenario: User adds multiple items to the TodoList
    When the user adds the items "Test1", "Test2", and "Test3" to the list
    Then the items "Test1", "Test2", and "Test3" should appear in the todo list
    And the item count should be 3

  @PositiveScenario
  Scenario: User renames an item in the TodoList
    When the user writes an item "test"
    And the user adds the item to the list
    And the user double-clicks on the item and renames the item to "it's renamed!"
    Then the item should have the new name "it's renamed!"

  @PositiveScenario
  Scenario: User marks an item as completed in the TodoList
    When the user writes an item "test"
    And the user adds the item to the list
    And the user marks the item as done
    Then the item should be marked as completed
    And the items left count should be 0

  @PositiveScenario
  Scenario: User deletes an item from the TodoList
    When the user writes an item "test"
    And the user adds the item to the list
    When the user writes an item "test1"
    And the user adds the item to the list
    Then the item count should be 2
    When the user deletes the item "test"
    Then the item "test" should disappear from the list
    And the item count should be 1
    When the user deletes the item "test1"
    Then the item "test1" should disappear from the list
    And the item count will disappear

  @PositiveScenario
  Scenario: User mark item as completed and clears the completed items
    When the user adds the items "Test1", "Test2", and "Test3" to the list
    Then the items "Test1", "Test2", and "Test3" should appear in the todo list
    And the item count should be 3
    When the user mark the item "Test2" as completed
    Then the item "Test2" should be marked as completed
    When the user clears the completed
    Then the item "Test2" will be deleted from the list
    And  item "Test1" and "Test3" will be still in the list

