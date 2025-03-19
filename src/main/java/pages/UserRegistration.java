package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserRegistration {
    private WebDriver driver;
    private WebDriverWait wait;

    // **Locators**
    private By myAccountButton = By.id("ctl00_lblUser");
    private By signupLink = By.cssSelector(".themecolor.d-block.w-100.mb-3.font-weight-bold.signuplink");
    private By loginLink = By.cssSelector(".btn.btn-block.themebackground.text-white.loginlink");
    private By emailField = By.id("ctl00_phBody_SignIn_txtEmail");
    private By passwordField = By.id("ctl00_phBody_SignIn_txtPassword");
    private By loginButton = By.id("ctl00_phBody_SignIn_btnLogin");
    
    // **Password Change Locators**
    //private By changePasswordLink = By.xpath("//*[@id=\"aspnetForm\"]/header/div[2]/div/div[3]/ul/li[1]/div/div/ul/li[7]/a");
    private By changePasswordLink = By.cssSelector(".accountbox a[href='changepassword.aspx']");

    //WebElement changePasswordButton = driver.findElement(By.cssSelector(".accountbox a[href='changepassword.aspx']"));
    
    private By currentPasswordField = By.id("ctl00_phBody_ChangePassword_txtCurPwd");
    private By newPasswordField = By.id("ctl00_phBody_ChangePassword_txtNewPassword");
    private By confirmPasswordField = By.id("ctl00_phBody_ChangePassword_txtConfirmPwd");
    private By submitChangePasswordButton = By.id("ctl00_phBody_ChangePassword_imgSubmit");
    private By successMessage = By.id("ctl00_phBody_ChangePassword_lblmsg");

    // **Constructor**
    public UserRegistration(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // **Method: Click on My Account Button**
    public void clickMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountButton)).click();
    }

    // **Method: Click on Signup**
    public void clickSignup() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLink)).click();
    }

    // **Method: Click on Login**
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    // **Method: Login to Account**
    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // **Method: Change Password**
    public void changePassword(String currentPassword, String newPassword) throws InterruptedException {
    	WebElement accountButton= wait.until(ExpectedConditions.elementToBeClickable(myAccountButton));
    	accountButton.click();
    	WebElement changePwdLink =wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink));
    	changePwdLink.click();
    	Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPasswordField)).sendKeys(currentPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordField)).sendKeys(newPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField)).sendKeys(newPassword);
        wait.until(ExpectedConditions.elementToBeClickable(submitChangePasswordButton)).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPasswordField)).sendKeys(newPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordField)).sendKeys(currentPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField)).sendKeys(currentPassword);
        wait.until(ExpectedConditions.elementToBeClickable(submitChangePasswordButton)).click();
    }

    // **Method: Check if Password Change was Successful**
    public boolean isPasswordChangedSuccessfully() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
}
