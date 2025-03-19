package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomePageSteps {
	WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
	
    @Before("@SearchTest")
    public void setUp() {
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        homePage = new HomePage(driver);
    }
	@Given("User is on the Bookswagon homepage")
	public void user_is_on_the_bookswagon_homepage() {
		// Write code here that turns the phrase above into concrete actions
        driver.get("https://www.bookswagon.com/");
	}
	@When("User searches for a book {string} in the search bar")
	public void user_searches_for_a_book_in_the_search_bar(String bookName) {
		// Write code here that turns the phrase above into concrete actions
		homePage.searchForBook(bookName);
	}
	@Then("Search results are displayed")
	public void search_results_are_displayed() {
		String actualTitle=homePage.getPageTitle();
		String expectedTitle = "harry potter - Books - 24x7 online bookstore Bookswagon.com";
		Assert.assertEquals(actualTitle,expectedTitle);
		// Checking whether the element displays or not...
		boolean isBookPresent = driver.findElements(By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[2]/div[1]/div[1]/h1/span")).size() > 0;
	    Assert.assertTrue(isBookPresent, "The book 'Harry Potter' should be present in the search results.");
		// Write code here that turns the phrase above into concrete actions		
	}
	@After("@SearchTest")
    public void tearDown() {
        driver.quit();
    }
}