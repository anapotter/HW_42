package facebook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Chrome {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		Logger.getLogger("").setLevel(Level.OFF);

		String driverPath = "";

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/chromedriver";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/chromedriver.exe";
		else
			throw new IllegalArgumentException("Unknown OS");

		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions option = new ChromeOptions();

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			option.addArguments("-start-fullscreen");
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			option.addArguments("--start-maximized");
		else
			throw new IllegalArgumentException("Unknown OS");

		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		System.out.println("Browser is: Chrome");

		driver.get("https://www.facebook.com/");
		//Thread.sleep(1000); // Pause in milliseconds (1000 – 1 sec)
		System.out.println("Title: " + driver.getTitle());

		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(System.getProperty("mylogin"));
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(System.getProperty("password"));
		driver.findElement(By.xpath("//*[@id=\"u_0_2\"]")).click();
		Thread.sleep(1000);

		// Click on Timeline button
		driver.findElement(By.xpath("//*[@id=\"u_0_a\"]/div[1]/div[1]/div/a/span")).click();
		Thread.sleep(1000);
		String friends = driver.findElement(By.xpath("//*[@id=\"u_fetchstream_2_9\"]/li[3]/a/span[1]")).getText();
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
