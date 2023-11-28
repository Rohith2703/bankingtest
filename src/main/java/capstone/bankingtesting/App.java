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

public class App 
{
    public static void main( String[] args ) throws InterruptedException, IOException
    {
    System.out.println("Script Started");	
       //initializing the web driver
    //System.setProperty("webdriver.chrome.driver", "C:\\Users\\sairo\\Desktop\\DEvops\\Assignments\\chromedriver-win641\\chromedriver-win64\\chromedriver.exe");
    //setting properties
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
	//chromeOptions.addArguments("--headless");
	//chromeOptions.addArguments("--disable-gpu");
	//chromeOptions.addArguments("--no-sandbox");
    //open url
    System.out.println("Driver opening up the url in browser");	
    WebDriver driver = new ChromeDriver(chromeOptions);
    driver.get("http://localhost:8080/contact.html");	
    //invole implicit waits to load the page
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    System.out.println("Enter details in the form");
    //enter details
    driver.findElement(By.name("Name")).sendKeys("Tony Stark");
    Thread.sleep(1000);
    driver.findElement(By.name("Phone Number")).sendKeys("9988998899");
    Thread.sleep(1000);
    driver.findElement(By.name("Email")).sendKeys("IRONMAN@xyz.com");
    Thread.sleep(1000);
    driver.findElement(By.name("Message")).sendKeys("Hi, Iam intrested in FINANCE");
    Thread.sleep(1000);
    driver.findElement(By.id("submit")).click();
    Thread.sleep(1000);
    String response = driver.findElement(By.id("message")).getText();
    System.out.println(response);
    Thread.sleep(1000);
    System.out.println("test scripts are executed");
    TakesScreenshot scrShot = ((TakesScreenshot)driver);
    File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
    File destFile = new File("C:\\Users\\sairo\\Desktop\\test-ss.jpg");
    FileUtils.copyFile(srcFile, destFile);
    Thread.sleep(1000);
    System.out.println("ScreenShot Taken");
    driver.quit();
    }
}