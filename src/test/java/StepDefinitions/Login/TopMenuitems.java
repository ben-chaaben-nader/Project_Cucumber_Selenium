package StepDefinitions.Login;

import StepDefinitions.BugReport;
import StepDefinitions.ReportLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class TopMenuitems {
    WebDriver driver;
    Actions actions;
    String obtainedUrl;
    BugReport bugReport;
    static int scenarioID = 0;

    private String incrementScenarioID() {
        return "Scenario-" + (++scenarioID);
    }

    // Step to log into the Automation Test Store
    @Given("the user logs into the Automation Test Store01")
    public void loginToAutomationTestStore() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://automationteststore.com/index.php?rt=account/login");
        driver.manage().window().maximize();
        actions = new Actions(driver);
        bugReport = new BugReport(driver);

        try {
            // Input login credentials and click login button
            driver.findElement(By.xpath("//input[@id='loginFrm_loginname']")).sendKeys("Nader");
            driver.findElement(By.xpath("//input[@id='loginFrm_password']")).sendKeys("Test123");
            driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")).click();

            Thread.sleep(2000);
            obtainedUrl = driver.getCurrentUrl();
            String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";
            boolean isSuccess = obtainedUrl.equals(expectedUrl);

            // Capture a screenshot for the login page
            String screenshotPath = bugReport.captureScreenshot("LoginPage_" + scenarioID);

            // Log the result of the login
            ReportLogger.logStepResult(
                    scenarioID + ": Login to Automation Test Store",
                    expectedUrl,
                    obtainedUrl,
                    screenshotPath,
                    isSuccess
            );

            // Assert that the navigation was successful
            Assert.assertEquals("Login failed. Expected: " + expectedUrl + " but got: " + obtainedUrl, expectedUrl, obtainedUrl);
        } catch (Exception e) {
            // Capture and log any errors
            String errorScreenshotPath = bugReport.captureScreenshot("LoginError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Login to Automation Test Store",
                    "Expected URL: " + "https://automationteststore.com/index.php?rt=account/account",
                    "Error during login process: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }


    // Step to click on Special menu item
    @When("the user clicks on the Special menu item")
    public void clickOnSpecialMenuItem() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String xpath = "//header/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[1]/a[1]/span[1]";
        try {
            // Click on the Special menu item
            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(2000);

            // Capture a screenshot for the Special menu item click
            String screenshotPath = bugReport.captureScreenshot("SpecialMenuClick_" + scenarioID);

            // Log the result of the click action
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Special menu item",
                    "Special menu should be clicked successfully",
                    "Special menu clicked successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture and log any errors during the click action
            String screenshotPath = bugReport.captureScreenshot("SpecialMenuClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Special menu item",
                    "Special menu should be clicked successfully",
                    "Error occurred while clicking on Special menu: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }


    @Then("the Special menu item URL is verified")
    public void verifySpecialMenuItemUrl() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/special";
        try {
            // Get the current URL of the page
            String obtainedUrl = driver.getCurrentUrl();
            boolean isSuccess = obtainedUrl.equals(expectedUrl);

            // Capture a screenshot for verification
            String screenshotPath = bugReport.captureScreenshot("SpecialMenuURLVerification_" + scenarioID);

            // Log the result of the verification
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Special menu item URL",
                    expectedUrl,
                    obtainedUrl,
                    screenshotPath,
                    isSuccess
            );

            // Log a bug if the URL verification fails
            if (!isSuccess) {
                bugReport.logBug(
                        scenarioID + ": Special Menu Navigation Failed",
                        "Special menu did not navigate to the expected URL.",
                        "Click on Special menu and verify URL",
                        expectedUrl,
                        obtainedUrl,
                        "SpecialMenuURLVerification_" + scenarioID
                );
            }

            // Assert that the obtained URL matches the expected URL
            Assert.assertEquals("Special menu navigation failed.", expectedUrl, obtainedUrl);
        } catch (Exception e) {
            // Capture and log any errors during URL verification
            String screenshotPath = bugReport.captureScreenshot("SpecialMenuVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Special menu item URL",
                    "Special menu should navigate to the expected URL",
                    "Error occurred during verification: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }


    @When("the user clicks on the Cart menu item")
    public void clickOnCartMenuItem() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String xpath = "//header/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[3]/a[1]/span[1]";
        try {
            // Click on the Cart menu item
            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(2000);

            // Capture a screenshot and log the success
            String screenshotPath = bugReport.captureScreenshot("CartMenuClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Cart menu item",
                    "Cart menu should be clicked successfully",
                    "Cart menu clicked successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture and log any errors that occur
            String screenshotPath = bugReport.captureScreenshot("CartMenuClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Cart menu item",
                    "Cart menu should be clicked successfully",
                    "Error occurred while clicking on Cart menu: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }

    @Then("the Cart menu item URL is verified")
    public void verifyCartMenuItemUrl() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String expectedUrl = "https://automationteststore.com/index.php?rt=checkout/cart";
        try {
            // Obtain the current URL
            String obtainedUrl = driver.getCurrentUrl();
            boolean isSuccess = obtainedUrl.equals(expectedUrl);

            // Capture a screenshot and log the result
            String screenshotPath = bugReport.captureScreenshot("CartMenuURLVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Cart menu item URL",
                    expectedUrl,
                    obtainedUrl,
                    screenshotPath,
                    isSuccess
            );

            // Log a bug if the navigation failed
            if (!isSuccess) {
                bugReport.logBug(
                        scenarioID + ": Cart Menu Navigation Failed",
                        "Cart menu did not navigate to the expected URL.",
                        "Click on Cart menu and verify URL",
                        expectedUrl,
                        obtainedUrl,
                        "CartMenuURLVerificationError_" + scenarioID
                );
            }

            // Assert that the navigation was successful
            Assert.assertEquals("Cart menu navigation failed.", expectedUrl, obtainedUrl);
        } catch (Exception e) {
            // Capture and log the error
            String screenshotPath = bugReport.captureScreenshot("CartMenuVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Cart menu item URL",
                    "Cart menu should navigate to the expected URL",
                    "Error occurred during verification: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }


    // Step to click on Logoff and verify logout message
    @When("the user click Logout menu item")
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



    @Then("the user should check the logout message {string}")
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




    @When("the user clicks on Account and selects Check Your Order")
    public void clickOnAccountAndCheckYourOrder() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String accountMenuXpath = "//header/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[2]/a[1]/span[1]";
        String checkYourOrderXpath = "//ul[@id='main_menu_top']//span[contains(@class,'menu_text')][normalize-space()='Check Your Order']";

        try {
            // Hover over the Account menu
            WebElement accountMenu = driver.findElement(By.xpath(accountMenuXpath));
            actions.moveToElement(accountMenu).perform();
            Thread.sleep(5000); // Pause to ensure the hover action is effective

            // Click on "Check Your Order"
            WebElement checkYourOrder = driver.findElement(By.xpath(checkYourOrderXpath));
            checkYourOrder.click();
            Thread.sleep(5000);

            // Capture screenshot and log step result
            String screenshotPath = bugReport.captureScreenshot("CheckYourOrderClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Account > Check Your Order",
                    "Check Your Order should be selected successfully",
                    "Check Your Order selected successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture screenshot and log error
            String screenshotPath = bugReport.captureScreenshot("CheckYourOrderClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Account > Check Your Order",
                    "Check Your Order should be selected successfully",
                    "Error occurred while selecting Check Your Order: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }


    @Then("the Check Your Order URL is verified")
    public void verifyCheckYourOrderUrl() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/invoice";

        try {
            // Get the current URL and compare it with the expected URL
            String obtainedUrl = driver.getCurrentUrl();
            boolean isSuccess = obtainedUrl.equals(expectedUrl);

            // Capture screenshot and log step result
            String screenshotPath = bugReport.captureScreenshot("CheckYourOrderURLVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Check Your Order URL",
                    expectedUrl,
                    obtainedUrl,
                    screenshotPath,
                    isSuccess
            );

            // Log a bug report if the URL verification fails
            if (!isSuccess) {
                bugReport.logBug(
                        scenarioID + ": Check Your Order Navigation Failed",
                        "Check Your Order did not navigate to the expected URL.",
                        "Click on Check Your Order and verify URL",
                        expectedUrl,
                        obtainedUrl,
                        "CheckYourOrderURLVerification_" + scenarioID
                );
            }

            // Assert to ensure the test fails if the URLs don't match
            Assert.assertEquals("Check Your Order navigation failed.", expectedUrl, obtainedUrl);

        } catch (Exception e) {
            // Capture screenshot and log error if exception occurs
            String screenshotPath = bugReport.captureScreenshot("CheckYourOrderVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Check Your Order URL",
                    "Check Your Order should navigate to the expected URL",
                    "Error occurred during verification: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }



    @When("the user clicks on the Continue button")
    public void clickOnContinueButton() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String continueButtonXpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/fieldset[1]/div[3]/div[1]/button[1]";

        try {
            // Click on the Continue button
            driver.findElement(By.xpath(continueButtonXpath)).click();
            Thread.sleep(2000);

            // Log the success of clicking the Continue button
            String screenshotPath = bugReport.captureScreenshot("ContinueButtonClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Continue button",
                    "Continue button should be clicked successfully",
                    "Continue button clicked successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Log any error that occurs while clicking the Continue button
            String screenshotPath = bugReport.captureScreenshot("ContinueButtonClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Continue button",
                    "Continue button should be clicked successfully",
                    "Error occurred while clicking the Continue button: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }


    @Then("the error messages are verified")
    public void verifyErrorMessages() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically

        try {
            // Error messages to verify
            String orderIDError = "Order ID is required field!";
            String emailError = "E-Mail Address does not appear to be valid!";

            // Check for the presence of the first error message
            boolean isOrderIDErrorPresent = driver.getPageSource().contains(orderIDError);
            String orderIDErrorScreenshot = bugReport.captureScreenshot("OrderIDErrorVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Order ID error message",
                    "Error message should be displayed: " + orderIDError,
                    isOrderIDErrorPresent ? "Error message displayed successfully" : "Error message not displayed",
                    orderIDErrorScreenshot,
                    isOrderIDErrorPresent
            );

            // Check for the presence of the second error message
            boolean isEmailErrorPresent = driver.getPageSource().contains(emailError);
            String emailErrorScreenshot = bugReport.captureScreenshot("EmailErrorVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify Email Address error message",
                    "Error message should be displayed: " + emailError,
                    isEmailErrorPresent ? "Error message displayed successfully" : "Error message not displayed",
                    emailErrorScreenshot,
                    isEmailErrorPresent
            );

            // Assert both error messages are present
            Assert.assertTrue("Order ID error message is not displayed as expected.", isOrderIDErrorPresent);
            Assert.assertTrue("Email Address error message is not displayed as expected.", isEmailErrorPresent);
        } catch (Exception e) {
            // Log any errors that occur during verification
            String errorScreenshot = bugReport.captureScreenshot("ErrorMessageVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify error messages",
                    "Error messages should be displayed correctly",
                    "Error occurred during verification: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }



    @When("the user enters Order ID and Email and clicks Continue")
    public void fillOrderIdAndEmailAndClickContinue() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String orderIdXpath = "//input[@id='CheckOrderFrm_order_id']";
        String emailXpath = "//input[@id='CheckOrderFrm_email']";
        String continueButtonXpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/fieldset[1]/div[3]/div[1]/button[1]";

        try {
            // Fill the Order ID field
            driver.findElement(By.xpath(orderIdXpath)).sendKeys("1111");
            String orderIdScreenshot = bugReport.captureScreenshot("OrderIDFieldFilled_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Fill Order ID field",
                    "Order ID field should be filled successfully",
                    "Order ID field filled successfully with value: 1111",
                    orderIdScreenshot,
                    true
            );

            // Fill the Email field
            driver.findElement(By.xpath(emailXpath)).sendKeys("chaben.nader@gmail.com");
            String emailFieldScreenshot = bugReport.captureScreenshot("EmailFieldFilled_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Fill Email field",
                    "Email field should be filled successfully",
                    "Email field filled successfully with value: chaben.nader@gmail.com",
                    emailFieldScreenshot,
                    true
            );

            // Click the Continue button
            driver.findElement(By.xpath(continueButtonXpath)).click();
            Thread.sleep(2000);

            String continueButtonScreenshot = bugReport.captureScreenshot("ContinueButtonClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click Continue button",
                    "Continue button should be clicked successfully",
                    "Continue button clicked successfully",
                    continueButtonScreenshot,
                    true
            );
        } catch (Exception e) {
            // Capture screenshot and log any errors
            String errorScreenshot = bugReport.captureScreenshot("FillFieldsAndClickContinueError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Fill fields and click Continue",
                    "Fields should be filled and Continue button clicked successfully",
                    "Error occurred: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }


    @Then("the error message for missing order is verified and handled")
    public void verifyAndHandleOrderNotFoundError() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String errorText = "The order you have requested could not be found!";
        String continueButtonXpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]";

        try {
            // Check for the error message
            boolean isErrorPresent = driver.getPageSource().contains(errorText);
            String errorScreenshot = bugReport.captureScreenshot("OrderNotFoundError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify order not found error message",
                    errorText,
                    isErrorPresent ? "Error message found" : "Error message not found",
                    errorScreenshot,
                    isErrorPresent
            );

            // Handle the error if it exists
            if (isErrorPresent) {
                WebElement continueButton = driver.findElement(By.xpath(continueButtonXpath));
                continueButton.click();
                Thread.sleep(2000);

                String navigationScreenshot = bugReport.captureScreenshot("ContinueAfterError_" + scenarioID);
                ReportLogger.logStepResult(
                        scenarioID + ": Click Continue after order not found error",
                        "Navigated to appropriate page after error handling",
                        "Navigation performed successfully",
                        navigationScreenshot,
                        true
                );
            }

            // Assert to ensure the error message was displayed
            Assert.assertTrue("The order not found error message was not displayed as expected.", isErrorPresent);

        } catch (Exception e) {
            // Capture and log any errors
            String errorHandlingScreenshot = bugReport.captureScreenshot("ErrorHandlingFailure_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify and handle order not found error",
                    "Error handling should be completed successfully",
                    "Error occurred during error handling: " + e.getMessage(),
                    errorHandlingScreenshot,
                    false
            );
            throw e;
        }
    }



    @When("the user logs in again")
    public void enterLoginCredentialsAndSubmit() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically

        try {
            // Navigate to the login page
            driver.get("https://automationteststore.com/index.php?rt=account/login");

            // Fill in login credentials
            driver.findElement(By.xpath("//input[@id='loginFrm_loginname']")).sendKeys("Nader");
            driver.findElement(By.xpath("//input[@id='loginFrm_password']")).sendKeys("Test123");

            // Click the login button
            driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")).click();
            Thread.sleep(5000);

            // Capture a screenshot and log the result
            String screenshotPath = bugReport.captureScreenshot("LoginFormSubmitted_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Enter login credentials and submit the form",
                    "Login form should be submitted successfully",
                    "Login form submitted successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture and log any errors
            String screenshotPath = bugReport.captureScreenshot("LoginFormSubmissionError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Enter login credentials and submit the form",
                    "Login form should be submitted successfully",
                    "Error occurred during login submission: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }

    @Then("the user is navigated to the account history page")
    public void verifyLoginNavigation() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/history";

        try {
            // Get the current URL
            obtainedUrl = driver.getCurrentUrl();
            boolean isSuccess = obtainedUrl.equals(expectedUrl);

            // Capture a screenshot and log the result
            String screenshotPath = bugReport.captureScreenshot("LoginNavigationVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify login navigation",
                    expectedUrl,
                    obtainedUrl,
                    screenshotPath,
                    isSuccess
            );

            // Log a bug if navigation fails
            if (!isSuccess) {
                bugReport.logBug(
                        scenarioID + ": Login Navigation Failed",
                        "User was not navigated to the account page after login.",
                        "Submit login form and verify navigation",
                        expectedUrl,
                        obtainedUrl,
                        "LoginNavigationError_" + scenarioID
                );
            }

            // Assert to ensure the test fails if the URLs don't match
            Assert.assertEquals("Login navigation failed. Expected: " + expectedUrl + " but got: " + obtainedUrl, expectedUrl, obtainedUrl);

        } catch (Exception e) {
            // Capture and log any errors
            String screenshotPath = bugReport.captureScreenshot("LoginNavigationVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify login navigation",
                    "User should navigate to the account page",
                    "Error occurred during navigation verification: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }



    @When("the user clicks on Account and selects Check Your Order again")
    public void clickOnAccountAndCheckYourOrderAgain() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically

        try {
            // Click on Account menu
            String accountMenuXpath = "//header/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[2]/a[1]/span[1]";
            driver.findElement(By.xpath(accountMenuXpath)).click();
            Thread.sleep(1000);

            // Hover over Account menu and click Check Your Order
            String checkOrderXpath = "//header/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[2]/ul[1]/li[1]/a[1]/span[1]";
            WebElement accountMenu = driver.findElement(By.xpath(accountMenuXpath));
            actions.moveToElement(accountMenu).perform();
            Thread.sleep(500);

            driver.findElement(By.xpath(checkOrderXpath)).click();
            Thread.sleep(2000);

            // Capture a screenshot and log the step result
            String screenshotPath = bugReport.captureScreenshot("CheckYourOrderAgainClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Account > Check Your Order again",
                    "Check Your Order should be selected successfully",
                    "Check Your Order selected successfully",
                    screenshotPath,
                    true
            );

        } catch (Exception e) {
            // Capture a screenshot and log the error
            String screenshotPath = bugReport.captureScreenshot("CheckYourOrderAgainClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Account > Check Your Order again",
                    "Check Your Order should be selected successfully",
                    "Error occurred while selecting Check Your Order: " + e.getMessage(),
                    screenshotPath,
                    false
            );
            throw e;
        }
    }



    @Then("the Check Your Order history URL is verified")
    public void verifyCheckYourOrderHistoryUrl() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/history";

        try {
            // Use the helper method to verify the URL
            verifyMenuItemUrl("Check Your Order History", expectedUrl);

            // Capture a screenshot and log the successful step result
            String screenshotPath = bugReport.captureScreenshot("CheckYourOrderHistoryVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify navigation to Check Your Order History",
                    expectedUrl,
                    "Successfully navigated to Check Your Order History page",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture a screenshot and log the error if verification fails
            String errorScreenshotPath = bugReport.captureScreenshot("CheckYourOrderHistoryVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify navigation to Check Your Order History",
                    expectedUrl,
                    "Error during navigation to Check Your Order History: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }



    @When("the user clicks on the View button for an order")
    public void clickOnViewButtonForOrder() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically

        try {
            // Click on the View button
            String viewButtonXpath = "//body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/button[1]";
            driver.findElement(By.xpath(viewButtonXpath)).click();
            Thread.sleep(2000);

            // Capture a screenshot and log the successful step result
            String screenshotPath = bugReport.captureScreenshot("ViewButtonClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on the View button for an order",
                    "View button should be clicked successfully",
                    "View button clicked successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture a screenshot and log the error if clicking the View button fails
            String errorScreenshotPath = bugReport.captureScreenshot("ViewButtonClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on the View button for an order",
                    "View button should be clicked successfully",
                    "Error occurred while clicking the View button: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }


    @Then("the user is navigated to the invoice page")
    public void verifyInvoicePage() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/invoice&order_id=45187";

        try {
            // Use the helper method to verify the URL
            verifyMenuItemUrl("Invoice Page", expectedUrl);

            // Capture a screenshot and log the successful step result
            String screenshotPath = bugReport.captureScreenshot("InvoicePageVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify navigation to Invoice Page",
                    expectedUrl,
                    "Successfully navigated to Invoice page",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture a screenshot and log the error if URL verification fails
            String errorScreenshotPath = bugReport.captureScreenshot("InvoicePageVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify navigation to Invoice Page",
                    expectedUrl,
                    "Error during navigation to Invoice Page: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }


    @When("the user clicks on the Print button")
    public void clickOnPrintButton() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically

        try {
            // Click on the Print button
            String printButtonXpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/a[2]";
            driver.findElement(By.xpath(printButtonXpath)).click();
            Thread.sleep(2000);

            // Log the success of clicking the Print button
            String screenshotPath = bugReport.captureScreenshot("PrintButtonClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on the Print button",
                    "Print button should be clicked successfully",
                    "Print button clicked successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Log any error that occurs while clicking the Print button
            String errorScreenshotPath = bugReport.captureScreenshot("PrintButtonClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on the Print button",
                    "Print button should be clicked successfully",
                    "Error occurred while clicking the Print button: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }



    @Then("the print tab is opened and closed")
    public void verifyAndClosePrintTab() throws InterruptedException, AWTException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        try {
            // Wait for 5 seconds before closing
            Thread.sleep(5000);

            // Use a Robot class to send the Escape key to close the print preview
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);

            // Log the result of the close action
            String closeScreenshotPath = bugReport.captureScreenshot("PrintTabClosed_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Close Print Tab",
                    "Print tab should close successfully using Escape key",
                    "Print tab closed successfully",
                    closeScreenshotPath,
                    true
            );
        } catch (Exception e) {
            String errorScreenshotPath = bugReport.captureScreenshot("PrintTabError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify and close Print Tab",
                    "Print tab should open and close successfully",
                    "Error occurred while handling print tab: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }






    @When("the user clicks on the Continue button twice")
    public void clickOnContinueButtonTwice() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically

        try {
            // Click on the first Continue button
            String firstContinueButtonXpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/a[1]";
            driver.findElement(By.xpath(firstContinueButtonXpath)).click();
            Thread.sleep(5000);

            // Capture a screenshot and log the first Continue button click
            String firstScreenshotPath = bugReport.captureScreenshot("FirstContinueButtonClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on the first Continue button",
                    "First Continue button should be clicked successfully",
                    "First Continue button clicked successfully",
                    firstScreenshotPath,
                    true
            );

            // Click on the second Continue button
            String secondContinueButtonXpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[22]/div[1]/a[1]";
            driver.findElement(By.xpath(secondContinueButtonXpath)).click();
            Thread.sleep(2000);

            // Capture a screenshot and log the second Continue button click
            String secondScreenshotPath = bugReport.captureScreenshot("SecondContinueButtonClick_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on the second Continue button",
                    "Second Continue button should be clicked successfully",
                    "Second Continue button clicked successfully",
                    secondScreenshotPath,
                    true
            );

        } catch (Exception e) {
            // Capture a screenshot and log any error
            String errorScreenshot = bugReport.captureScreenshot("ContinueButtonClickError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click on Continue buttons",
                    "Both Continue buttons should be clicked successfully",
                    "Error occurred while clicking Continue buttons: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }


    @Then("the user navigated to the account page")
    public void verifyFinalAccountPage() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";

        try {
            // Use the helper method to verify the URL
            verifyMenuItemUrl("Final Account Page", expectedUrl);

            // Capture a screenshot and log the successful step result
            String screenshotPath = bugReport.captureScreenshot("FinalAccountPageVerification_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify navigation to Final Account Page",
                    expectedUrl,
                    "Successfully navigated to Final Account page",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            // Capture a screenshot and log the error if URL verification fails
            String errorScreenshotPath = bugReport.captureScreenshot("FinalAccountPageVerificationError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Verify navigation to Final Account Page",
                    expectedUrl,
                    "Error during navigation to Final Account Page: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }

    // Helper method to verify the current URL for a menu item
    private void verifyMenuItemUrl(String menuItem, String expectedUrl) {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        obtainedUrl = driver.getCurrentUrl();
        boolean isSuccess = obtainedUrl.equals(expectedUrl);

        // Capture a screenshot and log the step result
        String screenshotPath = bugReport.captureScreenshot(menuItem + "Verification_" + scenarioID);
        ReportLogger.logStepResult(
                scenarioID + ": Click and verify " + menuItem,
                expectedUrl,
                obtainedUrl,
                screenshotPath,
                isSuccess
        );

        // Log a bug if the navigation fails
        if (!isSuccess) {
            bugReport.logBug(
                    scenarioID + ": " + menuItem + " Navigation Failed",
                    "Failed to navigate to " + menuItem,
                    "Click on " + menuItem + " in the top menu",
                    expectedUrl,
                    obtainedUrl,
                    menuItem + "Error_" + scenarioID
            );
        }

        // Assert to ensure the test fails if the URLs don't match
        Assert.assertEquals("Navigation to " + menuItem + " failed.", expectedUrl, obtainedUrl);
    }


    @When("A test confirmation popup is displayed")
    public void displayTestConfirmationPopup() {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        try {
            // Display a confirmation popup
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('Test completed successfully!');");

            // Log that the popup was displayed successfully
            ReportLogger.logStepResult(
                    scenarioID + ": Display test confirmation popup",
                    "Popup should be displayed with a success message",
                    "Popup displayed successfully",
                    null, // No screenshot at this stage since the alert is active
                    true
            );
        } catch (Exception e) {
            // Log the error if popup display fails
            ReportLogger.logStepResult(
                    scenarioID + ": Display test confirmation popup",
                    "Popup should be displayed with a success message",
                    "Error occurred while displaying popup: " + e.getMessage(),
                    null,
                    false
            );
            throw e;
        }
    }



    @Then("after 5 seconds the browser and popup are closed")
    public void closeBrowserAndPopupAfterDelay() throws InterruptedException {
        String scenarioID = incrementScenarioID(); // Increment Scenario ID dynamically
        try {
            // Wait for 5 seconds
            Thread.sleep(5000);

            // Switch to the alert and get its text
            Alert alert = driver.switchTo().alert();
            String popupMessage = alert.getText();

            // Accept the alert to close the popup
            alert.accept();

            // Log the successful handling of the popup
            ReportLogger.logStepResult(
                    scenarioID + ": Handle confirmation popup",
                    "Popup should be handled successfully",
                    "Popup handled successfully with message: " + popupMessage,
                    null, // No screenshot of the alert itself
                    true
            );

            // Take a screenshot after the popup is closed
            String browserScreenshotPath = bugReport.captureScreenshot("BrowserAfterPopup_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Take screenshot after popup is closed",
                    "Screenshot of the browser should be captured successfully",
                    "Screenshot captured successfully",
                    browserScreenshotPath,
                    true
            );

            // Close the browser
            driver.quit();
            ReportLogger.logStepResult(
                    scenarioID + ": Close browser",
                    "Browser should be closed successfully",
                    "Browser closed successfully",
                    null,
                    true
            );
        } catch (Exception e) {
            // Log any error that occurs while handling the popup or closing the browser
            String errorScreenshotPath = bugReport.captureScreenshot("CloseBrowserAndPopupError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Close browser and popup",
                    "Browser and popup should be closed successfully",
                    "Error occurred while closing browser or popup: " + e.getMessage(),
                    errorScreenshotPath,
                    false
            );
            throw e;
        }
    }



}
