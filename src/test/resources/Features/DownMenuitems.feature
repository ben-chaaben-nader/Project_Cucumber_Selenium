Feature: Down Menu Items and User Actions

  Background:
    Given the user is logged to Automation Test Store2

  Scenario: User navigates to Down menu items, Logout
    When the user click on the "About Us" menu item
    Then the user should transferred to "https://automationteststore.com/index.php?rt=content/content&content_id=1"

    When the user click on the "Privacy Policy" menu item
    Then the user should transferred to "https://automationteststore.com/index.php?rt=content/content&content_id=2"

    When the user click on the "Return Policy" menu item
    Then the user should transferred to "https://automationteststore.com/index.php?rt=content/content&content_id=3"

    When the user click on the "Shipping" menu item
    Then the user should transferred to "https://automationteststore.com/index.php?rt=content/content&content_id=4"

    When the user click on the "Site Map" menu item
    Then the user should transferred to "https://automationteststore.com/index.php?rt=content/sitemap"

    When the user click on the "Contact Us" menu item
    Then the user should transferred to "https://automationteststore.com/index.php?rt=content/contact"

    When the user check error messages on Contact Us form
    Then the error messages should be displayed

    When the user submits a successful enquiry on Contact Us page
    Then the user should see the successful enquiry message "Your enquiry has been successfully sent to the store owner!"

    When the user click on Logout menu item
    Then the user should see the logout message "You have been logged off your account. It is now safe to leave the computer."
