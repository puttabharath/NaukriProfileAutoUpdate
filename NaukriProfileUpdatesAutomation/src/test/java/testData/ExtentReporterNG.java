package testData;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReporterNG {
    private static ExtentReports extent;

    public static ExtentReports getReportObject() {
        if (extent != null) {
            return extent; // Return existing instance to prevent duplicates
        }

        // Get project directory dynamically
  String reportDir = "C:\\Users\\bharath\\git\\NaukriProfileAutoUpdate\\NaukriProfileUpdatesAutomation\\reports\\";
        File dir = new File(reportDir);

        // Ensure reports directory exists
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("✅ Reports directory created: " + reportDir);
            } else {
                System.err.println("❌ Failed to create reports directory!");
            }
        }

        // Generate timestamped report file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String reportFile = "Naukri_Auto_Update_Profile_" + timestamp + ".html";
        String reportPath = reportDir + reportFile;

        // Create and configure ExtentSparkReporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Naukri Test Results");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setEncoding("UTF-8");
        reporter.config().enableOfflineMode(true);

        // Create ExtentReports instance and attach reporter
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Bharath Kumar Putta");
        extent.setSystemInfo("Host Name", "Naukri App");
        extent.setSystemInfo("Environment", "Automation Script - QA");
        extent.setSystemInfo("User Name", "PBK");

        System.out.println("✅ Extent Reports initialized at: " + reportPath);

        // Copy latest report as 'latest.html' for easy access
        try {
            Path latestReportPath = Paths.get(reportDir + "latest.html");
            Files.deleteIfExists(latestReportPath);
            Files.copy(Paths.get(reportPath), latestReportPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ Latest report copied to: " + latestReportPath);
        } catch (IOException e) {
            System.err.println("❌ Could not create latest.html file: " + e.getMessage());
        }

        return extent;
    }
}
