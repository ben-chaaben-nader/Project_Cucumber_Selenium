package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class SocialMediaLinks {
    WebDriver driver;
    Actions actions;
    String obtainedUrl;
    String originalWindow;

    // Step to log into the Automation Test Store3
    @Given("the user is logged the Automation Test Store3")
    public void loginToAutomationTestStore() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://automationteststore.com/index.php?rt=account/login");
        driver.manage().window().maximize();
        actions = new Actions(driver);

        // Enter credentials and submit login form
        driver.findElement(By.xpath("//input[@id='loginFrm_loginname']")).sendKeys("Nader");
        driver.findElement(By.xpath("//input[@id='loginFrm_password']")).sendKeys("Test123");
        driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")).click();

        Thread.sleep(2000);
        obtainedUrl = driver.getCurrentUrl();
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";
        boolean isSuccess = obtainedUrl.equals(expectedUrl);
        ReportLogger.logStepResult("Login to Automation Test Store", expectedUrl, obtainedUrl, obtainedUrl, isSuccess);
        // Assert login was successful
        Assert.assertEquals("Login failed. Expected: " + expectedUrl + " but got: " + obtainedUrl, expectedUrl, obtainedUrl);
    }

    // Step to open and close social media link
    @When("the user opens and closes  {string} link")
    public void openAndCloseSocialMediaLink(String socialMedia2) throws InterruptedException {

        String xpath = "";
        //String expectedUrl = "";

        switch (socialMedia2) {
            case "Facebook":
                xpath = "//div[@class='header_block']//a[@title='Facebook'][normalize-space()='Facebook']";
                //expectedUrl = "https://www.facebook.com/";
                break;
            case "Twitter":
                xpath = "//div[@class='header_block']//a[@title='Twitter'][normalize-space()='Twitter']";
                //expectedUrl = "https://x.com/";
                break;
            case "Linkedin":
                xpath = "//div[@class='header_block']//a[@title='Linkedin'][normalize-space()='Linkedin']";
                // expectedUrl = "https://uk.linkedin.com/";
                return;

        }

        // Click the social media link
        driver.findElement(By.xpath(xpath)).click();
        Thread.sleep(2000);

        // Special case for LinkedIn link to capture a screenshot if necessary
        if (socialMedia2.equalsIgnoreCase("Linkedin")) {
            BugReport bugReport = new BugReport(driver);
            String screenshotName = "LinkedInLinkBug";
            String screenshotPath = bugReport.captureScreenshot(screenshotName);
            System.out.println("Screenshot taken for LinkedIn link: " + screenshotPath);
        }

        //verifyCurrentDownTabRedirection(expectedUrl, socialMedia2);
    }

    @Then("the user should be transferred {string}")
    public void verifyCurrentDownTabRedirection(String expectedUrl, String socialMedia2) throws InterruptedException {
        Thread.sleep(2000);
        obtainedUrl = driver.getCurrentUrl();
        boolean isSuccess = obtainedUrl.startsWith(expectedUrl);

        if (!isSuccess) {
            System.out.println("Redirection to " + socialMedia2 + " failed. Expected URL: " + expectedUrl + ", but got: " + obtainedUrl);
            return;
        }

        ReportLogger.logStepResult("Verify " + socialMedia2 + " link in the same tab", expectedUrl, obtainedUrl, obtainedUrl, isSuccess);
        Assert.assertTrue("Redirection failed for " + socialMedia2 + " in the same tab. Expected URL: " + expectedUrl + " but got: " + obtainedUrl, isSuccess);
        driver.navigate().back();
        Thread.sleep(2000);
    }

}
