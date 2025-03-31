package testData;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReporterNG {
    private static ExtentReports extent;

    public static ExtentReports getReportObject() {
        if (extent != null) {
            return extent; // Prevent duplicate instances
        }

        // ✅ Use Jenkins workspace-friendly directory
        String reportDir = System.getProperty("user.dir") + "/test-output/ExtentReports/";
        File dir = new File(reportDir);

        // Ensure reports directory exists
        if (!dir.exists() && !dir.mkdirs()) {
            System.err.println("❌ Failed to create reports directory: " + reportDir);
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

        // ✅ Wait for 15 seconds to ensure the report is created
        try {
            Thread.sleep(15000); // Wait 15 seconds
            System.out.println("✅ 15-second wait completed. Report should be ready.");
        } catch (InterruptedException e) {
            System.err.println("❌ Thread sleep interrupted: " + e.getMessage());
        }

        return extent;
    }
}
