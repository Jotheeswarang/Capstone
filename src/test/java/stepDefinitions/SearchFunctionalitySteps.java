package stepDefinitions;

import java.time.Duration;
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
import pages.SearchFunctionality;

public class SearchFunctionalitySteps {
	WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SearchFunctionality searchFunctionality;
    
	@Before("@SearchFunctionalityTest")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        searchFunctionality = new SearchFunctionality(driver);
    }
	@Given("User is on the homepage")
	public void user_is_on_the_bookswagon_homepage() {
		// Write code here that turns the phrase above into concrete actions
		driver.get("https://www.bookswagon.com/");
	}
	@When("User searches for {string} book in the search bar")
	public void user_searches_for_book_in_the_search_bar(String bookName) {
		// Write code here that turns the phrase above into concrete actions
		homePage.searchForBook(bookName);
		
	}
	@When("User filters the book by discount 21% - 30% in the refine search section")
	public void user_filters_the_book_by_discount_in_the_refine_search_section() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		searchFunctionality.filterByDiscount();
		Thread.sleep(5000);
	}
	@When("User filters the book by price Rs.500 - Rs.1000 in the refine search section")
	public void user_filters_the_book_by_price_in_the_refine_search_section() throws InterruptedException {
		searchFunctionality.filterByPrice();		
		Thread.sleep(5000);
	}
	@When("User filters the book by language English in the refine search section")
	public void user_filters_the_book_by_language_in_the_refine_search_section() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		searchFunctionality.filterByLanguage();
		Thread.sleep(5000);
	}
	@Then("Searched filters are displayed")
	public void search_results_are_displayed() {
		// Write code here that turns the phrase above into concrete actions
		boolean isBookPresent = searchFunctionality.areSearchResultsDisplayed();
	    Assert.assertTrue(isBookPresent, "The book 'Harry Potter' should be present in the search results.");
	}
	@After("@SearchFunctionalityTest")
    public void tearDown() {
		// Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}