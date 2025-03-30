package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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
	
	@FindBy(xpath = "//span[text()='Sign in with Google']")
	private WebElement signInWithGoogle;
	
	@FindBy(id = "identifierId")
	private WebElement emailIdInputfield;
	
	@FindBy(xpath = "//span[text()='Next']")
	private WebElement userNameNext;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    
  //Verify the functionality by using invalid Username and valid password

    public void naukriLoginPortal(CharSequence[] invalidUsername, String password)
    {
    	wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(invalidUsername);
    	wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    	wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        SoftAssert loginSA = new SoftAssert();
       String LoginValidationError1 = driver.findElement(By.xpath("//span[contains(text(),'Invalid details')]")).getText();
       loginSA.assertEquals(LoginValidationError1, "Invalid details. Please check the Email ID - Password combination.");
       wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
   	wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
    }
    
    //Verify the functionality by using invalid password and valid username
    public void naukriLoginPortal(String username,CharSequence[] invalidPassword)
    {
    	wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    	wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(invalidPassword);
    	wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        SoftAssert loginSA = new SoftAssert();
       String LoginValidationError = driver.findElement(By.xpath("//span[contains(text(),'Invalid details')]")).getText();
       loginSA.assertEquals(LoginValidationError, "Invalid details. Please check the Email ID - Password combination.");
       wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
   	wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
    }
    
    //Verify the functionality by Sign in with Google
    public void naukriLoginPortal(String username)
    {
    	wait.until(ExpectedConditions.visibilityOf(signInWithGoogle)).click();
    	wait.until(ExpectedConditions.visibilityOf(emailIdInputfield)).sendKeys(username);
    	wait.until(ExpectedConditions.visibilityOf(userNameNext)).click();
    }

// Verify the functionality by using valid username and password
    public void naukriLoginPortal(String username, String password) throws InterruptedException {
    
        // Wait until username field is visible
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);

        // Click login button
        
       wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
       SoftAssert sa = new SoftAssert();
       sa.assertEquals(driver.getTitle(), "Home | Mynaukri");
       
       WebElement moreOptions = wait.until(ExpectedConditions.elementToBeClickable(threeDots)); 
		moreOptions.click();
WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View & Update Profile']")));
element.click();
    }
		
}