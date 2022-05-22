package base;
import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager 
{
	private static ExtentReports extent;
	
	public static ExtentReports createInstance()
	{
		 extent = new ExtentReports();
		 String fileName = getReportName();
		 String directory = System.getProperty("user.dir")+"/reports/";
		 new File(directory).mkdirs();
		 String path = directory + fileName;
		 ExtentSparkReporter spark = new ExtentSparkReporter(path);
		 spark.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		 extent.attachReporter(spark);   
		 return extent;
	}
	
	public static String getReportName() 
	{
		Date d = new Date();
		String fileName =  "AutomationReport_" + d.toString().replace(":", "_") + ".html";
		return fileName;
	}
}
