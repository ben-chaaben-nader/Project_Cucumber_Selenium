package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import java.util.concurrent.TimeUnit;

public class RegistrationSteps {
    WebDriver driver;

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set an implicit wait time of 10 seconds
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("https://automationteststore.com/index.php?rt=account/create");
    }

    @When("the user enters first name {string} and last name {string}")
    public void the_user_enters_first_and_last_name(String firstName, String lastName) {
        driver.findElement(By.id("AccountFrm_firstname")).sendKeys(firstName);
        driver.findElement(By.id("AccountFrm_lastname")).sendKeys(lastName);
    }

    @When("the user enters email {string}")
    public void the_user_enters_email(String email) {
        driver.findElement(By.id("AccountFrm_email")).sendKeys(email);
    }

    @When("the user enters phone number {string} and fax {string}")
    public void the_user_enters_phone_and_fax(String phone, String fax) {
        driver.findElement(By.id("AccountFrm_telephone")).sendKeys(phone);
        driver.findElement(By.id("AccountFrm_fax")).sendKeys(fax);
    }

    @When("the user enters company name {string}")
    public void the_user_enters_company_name(String company) {
        driver.findElement(By.id("AccountFrm_company")).sendKeys(company);
    }

    @When("the user enters address line 1 {string} and address line 2 {string}")
    public void the_user_enters_address_lines(String address1, String address2) {
        driver.findElement(By.id("AccountFrm_address_1")).sendKeys(address1);
        driver.findElement(By.id("AccountFrm_address_2")).sendKeys(address2);
    }

    @When("the user enters city {string}")
    public void the_user_enters_city(String city) {
        driver.findElement(By.id("AccountFrm_city")).sendKeys(city);
    }

    @When("the user selects country {string} and region {string}")
    public void the_user_selects_country_Poland_and_region_Lodzkie() throws InterruptedException {
        // Assuming you have a helper for selecting country and region if it's a dropdown
        driver.findElement(By.id("AccountFrm_country_id")).sendKeys("Poland");
        TimeUnit.SECONDS.sleep(10); // Minor wait for region options to update
        driver.findElement(By.id("AccountFrm_zone_id")).sendKeys("Lodzkie");
    }

    @When("the user enters postal code {string}")
    public void the_user_enters_postal_code(String postalCode) {
        driver.findElement(By.id("AccountFrm_postcode")).sendKeys(postalCode);
    }

    @When("the user enters login name {string}")
    public void the_user_enters_login_name(String loginName) {
        driver.findElement(By.id("AccountFrm_loginname")).sendKeys(loginName);
    }

    @When("the user enters password {string} and confirms password {string}")
    public void the_user_enters_password_and_confirms(String password, String confirmPassword) {
        driver.findElement(By.id("AccountFrm_password")).sendKeys(password);
        driver.findElement(By.id("AccountFrm_confirm")).sendKeys(confirmPassword);
    }

    @When("the user agrees to receive newsletters")
    public void the_user_agrees_to_receive_newsletters() {
        driver.findElement(By.id("AccountFrm_newsletter1")).click();
    }

    @When("the user agrees to the terms and conditions")
    public void the_user_agrees_to_terms_and_conditions() {
        driver.findElement(By.id("AccountFrm_agree")).click();
    }

    @When("the user submits the registration form")
    public void the_user_submits_the_registration_form() {
        driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
    }

    @Then("the user should be redirected to the account success page")
    public void the_user_should_be_redirected_to_the_account_success_page() {
        // Expected URL after successful registration
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/success";

        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Verify the URL and print a message based on the result
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Registration successful. Redirected to the account page.");
        } else {
            System.err.println("Registration failed. Expected URL: " + expectedUrl + " but got: " + currentUrl);
            Assert.fail("User was not redirected to the account page.");
        }

        // Close the browser after the test
        driver.quit();
}

        @Then("an error message should be displayed indicating the login name is not available")
        public void an_error_message_should_be_displayed_indicating_the_login_name_is_not_available() {
            // XPath for the error message
            String errorXPath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]";

            // Retrieve the error message text
            String errorMessage = driver.findElement(By.xpath(errorXPath)).getText();

            // Assert that the error message contains the expected text
            Assert.assertTrue("Error message not displayed or incorrect.",
                    errorMessage.contains("This login name is not available"));

            // Print the error message for debugging
            System.out.println("Displayed error message: " + errorMessage);

            // Close the browser after verification
            driver.quit();

    }
}
