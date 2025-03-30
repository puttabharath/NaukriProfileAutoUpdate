package test;

import org.testng.annotations.Test;

import listeners.RetryAnalyzer;
import pages.BaseClass;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseClass{
	
	@Test (description = "Verify the functionality by giving invalid userName and Valid Password", enabled = false)
	public void invalidUsernameVPassword() throws InterruptedException {
	LoginPage lp1 = new LoginPage(driver);
	  String inValidusername = ConfigReader.getProperty("invalidUsername");
      String password = ConfigReader.getProperty("password");
    lp1.naukriLoginPortal(inValidusername,password);
	}
	
	@Test (description = "Verify the functionality by giving valid UserName and invalid password", enabled = false)
	public void userNameInvalidPassword() throws InterruptedException{
		LoginPage lp2 = new LoginPage(driver);
		  String username = ConfigReader.getProperty("username");
	      String invalidpassword = ConfigReader.getProperty("invalidPassword");
	    lp2.naukriLoginPortal(username,invalidpassword);
	}
	
    @Test (priority = 1, retryAnalyzer = RetryAnalyzer.class, description = "Verify the functionality by giving valid username and password")
    public void validLogin() throws InterruptedException {

        // Create LoginPage object
        LoginPage lp = new LoginPage(driver);

        // Fetch credentials from properties file
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        // Perform login
        lp.naukriLoginPortal(username, password);
    }   
}
