package selenium.SeleniumFramework.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import selenium.SeleniumFramework.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	//ItestLister interface provided by test ng
	// make extententreport as static so you can acess without creating 
	//object
	ExtentTest test;
	ExtentReports extent= ExtentReporterNG.getReoprtObject();
	
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();//Thread Safe
	//parallel ="tests"
	@Override
	 public void onTestStart(ITestResult result) {
		//result this variable hold the info about test that is going to get executed
		//test
		 //first line to execute   
		//this hold the entry to your report
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id will be created of each test and store it as map
	}
	
	@Override
	 public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS,  "test pass");
		   }
	
	@Override
	 public void onTestFailure(ITestResult result) {
		    // not implemented
		 extentTest.get().fail(result.getThrowable());//it shows logs were it failed msg
		 // tis will say which thread id should be used
		 try {
			driver= (WebDriver)result.getTestClass().getRealClass().getField("driver")
					 .get(result.getInstance());
			// this give info about driver as screen shot method  do not have knowledge 
			//about driver we agiving it by listners. listner will get from above lines
			//fields are associated with class level so we not using method-> .getmethodname
			
		} catch( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 String FilePath = null;
		try {
			FilePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();// if no sceeen shot tell no path present
		}
		extentTest.get().addScreenCaptureFromPath(FilePath,result.getMethod().getMethodName());
		 //below line for without sny above for paralle execution
		// test.addScreenCaptureFromPath(FilePath,result.getMethod().getMethodName());
		  }
	 //<listeners>
	//	<listener class-name="selenium.SeleniumFramework.TestComponent.Listeners"></listener>
	//</listeners>
	 //add this in testng xml  by this it will get knowledge on listners
	
	@Override
	public void onFinish(ITestContext context) {
		    //at last
		//if you  not write bellow linkreport generation will not seen on the screen
		extent.flush();
		  }
	 
	 
	 
	 
	 

}
