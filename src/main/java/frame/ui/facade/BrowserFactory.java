package frame.ui.facade;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import frame.tools.file.IniFile;
import frame.ui.driver.IDriver;
import frame.ui.driver.LocalChromeDriver;
import frame.ui.driver.RemoteChromeDriver;

public class BrowserFactory {

	private static WebDriver driver;
	
	public static synchronized Browser getBrowser(){
		HashMap<String,String> config = new IniFile().getConfig();
		IDriver rcd = null;
		if(config.get("remote").equals("true")){
			rcd  = new RemoteChromeDriver();
		}else if(config.get("browser").equalsIgnoreCase("chrome")){
			rcd = new LocalChromeDriver(); 
		}
		if(driver == null){
			 driver = rcd.genDriver(config);
		}
		//BaseElement be = new BaseElement(driver);
		Browser browser = new Browser(driver);
		browser.setConfig(config);
		return browser;
	}
}
