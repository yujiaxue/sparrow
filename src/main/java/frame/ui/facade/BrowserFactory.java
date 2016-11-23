package frame.ui.facade;

import java.util.HashMap;

import org.framework.image.ScreenShot;
import org.openqa.selenium.WebDriver;

import com.iwjw.pageOperation.IwjwPageWrapper;

import frame.tools.file.IniFile;
import frame.ui.assertion.Assertion;
import frame.ui.driver.IDriver;
import frame.ui.driver.LocalChromeDriver;
import frame.ui.driver.RemoteChromeDriver;
import frame.ui.element.Alert;
import frame.ui.element.Attr;
import frame.ui.element.BaseElement;
import frame.ui.element.Clicker;
import frame.ui.element.CookieOperation;
import frame.ui.element.FindElement;
import frame.ui.element.Selector;
import frame.ui.element.SwitchWindow;
import frame.ui.element.UIWindow;

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
		BaseElement be = new BaseElement(driver);
		
		be.setConfig(config);
		be.setScreenShot(new ScreenShot());
		be.setAssertion(new Assertion());
		be.setCo(new CookieOperation());
		be.setFindElement(new FindElement());
		be.setAlert(new Alert());
		be.setUiwindow(new UIWindow());
		be.setClicker(new Clicker());
		be.setAttr(new Attr());
		be.setElementTimeout(Integer.parseInt(config.get("elementTimeout")));
		be.setSelector(new Selector());
		be.setSwitchwindow(new SwitchWindow());
		be.setIpw(new IwjwPageWrapper());
		Browser browser = new Browser();
		return browser;
	}
}
