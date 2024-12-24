package selenium.SeleniumFramework.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	    int count = 0;  
		int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {
		
		
		//when fail test after listner if we want to rerun again or not 
		// which is failed
		//how many times execute
		//@test(retryAnalyzer= Retry.class)
		 if (count<maxTry) {
			 count++;
			 return true;
		 }
		return false;
	}

}
