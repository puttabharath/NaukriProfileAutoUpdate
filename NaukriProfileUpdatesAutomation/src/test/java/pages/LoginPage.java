package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LoginPage {
    public WebDriver driver;
    public WebDriverWait wait;

    // Locators
    @FindBy(xpath = "//input[@id='usernameField']")
    private WebElement usernameField;

    @FindBy(id = "passwordField")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//div[@class='nI-gNb-drawer__icon']/div[1]")
	 private WebElement threeDots;
	
	@FindBy(xpath = "//a[text()='View & Update Profile']")
	private WebElement viewUpdateProfile;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Method to handle login
    public void naukriLoginPortal(String username, String password) throws InterruptedException {
    
        // Wait until username field is visible
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);

        // Click login button
        
       wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
       
       WebElement moreOptions = wait.until(ExpectedConditions.elementToBeClickable(threeDots)); 
		moreOptions.click();
WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View & Update Profile']")));
element.click();
        
    }
}
