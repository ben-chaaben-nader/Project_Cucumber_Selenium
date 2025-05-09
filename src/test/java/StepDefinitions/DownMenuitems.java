package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class DownMenuitems {
    WebDriver driver;
    String obtainedUrl;
    static int scenarioID = 0;

    private String incrementScenarioID() {
        return "Scenario-" + (++scenarioID);
    }

    // Step to log in
    @Given("the user is logged to Automation Test Store2")
    public void loginToAutomationTestStore2(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://automationteststore.com/index.php?rt=account/login");
        driver.manage().window().maximize();

        driver.findElement(By.id("loginFrm_loginname")).sendKeys("Nader");
        driver.findElement(By.id("loginFrm_password")).sendKeys("Test123");
        driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")).click();

        obtainedUrl = driver.getCurrentUrl();
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";
        boolean isSuccess = obtainedUrl.equals(expectedUrl);
        ReportLogger.logStepResult(incrementScenarioID() + ": Login to Automation Test Store", expectedUrl, obtainedUrl, obtainedUrl, isSuccess);
        Assert.assertEquals("Login failed. Expected: " + expectedUrl, expectedUrl, obtainedUrl);
    }

    // Step to click on menu item and verify redirection
    @When("the user click on the {string} menu item")
    public void clickOnMenuItem(String menuItem) {
        String expectedUrl = getMenuItemUrl(menuItem);
        driver.findElement(By.linkText(menuItem)).click();
        obtainedUrl = driver.getCurrentUrl();
        boolean isSuccess = obtainedUrl.equals(expectedUrl);
        ReportLogger.logStepResult(incrementScenarioID() + ": Click and verify " + menuItem, expectedUrl, obtainedUrl, obtainedUrl, isSuccess);
        Assert.assertEquals("Navigation to " + menuItem + " failed.", expectedUrl, obtainedUrl);
    }

    @Then("the user should transferred to {string}")
    public void verifyRedirection(String expectedUrl) {
        obtainedUrl = driver.getCurrentUrl();
        boolean isSuccess = obtainedUrl.equals(expectedUrl);
        ReportLogger.logStepResult(incrementScenarioID() + ": Verify redirection", expectedUrl, obtainedUrl, obtainedUrl, isSuccess);
        Assert.assertEquals("Redirection failed. Expected: " + expectedUrl, expectedUrl, obtainedUrl);
    }

    // Step to check error messages on Contact Us form
    @When("the user check error messages on Contact Us form")
    public void checkContactUsErrors() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='ContactUsFrm_first_name']")).sendKeys("Nader");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/button[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[2]/button[1]")).click();

        String[] errorXpaths = {
                "//div[contains(text(),'First name: is required field!')]",
                "//div[contains(text(),'Email: is required field!')]",
                "//div[contains(text(),'Enquiry: is required field!')]"
        };

        for (String xpath : errorXpaths) {
            String errorMessage = driver.findElement(By.xpath(xpath)).getText();
            Assert.assertTrue("Error message not displayed: " + xpath, errorMessage.contains("required field"));
        }
    }

    @Then("the error messages should be displayed")
    public void errorMessagesShouldBeDisplayed() {
        try {
            // Capture a screenshot of the error messages
            ReportLogger.captureAndLogScreenshot(driver, "Verify error messages displayed", true);

            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify error messages displayed",
                    "Error messages should be displayed as expected",
                    "Error messages are displayed",
                    String.valueOf(true)
            );

            System.out.println("Error messages are displayed as expected.");
        } catch (Exception e) {
            System.out.println("Exception in errorMessagesShouldBeDisplayed: " + e.getMessage());

            // Capture a screenshot in case of failure
            ReportLogger.captureAndLogScreenshot(driver, "Verify error messages displayed", false);

            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify error messages displayed",
                    "Error messages should be displayed as expected",
                    "Error messages are NOT displayed due to: " + e.getMessage(),
                    String.valueOf(false)
            );

            throw e;
        }
    }



    // Step to submit a successful enquiry on Contact Us page
    @When("the user submits a successful enquiry on Contact Us page")
    public void submitSuccessfulEnquiry() throws InterruptedException {
        driver.findElement(By.id("ContactUsFrm_first_name")).sendKeys("Nader");
        driver.findElement(By.id("ContactUsFrm_email")).sendKeys("chaben.nader@gmail.com");
        driver.findElement(By.id("ContactUsFrm_enquiry")).sendKeys("This is a test enquiry.");
        driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[2]/button[1]")).click();
        Thread.sleep(5000);
    }

    @Then("the user should see the successful enquiry message {string}")
    public void verifySuccessfulEnquiryMessage(String expectedMessage) {
        try {
            System.out.println("Start verifying successful enquiry message...");

            String actualMessage = driver.findElement(By.xpath("//p[contains(text(),'Your enquiry has been successfully sent to the store owner!')]")).getText();
            System.out.println("Actual message: " + actualMessage);

            boolean isSuccess = actualMessage.equals(expectedMessage);

            // Capture a screenshot of the successful enquiry message
            ReportLogger.captureAndLogScreenshot(driver, "Verify successful enquiry message", isSuccess);

            // Log the success or failure
            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify successful enquiry message",
                    "Expected message: " + expectedMessage,
                    "Actual message: " + actualMessage,
                    String.valueOf(isSuccess)
            );

            Assert.assertEquals("Successful enquiry message mismatch.", expectedMessage, actualMessage);
        } catch (Exception e) {
            System.out.println("Exception in verifySuccessfulEnquiryMessage: " + e.getMessage());

            // Capture a screenshot in case of failure
            ReportLogger.captureAndLogScreenshot(driver, "Verify successful enquiry message", false);

            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify successful enquiry message",
                    "Expected message: " + expectedMessage,
                    "Verification failed due to: " + e.getMessage(),
                    String.valueOf(false)
            );

            throw e;
        }
    }


    // Step to click on Logoff and verify logout message
    @When("the user click on Logout menu item")
    public void clickOnLogout() {
        // Extract the HTML content of the page
        String pageContent = driver.findElement(By.tagName("html")).getText();
        String logoutText = "Logoff";

        // Check if "Logout" exists in the page content
        if (pageContent.contains(logoutText)) {
            System.out.println("The element \"" + logoutText + "\" exists on the page. Attempting to click.");

            // Log success for finding the "Logout" element
            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify Logoff element exists",
                    "Logoff element should be present on the page",
                    "Logoff element is present",
                    String.valueOf(true)
            );

            // Click on the "Logoff" element
            driver.findElement(By.linkText(logoutText)).click();

            // Verify redirection to the expected URL
            obtainedUrl = driver.getCurrentUrl();
            String expectedUrl = "https://automationteststore.com/index.php?rt=account/logout";
            boolean isSuccess = obtainedUrl.equals(expectedUrl);

            // Log success or failure for redirection
            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify Logout redirection",
                    "Expected URL: " + expectedUrl,
                    "Obtained URL: " + obtainedUrl,
                    String.valueOf(isSuccess)
            );

            // Assert redirection
            Assert.assertEquals("Logout failed.", expectedUrl, obtainedUrl);
        } else {
            // Log failure for "Logout" element not found
            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify Logout element exists",
                    "Logout element should be present on the page",
                    "Logout element is NOT present",
                    String.valueOf(false)
            );

            // Print error and fail the test
            System.out.println("The element \"" + logoutText + "\" does not exist on the page.");
            Assert.fail("Logout button not found on the page.");
        }
    }



    @Then("the user should see the logout message {string}")
    public void verifyLogoutMessage(String expectedMessage) {
        try {
            String actualMessage = driver.findElement(By.xpath("//p[contains(text(),'You have been logged off your account. It is now safe to leave the computer.')]")).getText();

            boolean isSuccess = actualMessage.equals(expectedMessage);

            // Capture a screenshot of the logout message
            ReportLogger.captureAndLogScreenshot(driver, "Verify logout message", isSuccess);

            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify logout message",
                    "Expected message: " + expectedMessage,
                    "Actual message: " + actualMessage,
                    String.valueOf(isSuccess)
            );

            Assert.assertEquals("Logout message mismatch.", expectedMessage, actualMessage);
        } catch (Exception e) {
            System.out.println("Exception in verifyLogoutMessage: " + e.getMessage());

            // Capture a screenshot in case of failure
            ReportLogger.captureAndLogScreenshot(driver, "Verify logout message", false);

            ReportLogger.logStepResult(
                    incrementScenarioID() + ": Verify logout message",
                    "Expected message: " + expectedMessage,
                    "Verification failed due to: " + e.getMessage(),
                    String.valueOf(false)
            );

            throw e;
        }
    }



    // Helper method to get menu item URLs
    private String getMenuItemUrl(String menuItem) {
        switch (menuItem) {
            case "About Us":
                return "https://automationteststore.com/index.php?rt=content/content&content_id=1";
            case "Privacy Policy":
                return "https://automationteststore.com/index.php?rt=content/content&content_id=2";
            case "Return Policy":
                return "https://automationteststore.com/index.php?rt=content/content&content_id=3";
            case "Shipping":
                return "https://automationteststore.com/index.php?rt=content/content&content_id=4";
            case "Site Map":
                return "https://automationteststore.com/index.php?rt=content/sitemap";
            case "Contact Us":
                return "https://automationteststore.com/index.php?rt=content/contact";
            default:
                throw new IllegalArgumentException("Invalid menu item: " + menuItem);
        }
    }
}
