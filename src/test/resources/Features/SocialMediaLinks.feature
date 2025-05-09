Feature: Social Media Links Navigation

  Background:
    Given the user is logged the Automation Test Store3

  Scenario: Open and verify Facebook link
    When the user opens and closes "Facebook" link
    Then the user should be transferred "https://www.facebook.com/"

  Scenario: Open and verify Twitter link
    When the user opens and closes "Twitter" link
    Then the user should be transferred "https://x.com/"

  Scenario: Open and verify LinkedIn link
    When the user opens and closes "Linkedin" link
    Then the user should be transferred "https://uk.linkedin.com/"
