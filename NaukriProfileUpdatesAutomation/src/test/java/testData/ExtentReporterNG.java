package testData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
    private static ExtentReports extent;

    public static ExtentReports getReportObject() {
        if (extent != null) {
            return extent; // Reuse existing instance
        }

        // Define base directory for reports
        String reportDir = System.getProperty("user.dir") + "/test-output/ExtentReports/";
        File dir = new File(reportDir);
        if (!dir.exists() && !dir.mkdirs()) {
            System.err.println("❌ Failed to create reports directory: " + reportDir);
        }

        // Generate timestamped report file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String reportFile = "Naukri_Auto_Update_Profile_" + timestamp + ".html";
        String reportPath = reportDir + reportFile;

        // Configure Spark Reporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Naukri Test Results");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setEncoding("UTF-8");
        reporter.config().enableOfflineMode(true); // Embed JS and CSS for Jenkins compatibility

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Bharath Kumar Putta");
        extent.setSystemInfo("Host Name", "Naukri App");
        extent.setSystemInfo("Environment", "Automation Script - QA");
        extent.setSystemInfo("User Name", "PBK");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        System.out.println("✅ Extent Reports initialized at: " + reportPath);

        // Create a static 'latest.html' file Jenkins can point to
        try {
            Path latestReport = Paths.get(reportDir + "latest.html");
            Files.copy(Paths.get(reportPath), latestReport, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ Copied report to: " + latestReport);
        } catch (IOException e) {
            System.err.println("❌ Failed to copy report as latest.html: " + e.getMessage());
        }

        return extent;
    }


@AfterSuite
public void tearDown() {
    ExtentReporterNG.getReportObject().flush();
    System.out.println("✅ Extent Report flushed and finalized.");
}
}
