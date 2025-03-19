package stepDefinitions;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;
import runner.TestRunner;

public class Hooks {
    WebDriver driver = TestRunner.driver;

    @AfterStep
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtil.captureScreenshot(driver, scenario.getName());
        }
    }
}
