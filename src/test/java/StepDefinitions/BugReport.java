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

public class BugReport {
    private static final String BUG_REPORT_FILE = "bug-report.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String SCREENSHOTS_DIR = "screenshots/";
    private WebDriver driver;

    // Constructor
    public BugReport(WebDriver driver) {
        this.driver = driver;
        createScreenshotsDirectory();
    }

    // Create screenshots directory if it doesn't exist
    private void createScreenshotsDirectory() {
        try {
            Files.createDirectories(Paths.get(SCREENSHOTS_DIR));
        } catch (IOException e) {
            System.out.println("Error creating screenshots directory: " + e.getMessage());
        }
    }

    // Capture screenshot
    public String captureScreenshot(String screenshotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = SCREENSHOTS_DIR + screenshotName + ".png";
        try {
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
        }
        return screenshotPath;
    }

    // Log bug with six arguments
    public void logBug(String bugTitle, String bugDescription, String stepsToReproduce, String expectedBehavior, String actualBehavior, String screenshotName) {
        try (FileWriter fw = new FileWriter(BUG_REPORT_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = DATE_FORMAT.format(new Date());
            pw.println("======= BUG REPORT =======");
            pw.println("Timestamp: " + timestamp);
            pw.println("Bug Title: " + bugTitle);
            pw.println("Description: " + bugDescription);
            pw.println("Steps to Reproduce: " + stepsToReproduce);
            pw.println("Expected Behavior: " + expectedBehavior);
            pw.println("Actual Behavior: " + actualBehavior);

            // Include screenshot path if provided
            if (screenshotName != null && !screenshotName.isEmpty()) {
                String screenshotPath = captureScreenshot(screenshotName);
                pw.println("Screenshot Path: " + screenshotPath);
            }

            pw.println("==========================");
        } catch (IOException e) {
            System.out.println("Error writing to bug report file: " + e.getMessage());
        }
    }
}
