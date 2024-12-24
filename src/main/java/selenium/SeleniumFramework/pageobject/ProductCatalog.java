package selenium.SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.SeleniumFramework.AbstractComponents.AbsractComponent;


public class ProductCatalog extends AbsractComponent  {
	
	
	 WebDriver driver;
	public  ProductCatalog (WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(""));	

    @FindBy(css=".mb-3")
    List<WebElement> products;
    
    @FindBy(css=".ng-animating")
    WebElement Spinner;
   
    
    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By ToastMessage= By.cssSelector("#toast-container");
   
    
   public List<WebElement> getProductList() throws InterruptedException {
	   waitForElementToAppear(productsBy);
	   
	   return products;
	   
   }
   
   public WebElement getproductName(String productName) throws InterruptedException {
	   
	   WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	   return prod;
   
   }
   
   public void addProductToCart(String productName) throws InterruptedException {
	   WebElement prod= getproductName(productName);
	   
		prod.findElement(addToCart).click();
		waitForElementToAppear(ToastMessage);
		Thread.sleep(1000);
		waitForElementToDissappear(Spinner);
		
		
   }
   
    
}
