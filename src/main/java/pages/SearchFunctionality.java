package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFunctionality {
	private WebDriver driver;
	private WebDriverWait wait;
	//Locators
	private By discountFilter = By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[3]/li[4]/a");
	private By priceFilter = By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[2]/li[3]/a");
	private By languageFilter = By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[8]/li[2]/a");
	private By searchResultHeader = By.xpath("//*[@id='site-wrapper']/div[1]/div[2]/div[1]/div[1]/h1/span");

	// Constructor
	public SearchFunctionality(WebDriver driver) {
		this.driver = driver;
		//this.wait = wait;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Method to filter by discount
	public void filterByDiscount() {
				wait.until(ExpectedConditions.presenceOfElementLocated(discountFilter));
				WebElement discountElement = wait.until(ExpectedConditions.elementToBeClickable(discountFilter));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", discountElement);
				wait.until(ExpectedConditions.stalenessOf(discountElement));
	}

	// Method to filter by price with stale element handling
	public void filterByPrice(){
			wait.until(ExpectedConditions.presenceOfElementLocated(priceFilter));
			WebElement priceElement = wait.until(ExpectedConditions.elementToBeClickable(priceFilter));
			priceElement.click();
			wait.until(ExpectedConditions.stalenessOf(priceElement)); // Ensure the page reloads
	}

	// Method to filter by language with stale element handling
	public void filterByLanguage(){
		
		wait.until(ExpectedConditions.presenceOfElementLocated(languageFilter));
		WebElement languageElement = wait.until(ExpectedConditions.elementToBeClickable(languageFilter));
		languageElement.click();
		wait.until(ExpectedConditions.stalenessOf(languageElement)); // Ensure the page reloads
	}
	// Method to check if the search results are displayed
	public boolean areSearchResultsDisplayed() {
		return driver.findElements(searchResultHeader).size() > 0;
	}
}