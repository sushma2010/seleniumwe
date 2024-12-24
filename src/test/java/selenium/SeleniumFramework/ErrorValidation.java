package selenium.SeleniumFramework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.SeleniumFramework.TestComponent.BaseTest;
import selenium.SeleniumFramework.TestComponent.Retry;
import selenium.SeleniumFramework.pageobject.CartPage;
import selenium.SeleniumFramework.pageobject.ConformationPage;
import selenium.SeleniumFramework.pageobject.ProductCatalog;
import selenium.SeleniumFramework.pageobject.checkOutPage;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class ErrorValidation extends BaseTest{	
	
	@Test(dataProvider="getdata", retryAnalyzer=Retry.class)
	public void loginerror(String email, String password) throws IOException, InterruptedException  {
		landingPage.loginApplication(email,password );
		landingPage.geterrormessage();
		Assert.assertEquals("Incorrect email or password.",landingPage.geterrormessage());
	}
	
	public void producterror() throws IOException, InterruptedException  {
		
		ProductCatalog productCatalog= landingPage.loginApplication("suhani@gmail.com", "Suhani@2010");
		
		String productName="ZARA COAT 3";
		String CountryName="India";
		
		productCatalog.addProductToCart(productName);
		CartPage CartPage= productCatalog.GoToCartPage();
		
		Thread.sleep(2000);
		Boolean match= CartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertTrue(match);
		//test
	
	}
	
	@DataProvider
	public Object[][] getdata() {
		return new Object[][] { {"suhani@gmail.com","Suhani@20010"}};
	}

}
