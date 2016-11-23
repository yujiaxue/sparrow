package frame.ui.facade;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.jdbc.TcSql;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import com.iwjw.pageOperation.IwjwPageWrapper;

import frame.ui.assertion.Assertion;
import frame.ui.element.Alert;
import frame.ui.element.Attr;
import frame.ui.element.BaseElement;
import frame.ui.element.Clicker;
import frame.ui.element.FindElement;
import frame.ui.element.Selector;
import frame.ui.element.SwitchWindow;
import frame.ui.element.UIWindow;

/**
 * 浏览器操作类
 */
/**
 * @author zhangfujun
 *
 */

public class Browser extends BaseElement {

	private static final Logger logger = LogManager.getLogger(Browser.class);
	public UIWindow uiwindow;
	public SwitchWindow switchwindow;
	public Clicker clicker;
	public FindElement findElement;
	public Attr attr;
	public IwjwPageWrapper ipw;
	public Assertion assertion;
	public Selector selector;
	public Alert alert;
	public Browser(WebDriver rw) {
		super(rw);
		super.setElementTimeout(Integer.parseInt(config.get("elementTimeout")));
		this.uiwindow = new UIWindow(rw);
		this.switchwindow = new SwitchWindow(rw);
		this.clicker = new Clicker(rw);
		this.findElement = new FindElement(rw);
		this.attr = new Attr(rw);
		this.ipw = new IwjwPageWrapper(rw);
		this.assertion = new Assertion(rw);
		this.selector = new Selector(rw);
		this.alert = new Alert(rw);
	}

	/**
	 * 加载新的页面
	 *
	 * @param url
	 */
	public void get(String url) {
		uiwindow.get(url);
		uiwindow.setCookie("iw_user_province_cookie", "2");
		uiwindow.refresh();
		TcSql.updateDone("done", "打开网址" + url);
	}

	/**
	 * 退出当前driver
	 */
	public void quit() {
		uiwindow.quit();
	}

	/**
	 * 模拟键盘动作F5刷新当前页面
	 */
	public void refresh() {
		uiwindow.refresh();
		TcSql.updateDone("done", "刷新页面");
	}

	/***
	 * 查找当前页面指定文本是否存在，存在表示断言通过
	 * 
	 * @param text
	 */
	public Boolean searchText(String text) {
		return checkIfExists("//*[contains(text(),'" + text + "')]");
	}

	/**
	 * 验证当前页面定位器能否定位到元素
	 * 
	 * @param locator
	 *            页面元素定位器
	 * @return 在预定时间内定位到就返回true, 没有定位到则返回false.
	 */
	public boolean checkIfExists(String locator) {
		WebElement ele = findElement.searchUntilPresent(locator, elementTimeout);
		if (ele == null) {
			assertion.info("没有找到元素 locator=" + locator);
			TcSql.updateDone("done", "没有找到元素 locator=" + locator);
			return false;
		} else {
			assertion.info("找到元素 locator=" + locator);
			TcSql.updateDone("done", "找到元素 locator=" + locator);
			return true;
		}
	}

	/**
	 * 切换焦点到指定的窗口,参数为窗口生成的次序,如1是最初打开的窗口 注意执行切回原来页面的步骤时，需close当前子页面
	 * 再调用switchWindow 切回主页
	 * 
	 * @param number
	 *            当前页面的索引值，默认为1 新开的页面既为2 类推
	 */
	public void switchWindowInt(int number) {
		switchwindow.switchWindow(number);
		TcSql.updateDone("done", "切换tab窗口至第" + number + "个");
	}

	/**
	 * 获取当前页面的 handle
	 * 
	 * @return
	 */
	public String getWindowHandle() {
		return switchwindow.getWindowHandle();
	}

	public void switchWindowString(String windowHandle) {
		switchwindow.switchWindowByString(windowHandle);
		TcSql.updateDone("done", "切换tab窗口至" + windowHandle);
	}

	/**
	 * 关闭当前浏览器窗口
	 */
	public void close() {
		uiwindow.close();
		TcSql.updateDone("done", "关闭浏览器窗口");
	}

	/**
	 * 返回上一个访问的页面
	 */
	public void goBack() {
		uiwindow.back();
		TcSql.updateDone("done", "浏览器后退");
	}

	/**
	 * 进到后一个页面，浏览器的前进功能
	 */
	public void goForward() {
		uiwindow.forward();
		TcSql.updateDone("done", "浏览器向前");
	}

	/**
	 * 鼠标左键单击一个页面元素
	 * 
	 * @param locator
	 *            定位器
	 */
	public void click(String locator) {
		clicker.clickByLocator(locator);
	}

	/**
	 * 鼠标左键单击一个页面元素
	 * 
	 * @param text
	 *            文本内容
	 */
	public void clickOnText(String text) {
		clicker.clickOnText(text);
	}

	/**
	 * 对原有的字符进行清除，只输入值
	 * 
	 * @param locator
	 * @param value
	 */
	public void sendKeys(String locator, String value) {
		WebElement ele = findElement.searchUntilPresent(locator, elementTimeout);
		if (null == ele) {
			assertion.error("没有找到元素locator=" + locator);
			TcSql.updateDone("fail", "没有找到元素locator=" + locator);
		} else {
			ele.clear();
			ele.sendKeys(value);
			assertion.info("输入" + value + "至localtor=" + locator + "成功");
			TcSql.updateDone("done", "输入" + value + "至localtor=" + locator + "成功");
		}
	}

	/**
	 * 获取定位元素的页面可见值,包括子元素,会自动去除前后留白.超时时间根据配置文件中的 #超时时间 单位是秒 timeout 来定义
	 * 
	 * @param locator
	 *            元素定位器
	 * @return 返回页面元素的值
	 */
	public String getText(String locator) {
		WebElement we = findElement.searchUntilPresent(locator, elementTimeout);
		String text = we.getText().trim();
		// TcSql.updateDone("done", "获取元素文本"+text);
		return text;
	}

	/**
	 * 选择下拉列表,有些元素看着像下拉列表其实是js控制的div,使用前最好先看一下元素的标签是否为select.
	 * 
	 * @param selectLocator
	 *            页面元素定位器
	 * @param optionLocator
	 *            指定要选择的元素
	 */
	public void selectByVisibleText(String selectLocator, String optionLocator) {
		selector.selectByVisibleText(selectLocator, optionLocator);
	}

	/**
	 * 选择下拉列表,传入select的是index
	 * 
	 * @param selectLocator
	 *            页面元素定位器
	 * @param index
	 *            指定要选择的元素
	 */
	public void selectByIndex(String selectLocator, int index) {
		selector.selectByIndex(selectLocator, index);
	}

	/**
	 * 选择下拉列表，传入select的是value
	 * 
	 * @param selectLocator
	 * @param value
	 */
	public void selectByValue(String selectLocator, String value) {
		selector.selectByValue(selectLocator, value);
	}

	/***
	 * 当目标定位器对应多个控件时取最后一个
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getLastElementInList(String locator) {
		List<WebElement> elementList = findElement.searchsUntilPresent(locator, elementTimeout);
		int listLength = elementList.size();
		return elementList.get(listLength - 1);
	}

	/**
	 * 获取当前页面活动元素的值，focus的元素
	 */
	public String getActiveElementText() {
		return rw.switchTo().activeElement().getText().trim();
	}

	/**
	 * 切换到需要操作的iframe
	 * 
	 * @param i
	 *            当前需要操作的iframe下标 默认从0开始
	 */
	public void switchToIframeByIndex(int i) {
		try {
			switchwindow.switchWindow(i);
			logger.info("跳转到frame"+i+"成功");
		} catch (WebDriverException e) {
			logger.info("跳转到frame"+i+"失败");
		}
	}
	
	 /**
	 * 切换到需要操作的iframe
	 * @param locator frameElement元素定位器
	 */
	 public void switchToIframeByLocator(String locator) {
		 switchwindow.switchToIframeByEle(locator);
	 }
	
	
	
	
	
	
	 /**
	 * 对跳出的窗口输入文字
	 * @param value
	 */
	 public void typeVauleForAlert(String value){
		 alert.sendkeys(value);
	 }
	
	 /**
	 * 确认弹出框
	 */
	 public void confirmAlert() {
	 	alert.accept();
	 }
	
	
	 /***
	 * 取消弹出框
	 */
	 public void dismissAlert(){
		alert.dismiss();
	 }
	
	
	 /***
	 * 取弹出框文本值
	 * @return
	 */
	 public String getAlertText(){
	 return alert.getText();
	 }
	
	
	
	
	 
	
	
	 
	
	 /**
	 * 拖拉一个元素到另一个元素 默认超时时间为config文件中配置的timeout时间 单位秒
	 * @param sourceLoc 源元素定位器
	 * @param targetLoc 目标元素定位器
	 */
	 public void dragAndDrop(String sourceLoc, String targetLoc) {
		 uiwindow.dragAndDrop(sourceLoc, targetLoc, elementTimeout);
	 }
	
	
	
	
	
	
	 /**
	 * 执行js
	 * @param js
	 */
	 public void runScript(String js) {
		 uiwindow.runScript(js);
	 }
	

	/**
	 * 根据指定的name获取cookie的value
	 * 
	 * @param name
	 * @return
	 */
	public String getCookieValueByName(String name) {
		return uiwindow.getCookieValue(name);
	}

	/**
	 * 设置cookie
	 * 
	 * @param key
	 * @param value
	 */
	public void setCookie(String key, String value) {
		uiwindow.setCookie(key, value);
		TcSql.updateDone("done", "设置cookie key=" + key + ";value=" + value);
	}

	/***
	 * 向下滚动屏幕至底
	 */
	public void scroolDown() {
		uiwindow.srollDown();
	}

	/***
	 * 滚动至屏幕中央
	 */
	public void scroolDownHalf() {
		uiwindow.scrollDownHalf();
	}

	/***
	 * 向上滚动一屏屏幕
	 */
	public void scroolUp() {
		uiwindow.scrollUp();
	}

	/***
	 * 移动鼠标至元素
	 * 
	 * @param locator
	 */
	public void moveToElement(String locator) {
		WebElement ele = findElement.searchUntilPresent(locator, elementTimeout);
		action.moveToElement(ele);
		TcSql.updateDone("done", "移动鼠标至" + locator);
	}

	/**
	 * 移动鼠标至元素并点击
	 */
	public void moveToElementClick(String locator) {
		WebElement ele = findElement.searchUntilPresent(locator, elementTimeout);
		action.moveToElement(ele).click().perform();
		TcSql.updateDone("done", "移动鼠标至" + locator + "并点击");
	}

	/**
	 * 定位器在当前页面能定位到元素的个数,如果没有定位到就返回零.
	 * 
	 * @param locator
	 *            元素定位器
	 * @return 返回元素个数
	 */
	public int getElementCount(String locator) {
		return findElement.searchsUntilPresent(locator, elementTimeout).size();
	}

	/**
	 * 通过对象的equals方式判断两个变量内容是否相等
	 * 
	 * @param expected
	 *            预期的值
	 * @param locator
	 *            实际值的定位器
	 * 
	 */
	public void assertEqualString(String locator, String expected) {
		String actual = findElement.searchUntilPresent(locator, elementTimeout).getText().trim();
		assertion.assertEquals(actual, expected);
	}

	/**
	 * 通过对象的equals方式判断两个整型变量内容是否相等
	 * 
	 * @param expected
	 *            预期的值
	 * @param actual
	 *            实际值所在的定位器
	 */
	public void assertEqualsInt(int expected, String actual) {
		String actualText = findElement.searchUntilPresent(actual, elementTimeout).getText().trim();
		assertion.assertEquals(expected, Integer.parseInt(actualText));
	}

	/**
	 * /** 通过对象的equals方式判断两个变量内容是否相等,通过attr确定获取元素的哪个属性值,value,text
	 * 
	 * @param locator
	 *            给定元素的定位器
	 * @param expected
	 *            期望的文本字符串
	 * @param attr
	 *            期望的属性 value,text
	 * @return
	 */
	public void assertAttrEquals(String locator, String expected, String attr) {
		WebElement we = findElement.searchUntilPresent(locator, elementTimeout);
		String actual = "";
		if ("text".equals(attr)) {
			actual = we.getText().trim();
		} else if ("value".equals(attr)) {
			actual = we.getAttribute("value").trim();
		}
		assertion.assertEquals(actual, expected);
	}

	/***
	 * 断言指定的目标控件存在
	 * 
	 * @param locator
	 *            目标控件ID
	 */
	public void assertViewExist(String locator) {
		checkIfExists(locator);
	}

	/**
	 * 断言condition是否为True
	 * 
	 * @param condition
	 *            需要被断言的变量
	 */
	public void assertTrue(Boolean condition) {
		assertion.assertTrue(condition);
	}

	/**
	 * 断言condition是否为False，不为false则中断执行
	 * 
	 * @param condition
	 *            需要被断言的变量
	 */
	public void assertFalse(Boolean condition) {
		assertion.assertFalse(condition);
	}

	/**
	 * 断言locator元素的文本值是否包含expected
	 * 
	 * @param locator
	 * @param expected
	 */
	public void assertContain(String locator, String expected) {
		String actual = getText(locator);
		assertion.assertContains(actual, expected);
	}

	/**
	 * 断言给定定位器元素的值不等于给定预期值
	 * 
	 * @param locator
	 * @param expected
	 */
	public void assertNotEquals(String locator, String expected) {
		String actual = getText(locator);
		if (actual.equals(expected)) {
			logger.error(actual + expected + "相等");
			assertion.error(actual + expected + "相等");
		} else {
			assertion.pass(actual + expected + "不相等");
		}
	}

	/**
	 * 断言给定定位器的css的propertyName指定的属性和预期expected相等
	 * 
	 * @param locator
	 * @param propertyName
	 * @param expected
	 */
	public void assertCssValue(String locator, String propertyName, String expected) {
		WebElement we = findElement.searchUntilPresent(locator, elementTimeout);
		String cssvalue = we.getCssValue(propertyName);
		assertion.assertEquals(cssvalue, expected);
	}

	/**
	 * 断言给定定位器的css的背景色与给定的expected相等
	 * 
	 * @param locator
	 * @param propertyName
	 * @param expected
	 */
	public void assertCssColor(String locator, String expected) {
		WebElement we = findElement.searchUntilPresent(locator, elementTimeout);
		String cssvalue = we.getCssValue("background");
		String cssColor = Color.fromString(cssvalue).asHex();
		this.assertion.assertEquals(cssColor, expected);
	}
	/**
	 * 返回textXpath定位元素的文本，返回UIxpath定位元素的li个数；两者比较相等
	 * @param textXpath
	 * @param ULxpath
	 */
	public void assertTextEqualsUIListCount(String textXpath,String ULxpath){
		ipw.assertTextEqualsUIListCount(textXpath, ULxpath);
	}

}