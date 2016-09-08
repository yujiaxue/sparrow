package frame.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AdministrativeDivisionSearch_RentHouse extends BaseCase{

	
	@Test
	public void search(){
		browser.get("http://pc.iwjwbeta.com");
		if(browser.checkIfExists("//div[@class='dialog-content']/ul/li")){
			browser.click("//div[@class='dialog-content']/ul/li");
		}
		
		browser.click("//span[@class='v-middle' and text()='租房']");
		browser.sendKeys("//input[@class='kw-input']","南翔");
		
		
	}
	@Test
	public void search2(){
		browser.get("http://www.baidu.com");
		browser.sendKeys("//input[@id='kw']", "jinrong");
	}
//	@AfterMethod
//	public void after(){
//		browser.close();
//	}
}
