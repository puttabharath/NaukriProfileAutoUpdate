package test;

import org.testng.annotations.Test;

import listeners.RetryAnalyzer;
import pages.BaseClass;
import pages.LoginPage;
import pages.skillsUpdate;
import utils.ConfigReader;

public class skillUpdateTest extends BaseClass{
	
	@Test(priority = 3,description = "Verify the functionality by removing and adding the any skill in the Skills Section", retryAnalyzer = RetryAnalyzer.class)
	public void skillUpdationTest() throws Throwable
	{
		 LoginPage loginPage = new LoginPage(driver);

	        // Fetch credentials from properties file
	        String username = ConfigReader.getProperty("username");
	        String password = ConfigReader.getProperty("password");

	        // Perform login
	        loginPage.naukriLoginPortal(username, password);
		skillsUpdate skill = new skillsUpdate(driver);
				skill.addDeleteSkills();
	}

}
