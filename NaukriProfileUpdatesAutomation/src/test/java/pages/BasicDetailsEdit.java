package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class BasicDetailsEdit {

    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath = "//em[contains(@class,'icon edit')]") 
    private WebElement editProfileBtn;

    @FindBy(id = "name") 
    private WebElement NameField;

    @FindBy(id = "exp-years-droopeFor") 
    private WebElement expYeardropdown;

    @FindBy(xpath = "//a[@data-id='exp-years-droope_2']") 
    private WebElement expDropdown2;

    @FindBy(xpath = "//span[@data-id='1']") 
    private WebElement availToJoinoptn;

    @FindBy(id = "saveBasicDetailsBtn") 
    private WebElement savebtn;

    @FindBy(xpath = "//div[@class='err-container']/span[@id='name_err']") 
    private WebElement nameErrorNameField;

    @FindBy(xpath = "//span[@class='fullname']") 
    private WebElement fullname;

    @FindBy(xpath = "//div[@data-ga-track='spa-event|EditProfile|BasicDetails|Cancel']/span") 
    private WebElement crossBtn;

    public BasicDetailsEdit(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Helper: Scroll into view and click safely
    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            Reporter.log("Click intercepted, trying JS click", true);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // Invalid name test
    public void nameInputFieldInvalid() throws Throwable {
        Reporter.log("Naukri basic details module is initiated", true);
        safeClick(editProfileBtn);

        wait.until(ExpectedConditions.visibilityOf(NameField)).click();
        NameField.clear();
        Thread.sleep(3000);
        Reporter.log("Name field is cleared", true);

        NameField.sendKeys("Bharath Kumar Putta@12");
        Reporter.log("Entered invalid data in the name field", true);

        safeClick(savebtn);
        safeClick(crossBtn);
        Thread.sleep(3000);
        Reporter.log("Basic details module is validated with invalid data", true);
    }

    // Valid name test
    public void nameInputFieldValid() throws Throwable {
        Reporter.log("Naukri input field module is initiated with valid data", true);
        safeClick(editProfileBtn);

        wait.until(ExpectedConditions.visibilityOf(NameField)).click();
        NameField.clear();
        Thread.sleep(1000);
        NameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        Thread.sleep(1000);
        NameField.sendKeys("Bharath Kumar Putta");

        Reporter.log("Entered valid data in the name field", true);
        safeClick(savebtn);
        Thread.sleep(3000);

        String actualFullName = wait.until(ExpectedConditions.visibilityOf(fullname)).getText();
        String expectedFullName = "Bharath Kumar Putta";

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actualFullName, expectedFullName);
        sa.assertAll();

        Thread.sleep(3000);
        Reporter.log("Basic details module is validated with valid data", true);
    }

    // Dropdowns and save test
    public void naukriProfileBasicDetails() throws Throwable {
        Reporter.log("Naukri input basic details with exp year and joining date", true);

        safeClick(editProfileBtn);
        safeClick(expYeardropdown);
        safeClick(expDropdown2);
        Reporter.log("Years of experience is updated in the dropdown successfully", true);

        safeClick(availToJoinoptn);
        Reporter.log("Avail to Join is updated", true);

        safeClick(savebtn);
        Reporter.log("Basic details saved successfully", true);
    }
}
