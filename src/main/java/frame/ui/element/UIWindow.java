package frame.ui.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UIWindow extends BaseElement {

	Logger logger = LogManager.getLogger(UIWindow.class);

	public UIWindow(WebDriver rw) {
		super(rw);
	}

	/**
	 * 
	 * @param url
	 */
	public void get(String url) {
		rw.get(url);
		logger.info("访问 " + url);
	}

	public void quit() {
		rw.quit();
		logger.info("退出WebDriver实例");
	}
	
	public void close() {
		rw.close();
		logger.info("关闭浏览器");
	}

	public void refresh() {
		rw.navigate().refresh();
		logger.info("刷新浏览器");
	}

	// cookie操作
	public void setCookie(String key, String value) {
		CookieOperation co = new CookieOperation(rw);
		co.addCookie(key, value);
		logger.info("设置cookie " + key + "=" + value);
	}

	// 判断检查

	/**
	 * 检查页面是否包含该文本
	 * 
	 * @param text
	 */
//	public boolean ifExists(String locator) {
//		WebElement ele = find.searchUntilPresent(locator);
//		if (ele == null) {
//			logger.error("元素没有找到 locator=" + locator);
//			Assert.fail("元素没有找到 locator=" + locator);
//			return false;
//		} else {
//			logger.info("元素找到 locator=" + locator);
//			return true;
//		}
//	}
	//窗口切换
//	public void switchWindow(int number){
//		switchwindow.switchWindow(number);
//	}
//	public String getWindowHandle(){
//		return switchwindow.getWindowHandle();
//	}

	public void back() {
		rw.navigate().back();
	}

	public void forward() {
		rw.navigate().forward();
	}

	
	
}
