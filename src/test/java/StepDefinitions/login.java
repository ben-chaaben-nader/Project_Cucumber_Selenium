package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    static int scenarioIDCounter = 0;

    // Method to dynamically generate a scenario ID
    private String incrementScenarioID() {
        return "TC_" + (++scenarioIDCounter);
    }

    @Given("the user is on the login page of automationteststore")
    public void the_user_is_on_the_login_page_of_automationteststore() {
        String scenarioID = "1";

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the website and maximize the window
        driver.get("https://automationteststore.com/");
        driver.manage().window().maximize();

        // Log the start of the scenario
        ReportLogger.logScenarioStart(scenarioID, "Login Test");
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) throws InterruptedException {
        String scenarioID = incrementScenarioID();

        try {
            driver.findElement(By.xpath("//a[contains(text(),'Login or register')]")).click();
            Thread.sleep(2000);

            // Enter username and password
            driver.findElement(By.id("loginFrm_loginname")).sendKeys(username);
            driver.findElement(By.id("loginFrm_password")).sendKeys(password);

            String screenshotPath = ReportLogger.captureScreenshot(driver, "LoginFormFilled_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Fill Login Form",
                    "Login form should be filled successfully with provided credentials",
                    "Login form filled successfully",
                    screenshotPath,
                    true
            );
        } catch (Exception e) {
            String errorScreenshot = ReportLogger.captureScreenshot(driver, "LoginFormError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Fill Login Form",
                    "Login form should be filled successfully with provided credentials",
                    "Error occurred while filling login form: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }

    @And("clicks on the login button")
    public void clicks_on_the_login_button() throws InterruptedException {
        String scenarioID = incrementScenarioID();

        try {
            driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            String errorScreenshot = ReportLogger.captureScreenshot(driver, "LoginButtonError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Click Login Button",
                    "Login button should be clicked successfully",
                    "Error occurred while clicking login button: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }

    @Then("the user should be redirected to the Home")
    public void the_user_should_be_redirected_to_the_Home() {
        String scenarioID = incrementScenarioID();

        try {
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("https://automationteststore.com/index.php?rt=account/account")) {
                String details = "User successfully redirected to the Home page.";
                ReportLogger.logScenarioEnd(scenarioID, "Login Test", true, details);
            } else {
                String details = "User is not on the Home page. Current URL: " + currentUrl;
                ReportLogger.logScenarioEnd(scenarioID, "Login Test", false, details);
                Assert.fail(details);
            }
        } finally {
            displayPopup("Test completed successfully! Closing the browser...");
            ReportLogger.logStepResult(
                    scenarioID + ": Display Popup",
                    "Popup with a confirmation message should appear before closing the browser",
                    "Popup displayed successfully",
                    null,
                    true
            );
            waitFor(5);
            closeBrowser(scenarioID);
        }
    }

    @Then("an error message should be displayed if invalid")
    public void an_error_message_should_be_displayed_if_invalid() {
        String scenarioID = incrementScenarioID();

        try {
            String errorXPath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]";
            boolean isErrorPresent = driver.findElements(By.xpath(errorXPath)).size() > 0;

            if (isErrorPresent) {
                String errorMessage = driver.findElement(By.xpath(errorXPath)).getText().trim();
                String expectedErrorMessage = "Error: Incorrect login or password provided.";

                if (errorMessage.startsWith("×")) {
                    errorMessage = errorMessage.substring(1).trim();
                }

                if (errorMessage.equals(expectedErrorMessage)) {
                    String screenshotPath = ReportLogger.captureScreenshot(driver, "ErrorMessage_" + scenarioID);
                    ReportLogger.logStepResult(
                            scenarioID + ": Verify Error Message",
                            "Error message should be displayed for invalid credentials",
                            "Correct error message displayed: " + errorMessage,
                            screenshotPath,
                            true
                    );
                } else {
                    String screenshotPath = ReportLogger.captureScreenshot(driver, "IncorrectErrorMessage_" + scenarioID);
                    ReportLogger.logStepResult(
                            scenarioID + ": Verify Error Message",
                            "Error message should match: " + expectedErrorMessage,
                            "Incorrect error message displayed: " + errorMessage,
                            screenshotPath,
                            false
                    );
                    Assert.fail("Incorrect error message displayed. Expected: " + expectedErrorMessage + " but got: " + errorMessage);
                }
            } else {
                String screenshotPath = ReportLogger.captureScreenshot(driver, "NoErrorMessage_" + scenarioID);
                ReportLogger.logStepResult(
                        scenarioID + ": Verify Error Message",
                        "Error message should be displayed for invalid credentials",
                        "No error message displayed on the page.",
                        screenshotPath,
                        false
                );
                Assert.fail("Error message element not found in the DOM.");
            }
        } finally {
            displayPopup("Test completed with error verification. Closing the browser...");
            ReportLogger.logStepResult(
                    scenarioID + ": Display Popup",
                    "Popup with a confirmation message should appear before closing the browser",
                    "Popup displayed successfully",
                    null,
                    true
            );
            waitFor(5);
            closeBrowser(scenarioID);
        }
    }

    private void displayPopup(String message) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('" + message + "');");
        } catch (Exception e) {
            System.err.println("Error displaying popup: " + e.getMessage());
        }
    }

    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void closeBrowser(String scenarioID) {
        driver.quit();
        ReportLogger.logStepResult(
                scenarioID + ": Close Browser",
                "Browser should close successfully",
                "Browser closed successfully",
                null,
                true
        );
    }
}
