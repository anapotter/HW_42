package facebook;

import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.*;

public class Safari {

	public static void main(String[] args) throws InterruptedException {
		Logger.getLogger("").setLevel(Level.OFF);

		WebDriver driver;
		driver = new SafariDriver();
		driver.manage().window().maximize();

		driver.get("https://www.facebook.com/");
		Thread.sleep(1000); // Pause in milliseconds (1000 – 1 sec)
		System.out.println("Title: " + driver.getTitle());
		
		
		driver.quit();
	}
}
