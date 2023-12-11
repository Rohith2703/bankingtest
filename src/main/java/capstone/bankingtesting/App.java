package capstone.bankingtesting;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Script Started");

        // Read test server IP from file
        String testServerIp = FileUtils.readFileToString(new File("/var/lib/jenkins/workspace/test-server-creation/test_ip.txt"), "UTF-8").trim().replaceAll("\"", "");
        //String testServerIp = FileUtils.readFileToString(new File("C:\\Users\\sairo\\Desktop\\terraform test\\test-ip.txt"), "UTF-8").trim().replaceAll("\"", "");
        System.out.println("Test Server IP: " + testServerIp);

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        // Open URL
        System.out.println("Driver opening up the URL in the browser");
        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            driver.get("http://" + testServerIp + ":8080/contact.html");

            // Implicit wait
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            System.out.println("Enter details in the form");

            // Enter details
            WebDriverWait wait = new WebDriverWait(driver, 10);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name"))).sendKeys("Tony Stark");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Phone Number"))).sendKeys("9988998899");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("IRONMAN@xyz.com");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Message"))).sendKeys("Hi, I am interested in FINANCE");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click();

            String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText();
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver in the finally block
            if (driver != null) {
                driver.quit();
            }
        }

        System.out.println("Test scripts are executed");

        // Take screenshot
        driver = new ChromeDriver(chromeOptions);
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("/var/lib/jenkins/workspace/bankapp-testing/test-ss.jpg");
            //File destFile = new File("C:\\Users\\sairo\\Desktop\\test-ss.jpg");
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot taken");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver in the finally block
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
