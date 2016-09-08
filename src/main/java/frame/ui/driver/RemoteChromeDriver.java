package frame.ui.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import frame.tools.file.Constant;

public class RemoteChromeDriver implements IDriver {

	public RemoteWebDriver genDriver(HashMap<String, String> config) {
		if (System.getProperty("os.name").equals("Mac OS X")) {
			System.setProperty("webdriver.chrome.driver", config.get("driverpath"));
		} else {
			System.setProperty("webdriver.chrome.driver", "D:\\workspace\\AgentWEBui\\tools\\chromedriver.exe");
		}
		DesiredCapabilities desiredCap = DesiredCapabilities.chrome();
		desiredCap.setBrowserName(BrowserType.CHROME);
		// desiredCap.setPlatform(Platform.WIN10);
		// desiredCap.setPlatform(Platform.MAC);
		RemoteWebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(config.get("hub")), desiredCap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.get(Constant.ELEMENTTIMEOUT)), TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.get("pageTimeout")), TimeUnit.SECONDS);
//		driver.manage().timeouts().setScriptTimeout(Integer.parseInt(config.get("scriptTimeout")), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.setLogLevel(Level.FINEST);
		return driver;
	}

}
