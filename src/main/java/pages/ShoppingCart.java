package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCart {
	private WebDriver driver;
	private WebDriverWait wait;

	// **Locators**
	private By cartIcon = By.id("ctl00_lblTotalCartItems");
	private By deleteProduct = By.id("ctl00_phBody_BookCart_lvCart_ctrl1_imgDelete");
	private By increaseQuantity = By.id("ctl00_phBody_BookCart_lvCart_ctrl1_btnPlus");
	private By checkoutButton = By.id("ctl00_phBody_BookCart_lvCart_imgPayment");
	private By checkoutPageTitle = By.tagName("h1");

	private By addToCartButton1 = By.xpath("//*[@id=\"listSearchResult\"]/div[1]/div[4]/div[4]/input[1]");
	private By addToCartButton2 = By.xpath("//*[@id=\"listSearchResult\"]/div[2]/div[4]/div[5]/input[1]");

	// **Constructor**
	public ShoppingCart(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	// **Method: Open the Cart Page**
	public void openCart() {
		WebElement cartIconvar=wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cartIconvar);
	}

	// **Method: Add Items to Cart**
	public void addItemToCart() {
		try {
			WebElement cartButton1 = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton1));
			//cartButton1.click();
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", cartButton1);

			WebElement cartButton2 = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton2));
			//cartButton2.click();
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", cartButton2);
			
			
		} catch (StaleElementReferenceException e) {
			System.out.println("Retrying add to cart due to StaleElementReferenceException...");
			addItemToCart(); 
		}
	}

	// **Method: Remove Product from Cart**
	public void removeProduct() {
		WebElement deleteElement = wait.until(ExpectedConditions.elementToBeClickable(deleteProduct));
		deleteElement.click();
		wait.until(ExpectedConditions.stalenessOf(deleteElement));
	}

	// **Method: Increase Product Quantity**
	public void increaseQuantity() {
		wait.until(ExpectedConditions.elementToBeClickable(increaseQuantity)).click();
	}

	// **Method: Proceed to Checkout**
	public void proceedToCheckout() {
		
		WebElement checkout=wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkout);
	}

	// **Method: Get Checkout Page Title**
	public String getCheckoutPageTitle() {
		return driver.findElement(checkoutPageTitle).getText();
	}
}
