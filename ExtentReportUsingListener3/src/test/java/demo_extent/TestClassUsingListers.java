package demo_extent;

import static org.testng.Assert.assertTrue;
import base.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClassUsingListers 
{
	public WebDriver driver;
	
	@BeforeClass
	public void beforeClass() 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void afterClass()
	{
		driver.quit();
	}
	
	@Test(description="To verify that user is able to get the homepage of UPEDIA portal.")
	public void SGL_UPEDIA_1()
	{
		TestListeners.extentTest.get().log(Status.INFO, "Step 1: Open the browser.");
		TestListeners.extentTest.get().log(Status.INFO, "Step 2: Enter the URL of the UPEDIA portal.");
		driver.get("https://www.youtube.com/");
		assertTrue(true);
		TestListeners.extentTest.get().log(Status.PASS, "User is able to get the homepage of UPEDIA portal.");
	}
	
	@Test(description="To verify that user is able to get the detail as per UPEDIA SRS document.")
	public void SGL_UPEDIA_2()
	{	
		TestListeners.extentTest.get().log(Status.INFO, "Step 1: Open the browser.");
		TestListeners.extentTest.get().log(Status.INFO, "Step 2: Enter the URL of the UPEDIA portal.");
		driver.get("https://www.youtube.com/");
		TestListeners.extentTest.get().log(Status.FAIL, "User is not able to get the detail as per UPEDIA SRS document.");
		assertTrue(false);
	}

	
}
