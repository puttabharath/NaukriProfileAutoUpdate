package pages;

import java.time.Duration;

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

    @FindBy(xpath = "//em[contains(@class,'icon edit')]") private WebElement editProfileBtn;
    @FindBy(id = "name") private WebElement NameField;
    @FindBy(id="exp-years-droopeFor") private WebElement expYeardropdown;
    @FindBy(xpath = "//a[@data-id='exp-years-droope_2']") private WebElement expDropdown2;
    @FindBy(xpath = "//span[@data-id='1']") private WebElement availToJoinoptn;
    @FindBy(id = "saveBasicDetailsBtn") private WebElement savebtn;
    @FindBy(id = "name_err") private WebElement nameErrorNameField;
    @FindBy(xpath = "//span[@class='fullname']") private WebElement fullname;
    @FindBy(xpath = "//div[@data-ga-track='spa-event|EditProfile|BasicDetails|Cancel']/span") private WebElement crossBtn;

    public BasicDetailsEdit(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Verify invalid name input (with special characters)
    public void nameInputFieldInvalid() throws Throwable {
        Reporter.log("Naukri basic details module is initiated", true);
        wait.until(ExpectedConditions.elementToBeClickable(editProfileBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(NameField)).click();
        wait.until(ExpectedConditions.visibilityOf(NameField)).clear();
        Thread.sleep(3000);
        Reporter.log("name field is cleared", true);
        wait.until(ExpectedConditions.visibilityOf(NameField)).sendKeys("Bharath Kumar Putta@12");
        Reporter.log("Entered invalid data in the name field", true);
        wait.until(ExpectedConditions.elementToBeClickable(savebtn)).click();
        Thread.sleep(4000); // wait for error
        String errorMessageUserNameField = wait.until(ExpectedConditions.visibilityOf(nameErrorNameField)).getText();
        String expectedErrorMessage = "Special characters other than Space( ) SingleQuote(') Dot(.) are not allowed.";
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(errorMessageUserNameField, expectedErrorMessage);
        sa.assertAll();
        wait.until(ExpectedConditions.elementToBeClickable(crossBtn)).click();
        Thread.sleep(3000);
        Reporter.log("Basic details module is validated with invalid data", true);
    }

    // Verify valid name input
    public void nameInputFieldValid() throws Throwable {
        Reporter.log("Naukri input field module is initiated with valid data", true);
        wait.until(ExpectedConditions.elementToBeClickable(editProfileBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(NameField)).click();
        NameField.clear();
        Thread.sleep(1000);
        NameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE); // Ensure field is fully cleared
        Thread.sleep(1000);
        NameField.sendKeys("Bharath Kumar Putta");
        Reporter.log("Enter valid data in the name field", true);
        wait.until(ExpectedConditions.elementToBeClickable(savebtn)).click();
        Thread.sleep(3000);
        String actualFullName = wait.until(ExpectedConditions.visibilityOf(fullname)).getText();
        String expectedFullName = "Bharath Kumar Putta";
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actualFullName, expectedFullName);
        sa.assertAll();
        Thread.sleep(3000);
        Reporter.log("Basic details module is validated with valid data", true);
    }

    // Verify dropdown selections for experience and joining date
    public void naukriProfileBasicDetails() throws Throwable {
        Reporter.log("Naukri input basic details with exp year and joining date", true);
        wait.until(ExpectedConditions.elementToBeClickable(editProfileBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(expYeardropdown)).click();
        wait.until(ExpectedConditions.visibilityOf(expDropdown2)).click();
        Reporter.log("Years of experience is updated in the dropdown successfully", true);
        wait.until(ExpectedConditions.elementToBeClickable(availToJoinoptn)).click();
        Reporter.log("Avail to Join is updated", true);
        wait.until(ExpectedConditions.elementToBeClickable(savebtn)).click();
    }
}
