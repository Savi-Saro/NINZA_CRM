package genericUtilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener, ISuiteListener
{

	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReports/report.html");
		spark.config().setDocumentTitle("CRM Reports");
		spark.config().setReportName("NINZA CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report BackUp");	
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String TestCaseName = result.getMethod().getMethodName();
		test=report.createTest(TestCaseName);		
		test.log(Status.INFO,TestCaseName+" Execution Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String TestCaseName = result.getMethod().getMethodName();
		test=report.createTest(TestCaseName);		
		test.log(Status.PASS,TestCaseName+" Execution Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String TestCaseName = result.getMethod().getMethodName();
		test=report.createTest(TestCaseName);		
		test.log(Status.FAIL,TestCaseName+" Execution Failed");
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ErrorShots/"+TestCaseName+".png");
		try {
			FileHandler.copy(src,dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String TestCaseName = result.getMethod().getMethodName();
		test=report.createTest(TestCaseName);		
		test.log(Status.SKIP,TestCaseName+" Execution Skipped");
	}

}
