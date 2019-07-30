package facebook;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import java.util.logging.*;

public class Firefox {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		Logger.getLogger("").setLevel(Level.OFF);

		String driverPath = "";

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/geckodriver.sh";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/geckodriver.exe";
		else
			throw new IllegalArgumentException("Unknown OS");

		System.setProperty("webdriver.gecko.driver", driverPath);

		driver = new FirefoxDriver();
		driver.manage().window().maximize();

		driver.get("https://www.facebook.com/");
		Thread.sleep(1000); // Pause in milliseconds (1000 – 1 sec)
		System.out.println("Title: " + driver.getTitle());
		
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(System.getenv("mylogin"));
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(System.getenv("password"));
		driver.findElement(By.xpath("//*[@value='Log In']")).click(); // 
		Thread.sleep(1000);

		// Click on Timeline button
		driver.findElement(By.xpath("//*[@title='Profile']/span/span")).click();
		Thread.sleep(1000);
		String friends = driver.findElement(By.xpath("//*[@data-tab-key='friends']")).getText();
		System.out.println("You have: " + friends + " friends");
		Thread.sleep(2000);

		// Click on Account Settings
		driver.findElement(By.id("userNavigationLabel")).click();
		Thread.sleep(2000);

		// Click on Log out button
		driver.findElement(By.linkText("Log Out")).click();
		
		
		
		driver.quit();
	}
}
