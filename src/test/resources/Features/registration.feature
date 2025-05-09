Feature: User Registration on Automation Test Store
  In order to create a new account
  As a new user
  I want to register with valid information and confirm that the registration is successful

  Background:
    Given the user is on the registration page

  Scenario: Successful registration with valid details
    When the user enters first name "Nader235" and last name "Ben chaaben"
    And the user enters email "Ch@gmail.com"
    And the user enters phone number "+4482223" and fax "0111"
    And the user enters company name "Sony"
    And the user enters address line 1 "Poland lodz" and address line 2 "Poland lodz"
    And the user enters city "Lodz"
    And the user selects country "Poland" and region "Lodzkie"
    And the user enters postal code "93-578"
    And the user enters login name "Nader235"
    And the user enters password "Test1235" and confirms password "Test1235"
    And the user agrees to receive newsletters
    And the user agrees to the terms and conditions
    When the user submits the registration form
    Then the user should be redirected to the account success page


  Scenario: Registration with unavailable login name
    When the user enters first name "Nader" and last name "Ben chaaben"
    And the user enters email "Chaben6@gmail.com"
    And the user enters phone number "+4482281" and fax "0011"
    And the user enters company name "Sony"
    And the user enters address line 1 "Poland lodz" and address line 2 "Poland lodz"
    And the user enters city "Lodz"
    And the user selects country "Poland" and region "Lodzkie"
    And the user enters postal code "93-578"
    And the user enters login name "Nader"
    And the user enters password "Test123" and confirms password "Test123"
    And the user agrees to receive newsletters
    And the user agrees to the terms and conditions
    When the user submits the registration form
    Then an error message should be displayed indicating the login name is not available
