package selenium.SeleniumFramework.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.SeleniumFramework.TestComponent.BaseTest;
import selenium.SeleniumFramework.pageobject.CartPage;
import selenium.SeleniumFramework.pageobject.ConformationPage;
import selenium.SeleniumFramework.pageobject.LandingPage;
import selenium.SeleniumFramework.pageobject.ProductCatalog;
import selenium.SeleniumFramework.pageobject.checkOutPage;

public class StepDefinationsImp extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage CartPage;
	String CountryName="India";
	ConformationPage ConformationPage;
	
	
	@Given("I landed on EcommercePage")// as so many given are there we use message to identifi
	public void I_landed_on_EcommercePage() throws IOException, InterruptedException {
		landingPage=launchApplication();
		System.out.println(landingPage);
	}	
	
	
	@Given("^Logged in with username (.+) and password is (.+)$")
	public void logged_in_username_and_password(String username, String password) throws InterruptedException {
		 productCatalog= landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+)  to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {;
	
	
	productCatalog.addProductToCart(productName);
	}
	//when or And can be used
	@And ("^Checkout (.+) and submit the order$")
	public void checkout_submit(String productName) throws InterruptedException {
		CartPage= productCatalog.GoToCartPage();
		Thread.sleep(2000);
		Boolean match= CartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CartPage.zoomOutBrowser();
		checkOutPage checkOutPage= CartPage.GoToCheckout();
		checkOutPage.Selectcountry(CountryName);
		 ConformationPage=checkOutPage.SubmitOrder();
	}
	
	@Then ("{string} message is displayed on conformationPage")
	
public void message_displayed_conformationPage(String string) {
		String conformmessage=ConformationPage.getconfmessage();
		Assert.assertTrue(conformmessage.equalsIgnoreCase(string));
	}	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String str1) {
		Assert.assertEquals("Incorrect email or password.",landingPage.geterrormessage());
		driver.close();
	}
}
