package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ProfileUpdation {
    WebDriver driver;
    WebDriverWait wait;

    public ProfileUpdation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//div[@class='nI-gNb-drawer__icon']/div[1]")
    private WebElement threeDots;

    @FindBy(xpath = "//a[text()='View & Update Profile']")
    private WebElement viewUpdateProfile;

    @FindBy(xpath = "//i[@data-title='delete-resume']")
    private WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='lightbox model_open flipOpen']//button[@class='btn-dark-ot'][normalize-space()='Delete']")
    private WebElement deletePopupBtn;

    @FindBy(xpath = "//span[text()='Upload resume']")
    private WebElement updateResumeBtn;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileUploadInput;

    public void profileUpdateModule() {
        try {
            Reporter.log("STEP 1: Initiating profile update module", true);

            // Step 1: Click on the three dots menu
            Reporter.log("STEP 2: Clicking on the menu (three dots)", true);
            wait.until(ExpectedConditions.elementToBeClickable(threeDots)).click();

            // Step 2: Close chat window if it appears
            Reporter.log("STEP 3: Checking and hiding chat window if present", true);
            hideChatWindowIfPresent();

            // Step 3: Click 'View & Update Profile'
            Reporter.log("STEP 4: Clicking on 'View & Update Profile'", true);
            wait.until(ExpectedConditions.elementToBeClickable(viewUpdateProfile)).click();

            // Step 4: Wait for any loader/overlay
            Reporter.log("STEP 5: Waiting for any loading overlays to disappear", true);
            waitForLoadersToDisappear();

            // Step 5: Scroll to resume section and click delete icon
            Reporter.log("STEP 6: Scrolling to the 'Delete Resume' icon", true);
            scrollToElement(deleteIcon);

            Reporter.log("STEP 7: Clicking the 'Delete Resume' icon", true);
            wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();

            // Step 6: Confirm deletion on popup
            Reporter.log("STEP 8: Clicking the confirmation 'Delete' button on popup", true);
            wait.until(ExpectedConditions.elementToBeClickable(deletePopupBtn)).click();
            Reporter.log("✔ Resume successfully deleted", true);

            // Step 7: Click upload resume button
            Reporter.log("STEP 9: Clicking 'Upload Resume' button", true);
            wait.until(ExpectedConditions.elementToBeClickable(updateResumeBtn)).click();

            // Step 8: Upload new resume
            Reporter.log("STEP 10: Uploading new resume", true);
            String filePath = System.getProperty("user.dir") +
                    "\\src\\test\\java\\testData\\Resume\\Bharath Kumar Putta Resume.pdf";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
            fileUploadInput.sendKeys(filePath);

            Thread.sleep(2000); // Wait for upload to complete (consider dynamic wait based on upload icon/status)

            Reporter.log("✔ Resume uploaded successfully", true);
        } catch (Exception e) {
            Reporter.log("✖ Profile update failed: " + e.getMessage(), true);
            e.printStackTrace();
            captureScreenshot("profileUpdateError");
        }
    }

    private void hideChatWindowIfPresent() {
        try {
            WebElement chatWindow = driver.findElement(By.xpath("//div[contains(@id,'chatList')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", chatWindow);
            Reporter.log("✔ Chat window found and hidden", true);
        } catch (Exception e) {
            Reporter.log("✔ No chat window found to hide", true);
        }
    }

    private void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Reporter.log("✔ Scrolled to the element successfully", true);
        } catch (Exception e) {
            Reporter.log("✖ Scroll failed for the element", true);
        }
    }

    private void waitForLoadersToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));
            Reporter.log("✔ Loader disappeared, proceeding", true);
        } catch (Exception e) {
            Reporter.log("✔ No loader found or already disappeared", true);
        }
    }

    private void captureScreenshot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName + ".png");
            FileUtils.copyFile(screenshot, destFile);
            Reporter.log("📸 Screenshot captured: " + destFile.getAbsolutePath(), true);
        } catch (IOException e) {
            Reporter.log("✖ Failed to capture screenshot: " + e.getMessage(), true);
        }
    }
}
