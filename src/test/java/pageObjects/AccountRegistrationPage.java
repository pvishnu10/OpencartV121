package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	@FindBy (xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy (xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy (xpath="//input[@id='input-email']") WebElement txtemail;
	@FindBy (xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy (xpath="//input[@id='input-password']") WebElement txtPass;
	@FindBy (xpath="//input[@id='input-confirm']") WebElement txtConfirmPass;
	@FindBy (xpath="//input[@name='agree']") WebElement chkPolicy;
	@FindBy (xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;


	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void settelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtPass.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPass.sendKeys(pwd);
	}
	
	public void clickPolicy()
	{
		chkPolicy.click();
	}
	
	public void clickSubmitBtn()
	{
		btnContinue.click();
	}
	
	public String getConfirmationMsg()
	{
		try
		{
			return (msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	

}
