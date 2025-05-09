
Feature: User Login on Automation Test Store
  In order to access my account
  As a registered user
  I want to log in with valid credentials

  Background:
    Given the user is on the login page of automationteststore

  Scenario Outline: User login attempts with different credentials
    When the user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then <expectedResult>
    Examples:
      | username | password  | expectedResult                                    |
      | Nader    | Test123   | the user should be redirected to the Home         |
      | wrongUsr | wrongPas  | an error message should be displayed if invalid   |
      | Nader    | wrongPas  | an error message should be displayed if invalid   |
      | wrongUsr | Test123   | an error message should be displayed if invalid   |
      |          |           | an error message should be displayed if invalid   |

