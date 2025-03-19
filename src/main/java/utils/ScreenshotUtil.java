package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File("screenshots/" + screenshotName + ".png"));
            System.out.println("Screenshot saved: " + screenshotName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
