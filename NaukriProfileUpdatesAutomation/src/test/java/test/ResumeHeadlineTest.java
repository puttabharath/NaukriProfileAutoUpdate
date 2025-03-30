package test;

import org.testng.annotations.Test;

import listeners.RetryAnalyzer;
import pages.BaseClass;
import pages.LoginPage;
import pages.ResumeHeadlineModule;
import utils.ConfigReader;

public class ResumeHeadlineTest extends BaseClass {
	@Test(priority = 4,description = "Verify the functionality by removing and adding the resume headline text", retryAnalyzer = RetryAnalyzer.class)
	public void resumeHeadlineTest() throws Throwable
	{
		 LoginPage loginPage = new LoginPage(driver);

	        // Fetch credentials from properties file
	        String username = ConfigReader.getProperty("username");
	        String password = ConfigReader.getProperty("password");

	        // Perform login
	        loginPage.naukriLoginPortal(username, password);
		ResumeHeadlineModule rhm= new ResumeHeadlineModule(driver);
		rhm.resumeHeadline();
	}

}
