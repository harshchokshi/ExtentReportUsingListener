package base;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import demo_extent.TestClassUsingListers;

public class TestListeners implements ITestListener
{
	public static ExtentReports extent = ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) 
	{
		ExtentTest test = extent.createTest(result.getMethod().getMethodName(), "<br> <span class=\"description\">Test Description:&nbsp;</span>"+ "<span class=\"descriptionValue\">"+result.getMethod().getDescription()+"</span>");
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) 
	{
		
	}

	public void onTestFailure(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		WebDriver driver = ((TestClassUsingListers)result.getInstance()).driver;
		String path = takeScreenshot(driver, result.getMethod().getMethodName());
		try {
			//extentTest.get().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			extentTest.get().log(Status.INFO, "", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			extentTest.get().log(Status.INFO, Arrays.toString(result.getThrowable().getStackTrace()));
			
		}catch (Exception e) {
			System.out.println("Test failed, cannot attach the screenshot.");
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		extentTest.get().log(Status.SKIP, "Test case is skipped.");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{

	}

	public void onTestFailedWithTimeout(ITestResult result) 
	{
	
	}

	public void onStart(ITestContext context) 
	{

	}
	
	public void onFinish(ITestContext context) 
	{
		if(extent!=null) 
		{
			extent.flush();
		}
	}
	
	
	public String takeScreenshot(WebDriver driver, String methodName)
	{	
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir")+"/screenshots/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		try {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File (path));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenshotName(String methodName)
	{
		Date d = new Date();
		String fileName =  methodName + "_" + d.toString().replace(":", "_") + ".png";
		return fileName;
	}

}
	

