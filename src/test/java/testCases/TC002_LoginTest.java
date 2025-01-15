package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
		
	@Test(groups={"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("****Starting TC_002_LoginTest****");
		
		try
		{
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on myaccount...");
		hp.clickLogin();
		logger.info("Clicked on login...");

		
		//HomePage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));          //taking data from config.properties file
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		Thread.sleep(4000);
		logger.info("Entered email and password and clicked on login button...");

		
		//MyAccountPage
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetPage = mp.isMyAccountExists();
		Assert.assertEquals(targetPage, true, "Login failed");
		//Assert.assertTrue(targetPage);

		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****Finished TC_002_LoginTest****");

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
