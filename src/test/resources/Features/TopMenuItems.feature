Feature: Top Menu Items Navigation and Account Management
  As a user of the Automation Test Store
  I want to navigate through the top menu items
  So that I can verify their functionality and manage my account

  Background:
    Given the user logs into the Automation Test Store01

  Scenario: Navigate to the Special menu item
    When the user clicks on the Special menu item
    Then the Special menu item URL is verified

    When the user clicks on the Cart menu item
    Then the Cart menu item URL is verified

    When the user click Logout menu item
    Then the user should check the logout message "You have been logged off your account. It is now safe to leave the computer."


    When the user clicks on Account and selects Check Your Order
    Then the Check Your Order URL is verified


    When the user clicks on the Continue button
    Then the error messages are verified

    When the user enters Order ID and Email and clicks Continue
    Then the error message for missing order is verified and handled

    When the user logs in again
    Then the user is navigated to the account history page

    When the user clicks on Account and selects Check Your Order again
    Then the Check Your Order history URL is verified

    When the user clicks on the View button for an order
    Then the user is navigated to the invoice page

    When the user clicks on the Print button
    Then the print tab is opened and closed

    When the user clicks on the Continue button twice
    Then the user navigated to the account page

    When A test confirmation popup is displayed
    Then after 5 seconds the browser and popup are closed
