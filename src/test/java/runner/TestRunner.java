package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-reports-html",
    		  "pretty", "json:target/cucumber-reports-json/Cucumber.json"},
    publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;
    
    @BeforeTest
    @Parameters({"browser"})
    public void setUp(@Optional("chrome")String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
