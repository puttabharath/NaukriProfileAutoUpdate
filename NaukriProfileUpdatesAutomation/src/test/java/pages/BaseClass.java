package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.ConfigReader;

public class BaseClass {
    public WebDriver driver;

    @BeforeClass
    
    public void setup() throws IOException {    
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Headless mode
        options.addArguments("--window-size=1920,1080"); // Set viewport size
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.addArguments("--disable-gpu"); 
        options.addArguments("--no-sandbox"); 
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--disable-popup-blocking"); // Prevent pop-ups blocking
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(ConfigReader.getProperty("url"));
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return file.getAbsolutePath();
    }

  @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
