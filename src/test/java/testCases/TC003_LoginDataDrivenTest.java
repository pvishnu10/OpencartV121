package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups="DataDriven")//DataProviders is class name we imported from utilities package
	public void verify_LoginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("**** Starting TC_003_LoginDDT ****");

		try
		{
			//HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on myaccount...");
			hp.clickLogin();                            //login link under my account
			logger.info("Clicked on login...");

			//HomePage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);                         
			lp.setPassword(pwd);          
			lp.clickLogin();                            //Login button          
			Thread.sleep(4000);
			logger.info("Entered email and password and clicked on login button...");

			//MyAccountPage
			MyAccountPage mp = new MyAccountPage(driver);
			boolean targetPage = mp.isMyAccountExists();

			/*Data is valid  - login success - test pass  - logout
		      Data is valid -- login failed - test fail

		      Data is invalid - login success - test fail  - logout
		      Data is invalid -- login failed - test pass
			 */
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					mp.clicklogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);	
				}
			}

			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					mp.clicklogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		}

		logger.info("**** Finished TC_003_LoginDDT *****");




	}
}
