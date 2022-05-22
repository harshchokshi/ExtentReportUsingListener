package demo_extent;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class SearchTestCase 
{
  ExtentReports extent = new ExtentReports();
  ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
  WebDriver driver;
	
  @Test(description="Test Description: To verify that user is able to get Google search engine page.")
  public void SGL_Search_1() 
  {
	  ExtentTest test = extent.createTest("SGL_Search_1", "\"Test Description: To verify that user is able to get Google search engine page.\"" );
	  test.info("Step 1: Capture the page title.");
	  String pageTitle = driver.getTitle();
	  test.info("Step 2: Page Title captured as: "+pageTitle);
	  if(pageTitle.equals("google"))
	  {
		  test.pass("User should able to get Google search engine page " +pageTitle);
		  Assert.assertEquals("google", pageTitle);
	  }else 
	  {
		test.fail("Page title is not matched with expected result : "+"google");
		Assert.assertEquals("google", pageTitle);
	  }
	  
  }
  @BeforeTest
  public void browserlaunch() 
  {
	  spark.config().setTheme(Theme.DARK);
	  spark.config().setDocumentTitle("MyReport");
	  extent.attachReporter(spark);       
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.get("https://www.google.com/");
  }

  @AfterTest
  public void tearDown() 
  {
	  extent.flush();
	  driver.quit();
  }

}
