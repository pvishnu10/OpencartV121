package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;          //Log4j
import org.apache.logging.log4j.Logger;              //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	
	
public static WebDriver driver;
public Logger logger;        //Log4j2
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master", "DataDriven"})
	@Parameters({"os", "browser"})       //cross browser testing using @Parameter tag (we have added os and browser in the testng.xml file)
	public void setup( String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());    //created logger object for logging purpose-(to generate logs/Log4j2)
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))   //grid setup
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os (operating system)                     //we are taking operating system from master.xml file
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;

			default: System.out.println("No matching browser"); return;

			}
			
			driver = new RemoteWebDriver(new URL ("http://192.168.0.107:4444/wd/hub"), capabilities);  // passed grid URL
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())                    //Used switch statement to switch between browsers (for cross browser testing)
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
	        default : System.out.println("Invalid browser name.."); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));      //reading url from config.properties file
		driver.manage().window().maximize();

	}
	
	@AfterClass(groups= {"Sanity","Regression","Master", "DataDriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
	//created java built-in methods to pass random data in first name, last name, email. password, confirm pass etc.
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;

	}
	
	public String randomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;

	}
	
	public String randomAlphaNumeric()
	{
		String generatedString=RandomStringUtils.randomAlphanumeric(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+ "@"+ generatedNumber);

	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
