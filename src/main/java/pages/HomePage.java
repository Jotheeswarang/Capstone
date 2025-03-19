package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;

	// Locators
	private By searchBar = By.id("inputbar");
	private By searchButton = By.id("btnTopSearch");

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Method to enter a book name in the search bar
	public void searchForBook(String bookName) {
		driver.findElement(searchBar).sendKeys(bookName);
		driver.findElement(searchButton).click();
	}

	// Method to get the page title
	public String getPageTitle() {
		return driver.getTitle();
	}

}

