package stepDefinitions;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
import pages.ShoppingCart;
import utils.ScreenshotUtil;

public class ShoppingCartSteps {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SearchFunctionality searchFunctionality;
    ProductDetailsPage productDetailsPage;
    ShoppingCart shoppingCart;

    // **Choose Browser (Cross-Browser Testing)**
    @Before("@ShoppingCartTest")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        searchFunctionality = new SearchFunctionality(driver);
        productDetailsPage = new ProductDetailsPage(driver, wait);
        shoppingCart = new ShoppingCart(driver, wait);
    }

    @Given("User Entered homepage")
    public void user_entered_homepage() {
        driver.get("https://www.bookswagon.com/");
    }

    @When("User logged in to the acc with mobile number {string} and password {string}")
    public void user_logged_in_to_the_acc_with_mobile_number_and_password(String number, String password){
    	WebElement MyaccButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_lblUser")));
        MyaccButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_SignIn_txtEmail")));
        emailField.sendKeys(number);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_SignIn_txtPassword")));
        passwordField.sendKeys(password);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_phBody_SignIn_btnLogin")));
        submitButton.click();
      
    }

    @When("User searches {string} adds other products to the cart")
    public void user_searchs_adds_other_products_to_the_cart(String bookName) {
        homePage.searchForBook(bookName);
        shoppingCart.addItemToCart();
    }

    @When("User removes the product and updates the quantities")
    public void user_removes_the_product_and_updates_the_quantities() throws InterruptedException {
        shoppingCart.openCart();
        
        shoppingCart.removeProduct();
        
        shoppingCart.increaseQuantity();
        Thread.sleep(5000);
      
    }

    @When("User proceeds to the checkout")
    public void user_proceeds_to_the_checkout() throws InterruptedException{
        shoppingCart.proceedToCheckout();
        // **Display message instead of proceeding to payment**
        System.out.println("Book Purchased");
        // **Capture screenshot**
        ScreenshotUtil.captureScreenshot(driver, "BookPurchased");
        Thread.sleep(5000);
    }

    @Then("Checkout page is displayed")
    public void checkout_page_is_displayed() throws InterruptedException {
        String actualTitle = shoppingCart.getCheckoutPageTitle();
        String expectedTitle = "Checkout Your Cart";
        Assert.assertEquals(actualTitle, expectedTitle, "Checkout page title mismatch!");
        Thread.sleep(5000);
    }
    
    @After("@ShoppingCartTest")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
