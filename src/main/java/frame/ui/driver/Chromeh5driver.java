package frame.ui.driver;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Chromeh5driver {
	
	public static WebDriver genDriver(){
		System.setProperty("webdriver.chrome.driver", "tools/chromedriver");
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities desired = new DesiredCapabilities();
		options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
		//desired.setCapability("chrome.switches", 
			//	Arrays.asList("--user-agent=\"Mozilla/5.0 (iPad; U; CPU OS 3_2_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B500 Safari/531.21.10\""));
		
		
		desired.setCapability(ChromeOptions.CAPABILITY, options);
		//options.addArguments("mobileEmulation=");
		ChromeDriver chromeDriver = new ChromeDriver(desired);
		
		Dimension targetSize = new Dimension(414, 736);
		chromeDriver.manage().window().setSize(targetSize);
		
		
		
		ChromeDriverService server  = new  ChromeDriverService.Builder().usingAnyFreePort().build();
		
		try {
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return chromeDriver;
	}
	public static void main(String[] args) {
		WebDriver driver = genDriver();
		driver.get("https://m.iwjw.com");
	}
}
