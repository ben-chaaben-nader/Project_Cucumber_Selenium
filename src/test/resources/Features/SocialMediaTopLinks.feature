Feature: Verify social media links on Automation Test Store
  In order to ensure proper redirection
  As a user
  I want to validate the behavior of social media links

  Background:
    Given the user logs into the Automation Test Store4

  Scenario: Verify Facebook link redirection
    When the user opens the Facebook link
    Then the user should be redirected to Facebook

    When the user opens the Twitter link
    Then the user should be redirected to Twitter

    When the user opens the LinkedIn link
    Then the user should be redirected to LinkedIn
    And the test should end with a confirmation popup
