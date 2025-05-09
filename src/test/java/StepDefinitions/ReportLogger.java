package StepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportLogger {
    // Constants for file paths and environment setup
    private static final String REPORT_FILE = "test-report.txt"; // File for test report
    private static final String SCREENSHOTS_DIR = "screenshots/"; // Directory for screenshots
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String TEST_ENVIRONMENT = "https://automationteststore.com/";
    private static final String BROWSER = "Chrome";
    private static final String OPERATING_SYSTEM = System.getProperty("os.name");

    // Ensure screenshots directory exists
    static {
        try {
            Files.createDirectories(Paths.get(SCREENSHOTS_DIR));
        } catch (IOException e) {
            System.out.println("Error creating screenshots directory: " + e.getMessage());
        }
    }

    // Method to log the start of a test scenario
    public static void logScenarioStart(String scenarioID, String scenarioName) {
        try (FileWriter fw = new FileWriter(REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String startTime = DATE_FORMAT.format(new Date());
            pw.println("======================================");
            pw.println("Test Case ID: " + scenarioID);
            pw.println("Scenario: " + scenarioName);
            pw.println("Start Time: " + startTime);
            pw.println("Environment: " + TEST_ENVIRONMENT);
            pw.println("Browser: " + BROWSER);
            pw.println("Operating System: " + OPERATING_SYSTEM);
            pw.println("======================================");
        } catch (IOException e) {
            System.out.println("Error writing to report file: " + e.getMessage());
        }
    }

    // Method to log the result of each test step for URL-based actions
    public static void logStepResult(String actionDescription, String expectedUrl, String obtainedUrl, String actualResult, boolean isSuccess) {
        try (FileWriter fw = new FileWriter(REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = DATE_FORMAT.format(new Date());
            String status = isSuccess ? "PASSED" : "FAILED";
            pw.println("[" + timestamp + "] Action: " + actionDescription);
            pw.println("Expected: " + expectedUrl);
            pw.println("Obtained: " + obtainedUrl);
            pw.println("Actual: " + actualResult);
            pw.println("Status: " + status);
            pw.println("--------------------------------------");
        } catch (IOException e) {
            System.out.println("Error writing to report file: " + e.getMessage());
        }
    }

    // Overloaded method to log step results for non-URL-based actions (e.g., error messages)
    public static void logStepResult(String actionDescription, String expectedOutcome, String actualOutcome, String status) {
        try (FileWriter fw = new FileWriter(REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = DATE_FORMAT.format(new Date());
            pw.println("[" + timestamp + "] Action: " + actionDescription);
            pw.println("Expected: " + expectedOutcome);
            pw.println("Actual: " + actualOutcome);
            pw.println("Status: " + status);
            pw.println("--------------------------------------");
        } catch (IOException e) {
            System.out.println("Error writing to report file: " + e.getMessage());
        }
    }

    // Method to log the end of a scenario
    public static void logScenarioEnd(String scenarioID, String scenarioName, boolean isSuccess, String details) {
        try (FileWriter fw = new FileWriter(REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String endTime = DATE_FORMAT.format(new Date());
            String status = isSuccess ? "PASSED" : "FAILED";
            pw.println("Scenario: " + scenarioName);
            pw.println("Test Case ID: " + scenarioID);
            pw.println("End Time: " + endTime);
            pw.println("Overall Status: " + status);
            pw.println("Details: " + details);
            pw.println("======================================");
        } catch (IOException e) {
            System.out.println("Error writing to report file: " + e.getMessage());
        }
    }

    // General-purpose logging function for additional information
    public static void log(String message) {
        try (FileWriter fw = new FileWriter(REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = DATE_FORMAT.format(new Date());
            pw.println("[" + timestamp + "] " + message);
        } catch (IOException e) {
            System.out.println("Error writing to report file: " + e.getMessage());
        }
    }

    // Method to log errors encountered during testing
    public static void logError(String errorMessage) {
        try (FileWriter fw = new FileWriter(REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = DATE_FORMAT.format(new Date());
            pw.println("[" + timestamp + "] ERROR: " + errorMessage);
            pw.println("======================================");
        } catch (IOException e) {
            System.out.println("Error writing to report file: " + e.getMessage());
        }
    }

    // Method to capture a screenshot and return the file path
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = SCREENSHOTS_DIR + screenshotName + "_" + timestamp + ".png";
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
        }
        return screenshotPath;
    }

    // Method to capture a screenshot and log it
    public static void captureAndLogScreenshot(WebDriver driver, String actionDescription, boolean isSuccess) {
        String status = isSuccess ? "PASSED" : "FAILED";
        String screenshotName = actionDescription.replaceAll("\\s+", "_") + "_" + status;
        String screenshotPath = captureScreenshot(driver, screenshotName);

        try (FileWriter fw = new FileWriter(REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = DATE_FORMAT.format(new Date());
            pw.println("[" + timestamp + "] Screenshot captured for action: " + actionDescription);
            pw.println("Screenshot Path: " + screenshotPath);
            pw.println("--------------------------------------");
        } catch (IOException e) {
            System.out.println("Error writing screenshot log: " + e.getMessage());
        }
    }
}
