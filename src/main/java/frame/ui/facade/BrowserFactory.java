package frame.ui.facade;

import java.util.HashMap;

import org.openqa.selenium.remote.RemoteWebDriver;

import frame.tools.file.IniFile;
import frame.ui.driver.RemoteChromeDriver;

public class BrowserFactory {

	private static RemoteWebDriver driver;
	
	public static synchronized Browser getBrowser(){
		HashMap<String,String> config = new IniFile().getConfig();
		RemoteChromeDriver rcd = new RemoteChromeDriver();
		if(driver == null){
			 driver = rcd.genDriver(config);
		}
		//BaseElement be = new BaseElement(driver);
		return new Browser(driver);
	}
}
