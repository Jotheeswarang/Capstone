package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // **Locators**
    private By bookTitle = By.id("ctl00_phBody_ProductDetail_lblTitle");
    private By bookDescription = By.id("ctl00_phBody_ProductDetail_lblLongDesc");
    private By bookPrice = By.id("ctl00_phBody_ProductDetail_lblourPrice");
    private By bookImage = By.cssSelector("img[alt*='Harry Potter']");
    private By bookAvailability = By.id("ctl00_phBody_ProductDetail_lblAvailable");
    private By wishlistButton = By.xpath("//*[@id=\"bordercornerbox\"]/div/a");
    private By wishlistcountButton = By.id("ctl00_lblWishlistCount");
    private By addtocartButton = By.id("ctl00_phBody_WishList_lvWishList_ctrl0_btnaddtocart");
    private By cartItems = By.id("ctl00_lblTotalCartItems");
    private By cartProducts = By.id("ctl00_phBody_BookCart_lvCart_lblCartItems");

    // **Constructor**
    public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // **Method: Get product details**
    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitle)).getText();
    }

    public String getProductDescription() {
        return driver.findElement(bookDescription).getText();
    }

    public double getProductPrice() {
        String priceText = driver.findElement(bookPrice).getText().replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(priceText);
    }

    public boolean isProductImageDisplayed() {
        return driver.findElement(bookImage).isDisplayed();
    }

    public boolean isProductAvailable() {
        return driver.findElement(bookAvailability).isDisplayed();
    }

    // **Method: Add product to wishlist**
    public void addToWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistButton)).click();
    }

    // **Method: Add product to cart**
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistcountButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addtocartButton)).click();
    }

    // **Method: Check if product is in cart**
    public boolean isProductInCart() {
        driver.findElement(cartItems).click();
        return driver.findElements(cartProducts).size() > 0;
    }
}
