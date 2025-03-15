package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileUpdation {
	WebDriver driver;
	WebDriverWait wait;

	public ProfileUpdation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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

	@FindBy(xpath = "//input[@type='file']") // Ensure this matches the actual file input field
	private WebElement fileUploadInput;

	public void profileUpdateModule() throws Throwable {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement moreOptions = wait.until(ExpectedConditions.elementToBeClickable(threeDots));
		moreOptions.click();

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View & Update Profile']")));
		element.click();

		deleteIcon.click();
		deletePopupBtn.click();

		WebElement updateResume = wait.until(ExpectedConditions.elementToBeClickable(updateResumeBtn));
		updateResume.click();

		// Upload file using sendKeys
		String filePath = "C:\\Users\\bharath\\git\\NaukriProfileAutoUpdate\\NaukriProfileUpdatesAutomation\\src\\test\\java\\testData\\Resume\\Bharath Kumar Putta Resume.pdf";
		fileUploadInput.sendKeys(filePath);

		// Wait for upload to complete
		Thread.sleep(2000);
	}
}
