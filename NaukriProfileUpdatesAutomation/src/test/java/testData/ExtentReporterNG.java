package testData;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReporterNG {
    private static ExtentReports extent;

    public static ExtentReports getReportObject() {
        if (extent != null) {
            return extent; // Prevent duplicate instances
        }

        // Use Jenkins workspace-friendly directory
        String reportDir = System.getProperty("user.dir") + "/test-output/ExtentReports/";
        File dir = new File(reportDir);

        // Ensure reports directory exists
        if (!dir.exists() && !dir.mkdirs()) {
            System.err.println("❌ Failed to create reports directory: " + reportDir);
        }

        // ✅ Use static filename for Jenkins to always locate
        String reportFile = "ExtentReport.html";
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

        return extent;
    }
}
