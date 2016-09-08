package frame.ui.sparrow;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import frame.ui.driver.RemoteChromeDriver;

public class BrowserFactory {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		RemoteChromeDriver rcd = new RemoteChromeDriver();
		driver = rcd.genDriver(new HashMap<String, String>());
	}

	@AfterMethod
	public void aftermethod() {
		//driver.close();∂
		//driver.quit();

	}

	@Test
	public void testThread1() {
		driver.get(" http://www.baidu.com");
		driver.findElement(By.id("kw")).sendKeys("我爱你祖国");
		Assert.assertEquals(driver.findElement(By.id("")).getText(), "我爱你祖国");
		System.out.println("我请求了百度");
	}

	@Test
	public void testThread2() {
		driver.get("http://www.iwjw.com");
		driver.findElement(By.linkText("新房")).click();
		System.out.println("我请求了爱屋及乌");
	}
}
