package frame.ui.driver;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocalChromeDriver implements IDriver{

	public WebDriver genDriver(HashMap<String, String> config) {
		System.setProperty("webdriver.chrome.driver", "tools/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		WebDriver driver = new ChromeDriver(desiredCapabilities);
		return driver;
	}

}
