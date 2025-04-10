package pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class ResumeHeadlineModule {
	
	WebDriver driver;
	public ResumeHeadlineModule(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "(//div[@class='widgetHead']/span[text()='editOneTheme'])[1]")
	private WebElement resumeHeaderEditIcon;
	
	
	@FindBy(id = "resumeHeadlineTxt")
	private WebElement resumeHeaderinputfield;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;
	
	public void resumeHeadline() throws InterruptedException, Throwable
	{
      JavascriptExecutor jse = (JavascriptExecutor) driver;
    	
    	jse.executeScript("window.scrollBy(300,400)");
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		Reporter.log("Resume Headline module is initiated",true);
		
		wait.until(ExpectedConditions.elementToBeClickable(resumeHeaderEditIcon)).click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_C);
       
        resumeHeaderinputfield.sendKeys(Keys.chord(Keys.CONTROL, "v")); // Paste action
	     saveButton.click();
	     Reporter.log("Resume headline module is validated successfully",true);
	}

}
