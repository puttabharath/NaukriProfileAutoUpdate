package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			Reporter.log("Profile update module is initiated", true);

			// Click on the three dots menu
			wait.until(ExpectedConditions.elementToBeClickable(threeDots)).click();

			// Close chat window if it appears
			hideChatWindowIfPresent();

			// Click 'View & Update Profile'
			wait.until(ExpectedConditions.elementToBeClickable(viewUpdateProfile)).click();

			// Delete existing resume
			wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
			wait.until(ExpectedConditions.elementToBeClickable(deletePopupBtn)).click();
			Reporter.log("Resume got deleted", true);

			// Upload new resume
			wait.until(ExpectedConditions.elementToBeClickable(updateResumeBtn)).click();

			String filePath = System.getProperty("user.dir") +
					"\\src\\test\\java\\testData\\Resume\\Bharath Kumar Putta Resume.pdf";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
			fileUploadInput.sendKeys(filePath);

			// Wait for the file to upload (can be improved with visual confirmation if available)
			Thread.sleep(2000);

			Reporter.log("Updated latest Resume", true);
		} catch (Exception e) {
			Reporter.log("Profile update failed: " + e.getMessage(), true);
			e.printStackTrace();
		}
	}

	private void hideChatWindowIfPresent() {
		try {
			WebElement chatWindow = driver.findElement(By.xpath("//div[contains(@id,'chatList')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", chatWindow);
		} catch (Exception e) {
			System.out.println("No chat window found");
		}
	}
}
