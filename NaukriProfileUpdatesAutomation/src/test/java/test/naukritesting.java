package test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;

public class naukritesting {
	
	    @FindBy(xpath = "//span[text()='Key skills']")
	    private WebElement keySkillsElement;

	    @FindBy(id = "keySkillSugg")
	    private WebElement keySkillsInputfld;
	    
	    @FindBy(xpath = "//li[@class='sugTouple']")
	    private List<WebElement> suggestedSkills;

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.naukri.com/nlogin/login?err=1");
		WebElement user = driver.findElement(By.id("usernameField"));
		user.clear();
		user.sendKeys("puttabharathkumar123@gmail.com");
		driver.findElement(By.id("passwordField")).sendKeys("Bharath@#6981");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		
	}}