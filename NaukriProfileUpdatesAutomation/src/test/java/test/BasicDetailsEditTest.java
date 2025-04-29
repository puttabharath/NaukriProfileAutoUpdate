package test;

import org.testng.annotations.Test;
import listeners.RetryAnalyzer;
import pages.BaseClass;
import pages.BasicDetailsEdit;
import pages.LoginPage;
import utils.ConfigReader;

public class BasicDetailsEditTest extends BaseClass {
	
	@Test(priority = 5,description = "Verify the functionality by giving invalid (Bharath@12) in the username input field")
	public void naukriBasicDetailsInvalid() throws Throwable
	{
		 LoginPage loginPage = new LoginPage(driver);

	        // Fetch credentials from properties file
	        String username = ConfigReader.getProperty("username");
	        String password = ConfigReader.getProperty("password");

	        // Perform login
	        loginPage.naukriLoginPortal(username, password);
		BasicDetailsEdit basicDetails = new BasicDetailsEdit(driver);
		basicDetails.nameInputFieldInvalid();
	}
	
	@Test(priority = 6,description = "Verify the functionality by giving valid data in the username Input field")
	public void naukriBasicDetailsValid() throws Throwable
	{
		BasicDetailsEdit basicDetails = new BasicDetailsEdit(driver);
		basicDetails.nameInputFieldValid();
	}
	
	@Test(priority = 7,description="Verify the functionality by removing and adding the basic details in Naukri profile")
	public void naukriBasicDetailsTest() throws Throwable
	{
		BasicDetailsEdit basicDetails = new BasicDetailsEdit(driver);
		basicDetails.naukriProfileBasicDetails();
	}

}
