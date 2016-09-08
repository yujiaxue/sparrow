package frame.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import frame.ui.facade.Browser;
import frame.ui.facade.BrowserFactory;

public class BaseCase {
	Browser browser;
	@BeforeClass
	public void setUp(){
		 browser = BrowserFactory.getBrowser();
	}
	
	@AfterClass
	public void teardown(){
		
	}
}
