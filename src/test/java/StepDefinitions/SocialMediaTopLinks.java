package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SocialMediaTopLinks {
    WebDriver driver;
    String obtainedUrl;
    String originalWindow;
    BugReport bugReport;
    static int scenarioIDCounter = 0;

    // Increment and return a dynamic scenario ID
    private String incrementScenarioID() {
        return "Scenario_" + (++scenarioIDCounter);
    }

    @Given("the user logs into the Automation Test Store4")
    public void loginToAutomationTestStore() throws InterruptedException {
        String scenarioID = incrementScenarioID();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://automationteststore.com/index.php?rt=account/login");
        driver.manage().window().maximize();
        bugReport = new BugReport(driver);

        try {
            driver.findElement(By.xpath("//input[@id='loginFrm_loginname']")).sendKeys("Nader");
            driver.findElement(By.xpath("//input[@id='loginFrm_password']")).sendKeys("Test123");
            driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")).click();

            Thread.sleep(2000);
            obtainedUrl = driver.getCurrentUrl();
            String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";
            boolean isSuccess = obtainedUrl.equals(expectedUrl);

            String screenshotPath = bugReport.captureScreenshot("LoginPage_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Login to Automation Test Store",
                    expectedUrl,
                    obtainedUrl,
                    screenshotPath,
                    isSuccess
            );

            Assert.assertEquals("Login failed. Expected: " + expectedUrl + " but got: " + obtainedUrl, expectedUrl, obtainedUrl);
        } catch (Exception e) {
            String errorScreenshot = bugReport.captureScreenshot("LoginError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Login to Automation Test Store",
                    "Expected to navigate to the account page",
                    "Error occurred: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }

    @When("the user opens the Facebook link")
    public void openFacebookLink() throws InterruptedException {
        String scenarioID = incrementScenarioID();
        String xpath = "//div[@class='header_block']//a[@title='Facebook'][normalize-space()='Facebook']";
        try {
            driver.findElement(By.xpath(xpath)).click();
            String screenshotPath = bugReport.captureScreenshot("FacebookLinkOpened_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Open Facebook Link",
                    "Facebook link should open successfully",
                    "Facebook link opened",
                    screenshotPath,
                    true
            );
            handleWindowSwitch("https://www.facebook.com/", "Facebook", scenarioID);
        } catch (Exception e) {
            String errorScreenshot = bugReport.captureScreenshot("FacebookLinkError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Open Facebook Link",
                    "Facebook link should open successfully",
                    "Error occurred: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }

    @Then("the user should be redirected to Facebook")
    public void verifyFacebookRedirection() throws InterruptedException {
        verifyCurrentTabRedirection("https://www.facebook.com/", "Facebook");
    }

    @When("the user opens the Twitter link")
    public void openTwitterLink() throws InterruptedException {
        String scenarioID = incrementScenarioID();
        String xpath = "//div[@class='header_block']//a[@title='Twitter'][normalize-space()='Twitter']";
        try {
            driver.findElement(By.xpath(xpath)).click();
            String screenshotPath = bugReport.captureScreenshot("TwitterLinkOpened_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Open Twitter Link",
                    "Twitter link should open successfully",
                    "Twitter link opened",
                    screenshotPath,
                    true
            );
            handleWindowSwitch("https://twitter.com/", "Twitter", scenarioID);
        } catch (Exception e) {
            String errorScreenshot = bugReport.captureScreenshot("TwitterLinkError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Open Twitter Link",
                    "Twitter link should open successfully",
                    "Error occurred: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }

    @Then("the user should be redirected to Twitter")
    public void verifyTwitterRedirection() throws InterruptedException {
        verifyCurrentTabRedirection("https://twitter.com/", "Twitter");
    }

    @When("the user opens the LinkedIn link")
    public void openLinkedInLink() throws InterruptedException {
        String scenarioID = incrementScenarioID();
        String xpath = "//div[@class='header_block']//a[@title='Linkedin'][normalize-space()='Linkedin']";
        try {
            driver.findElement(By.xpath(xpath)).click();
            String screenshotPath = bugReport.captureScreenshot("LinkedInLinkOpened_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Open LinkedIn Link",
                    "LinkedIn link should open successfully",
                    "LinkedIn link opened",
                    screenshotPath,
                    true
            );
            handleWindowSwitch("https://www.linkedin.com/", "LinkedIn", scenarioID);
        } catch (Exception e) {
            String errorScreenshot = bugReport.captureScreenshot("LinkedInLinkError_" + scenarioID);
            ReportLogger.logStepResult(
                    scenarioID + ": Open LinkedIn Link",
                    "LinkedIn link should open successfully",
                    "Error occurred: " + e.getMessage(),
                    errorScreenshot,
                    false
            );
            throw e;
        }
    }

    @Then("the user should be redirected to LinkedIn")
    public void verifyLinkedInRedirection() throws InterruptedException {
        verifyCurrentTabRedirection("https://www.linkedin.com/", "LinkedIn");
    }

    private void verifyCurrentTabRedirection(String expectedUrl, String socialMedia) throws InterruptedException {
        String scenarioID = incrementScenarioID();
        Thread.sleep(2000);
        obtainedUrl = driver.getCurrentUrl();
        boolean isSuccess = obtainedUrl.startsWith(expectedUrl);

        String screenshotPath = bugReport.captureScreenshot(socialMedia + "Redirection_" + scenarioID);
        ReportLogger.logStepResult(
                scenarioID + ": Verify " + socialMedia + " Redirection",
                expectedUrl,
                obtainedUrl,
                screenshotPath,
                isSuccess
        );

        Assert.assertTrue("Redirection failed for " + socialMedia + ". Expected URL: " + expectedUrl + " but got: " + obtainedUrl, isSuccess);
        driver.navigate().back();
        Thread.sleep(2000);
    }

    private void handleWindowSwitch(String expectedUrl, String socialMedia, String scenarioID) throws InterruptedException {
        originalWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                Thread.sleep(2000);

                obtainedUrl = driver.getCurrentUrl();
                boolean isSuccess = obtainedUrl.startsWith(expectedUrl);

                String screenshotPath = bugReport.captureScreenshot(socialMedia + "NewTab_" + scenarioID);
                ReportLogger.logStepResult(
                        scenarioID + ": Verify " + socialMedia + " in New Tab",
                        expectedUrl,
                        obtainedUrl,
                        screenshotPath,
                        isSuccess
                );

                Assert.assertTrue("Redirection failed for " + socialMedia + " in new tab. Expected URL: " + expectedUrl + " but got: " + obtainedUrl, isSuccess);

                driver.close();
                driver.switchTo().window(originalWindow);
                break;
            }
        }
    }

    @Then("the test should end with a confirmation popup")
    public void displayConfirmationPopup() throws InterruptedException {
        String scenarioID = incrementScenarioID();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('Test completed successfully!');");
            Thread.sleep(5000);
            driver.switchTo().alert().accept();
            ReportLogger.logStepResult(
                    scenarioID + ": Display Confirmation Popup",
                    "Popup should confirm test completion",
                    "Popup displayed successfully",
                    null,
                    true
            );
        } catch (Exception e) {
            ReportLogger.logStepResult(
                    scenarioID + ": Display Confirmation Popup",
                    "Popup should confirm test completion",
                    "Error occurred: " + e.getMessage(),
                    null,
                    false
            );
            throw e;
        } finally {
            driver.quit();
        }
    }
}
