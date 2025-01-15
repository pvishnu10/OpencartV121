package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		try
		{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");  //for logging purpose
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("clicked on ClickRegister Link");

		
		AccountRegistrationPage ap = new AccountRegistrationPage(driver);
		logger.info("Providing customer details...");                          //for logging purpose
		ap.setFirstName(randomString().toUpperCase());
		ap.setLastName(randomString().toUpperCase());
		ap.setEmail(randomString()+"@gmail.com");
		ap.settelephone(randomNumber());
		
		String password = randomAlphaNumeric(); //stored alphanumeric data in password variable and passed  that value in password field because we need same pass in both field
		ap.setPassword(password);
		ap.setConfirmPassword(password);
		Thread.sleep(2000);
		ap.clickPolicy();
		ap.clickSubmitBtn();
		Thread.sleep(2000);
		
		logger.info("Validating expected message...");
		String confrmMsg = ap.getConfirmationMsg();
		System.out.println(confrmMsg);
		if(confrmMsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed...");
			logger.debug("Debug logs...");
			Assert.assertFalse(false);
		}
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");  //for logging purpose

	}
	
	
	
	
	
	
	
	

}
