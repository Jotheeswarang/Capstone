package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchFunctionality;

public class ProductDetailsPageSteps {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SearchFunctionality searchFunctionality;
    ProductDetailsPage productDetailsPage;

    @Before("@ProductDetailsPageTest")
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        searchFunctionality = new SearchFunctionality(driver);
        productDetailsPage = new ProductDetailsPage(driver, wait);
    }

    @Given("User Entered the homepage")
    public void user_entered_the_homepage() throws InterruptedException {
        driver.get("https://www.bookswagon.com/");
    }

    @When("User login to the acc with mobile number {string} and password {string}")
    public void user_login_to_the_acc_with_mobile_number_and_password(String number, String password) {
        WebElement MyaccButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_lblUser")));
        MyaccButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_SignIn_txtEmail")));
        emailField.sendKeys(number);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_SignIn_txtPassword")));
        passwordField.sendKeys(password);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_phBody_SignIn_btnLogin")));
        submitButton.click();
      
    }

    @When("User searches for {string} in the search bar")
    public void user_searches_for_in_the_search_bar(String bookName) {
        homePage.searchForBook(bookName);
    }

    @When("User filters")
    public void user_filters() throws InterruptedException {
        searchFunctionality.filterByDiscount();
        Thread.sleep(5000);        
        searchFunctionality.filterByPrice();
        Thread.sleep(5000); 
        searchFunctionality.filterByLanguage();
        Thread.sleep(5000); 
    }

    @When("User validate the product")
    public void user_validate_the_product() throws InterruptedException {
        WebElement bookselect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"listSearchResult\"]/div[1]/div[3]/div[1]/a")));
        bookselect.click();

        // Expected values
        String expectedTitle = "Harry Potter and the Cursed Child, Parts One and Two: The Official Playscript of the Original West End Production";
        String expectedDescription = "The Eighth Story. Nineteen Years Later. Based on an original story by J.K. Rowling, John Tiffany, and Jack Thorne, a play by Jack Thorne. It was always difficult being Harry Potter and it isn't ...read more";
        double minPrice = 500;
        double maxPrice = 1000;

        // **Assertions**
        Assert.assertEquals(productDetailsPage.getProductTitle(), expectedTitle, "Book title mismatch!");
        Assert.assertEquals(productDetailsPage.getProductDescription(), expectedDescription, "Book description mismatch!");
        double bookPriceValue = productDetailsPage.getProductPrice();
        Assert.assertTrue(bookPriceValue >= minPrice && bookPriceValue <= maxPrice, "Price is not within the expected range!");
        Assert.assertTrue(productDetailsPage.isProductImageDisplayed(), "Book image is missing!");
        Assert.assertTrue(productDetailsPage.isProductAvailable(), "Book availability info missing!");
    }

    @When("User adding the product to the wishlist")
    public void user_adding_the_product_to_the_wishlist() {
        productDetailsPage.addToWishlist();
    }

    @When("User adding the product to the cart")
    public void user_adding_the_product_to_the_cart() {
        productDetailsPage.addToCart();
    }

    @Then("Product is displayed in the cart")
    public void product_is_displayed_in_the_cart() {
        Assert.assertTrue(productDetailsPage.isProductInCart(), "Product not found in the cart!");
    }

    @After("@ProductDetailsPageTest")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
