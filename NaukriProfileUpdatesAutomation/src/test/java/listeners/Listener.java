package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.BaseClass;
import testData.ExtentReporterNG;

public class Listener extends BaseClass implements ITestListener {
    private ExtentTest test;
    private static final ExtentReports extent = ExtentReporterNG.getReportObject();
    private final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "🚀 Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "✅ Test Passed: " + result.getMethod().getMethodName());

        WebDriver driver = getDriverInstance(result);
        if (driver != null) {
            try {
                String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
                extentTest.get().addScreenCaptureFromPath(filePath, "Pass Screenshot");
            } catch (IOException e) {
                extentTest.get().log(Status.WARNING, "⚠️ Screenshot capture failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "❌ Test Failed: " + result.getMethod().getMethodName());
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = getDriverInstance(result);
        if (driver != null) {
            try {
                String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
                extentTest.get().addScreenCaptureFromPath(filePath, "Fail Screenshot");
            } catch (IOException e) {
                extentTest.get().log(Status.FAIL, "⚠️ Screenshot capture failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "⏭️ Test Skipped: " + result.getMethod().getMethodName());
        extentTest.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("📌 Extent Reports generated successfully!");
    }

    // Helper method to get WebDriver instance from test class
    private WebDriver getDriverInstance(ITestResult result) {
        try {
            return (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            System.err.println("⚠️ Could not fetch WebDriver instance: " + e.getMessage());
            extentTest.get().log(Status.WARNING, "⚠️ WebDriver instance fetch failed: " + e.getMessage());
            return null;
        }
    }
}
