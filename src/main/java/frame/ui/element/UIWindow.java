package frame.ui.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class UIWindow extends BaseElement {

	Logger logger = LogManager.getLogger(UIWindow.class);


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
		co.addCookie(key, value);
		logger.info("设置cookie " + key + "=" + value);
	}
	public String getCookieValue(String key){
		String value = co.getCookieByName(key);
		logger.info("获取cookie key="+key+"的值为:"+value);
		return value;
	}
	// 判断检查

	/**
	 * 检查页面是否包含该文本
	 * 
	 * @param text
	 */
	// public boolean ifExists(String locator) {
	// WebElement ele = find.searchUntilPresent(locator);
	// if (ele == null) {
	// logger.error("元素没有找到 locator=" + locator);
	// Assert.fail("元素没有找到 locator=" + locator);
	// return false;
	// } else {
	// logger.info("元素找到 locator=" + locator);
	// return true;
	// }
	// }
	// 窗口切换
	// public void switchWindow(int number){
	// switchwindow.switchWindow(number);
	// }
	// public String getWindowHandle(){
	// return switchwindow.getWindowHandle();
	// }

	public void back() {
		rw.navigate().back();
	}

	public void forward() {
		rw.navigate().forward();
	}

	public void scrollUp() {
		javascriptExeccutor.executeScript("window.scrollTo(document.body.scrollHeight,0);");
		logger.info("滚动屏幕至顶端");
	}
	public void srollDown(){
		javascriptExeccutor.executeScript("window.scrollTo(document.body.scrollHeight);");
		logger.info("滚动屏幕至底部");
	}
	public void scrollDownHalf(){
		javascriptExeccutor.executeScript("window.scrollTo(0,document.body.scrollHeight/2);");
		logger.info("滚动至屏幕中央");
	}
	
	public void clear(WebElement ele){
		ele.clear();
		logger.info("清除文本框的文本");
	}
	public boolean isDisplays(WebElement ele){
		return ele.isDisplayed();
	}
	
	public boolean isSelected(String locator){
		WebElement we = findElement.searchUntilPresent(locator, Integer.parseInt(config.get("elementTimeout")));
		boolean selected=false;
		try{
			selected = we.isSelected();
		}catch(WebDriverException e){
			logger.info("元素是否被选中判断失败："+e.getMessage());
			assertion.error("元素是否被选中判断失败："+e.getMessage());
		}
		return selected;
	}
	
	public void dragAndDrop(String sourceLocator,String destLocator,int timeout){
		try{
			WebElement source = findElement.searchUntilPresent(sourceLocator,timeout);
			WebElement dest = findElement.searchUntilPresent(destLocator, timeout);
			action.dragAndDrop(source, dest).perform();
		}catch(WebDriverException e){
			logger.info("draganddrop fail:"+e.getMessage());
			assertion.error("draganddrop fail:"+e.getMessage());
		}
	}
	public void runScript(String javascript){
		try{
			javascriptExeccutor.executeScript(javascript);
			logger.info("运行javascript脚本成功");
		}catch(WebDriverException e){
			logger.info("运行javascript脚本失败:"+e.getMessage());
		}
	}
}
