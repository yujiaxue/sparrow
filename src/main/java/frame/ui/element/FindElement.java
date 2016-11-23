package frame.ui.element;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import frame.tools.file.Constant;
import frame.ui.assertion.Assertion;

/**
 * @author zhangfujun
 *
 */

public class FindElement extends BaseElement {
	Logger logger = Logger.getLogger(FindElement.class);
	Assertion assertion;
	public FindElement(WebDriver rw) {
		super(rw);
		assertion = new Assertion(rw);
	}

	public WebElement searchByImplicity(String xpath) {
		WebElement ele = null;
		try {
			ele = rw.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			assertion.error(e.getMessage());
		} catch (TimeoutException e) {
			assertion.error(e.getMessage());
		}
		return ele;
	}

	public ArrayList<String> getLocator(String locator) {
		ArrayList<String> l = new ArrayList<String>();
		if (locator.contains("=")) {
			String[] temp = locator.split("=");
			l.add(0, temp[0].trim());
			l.add(1, temp[1].trim());
		} else {
			l.add(0, "xpath");
			l.add(1, locator);
		}
		return l;
	}
	
	/**
	 * 查找给定定位器的元素列表
	 * @param xpath
	 * @param seconds
	 * @return List<WebElement>
	 */
	public List<WebElement> searchsUntilPresent(String xpath,int seconds){
		List<WebElement> ele = null;
		By by = null;
		ArrayList<String> l = getLocator(xpath);
		by = getBy(l);
		if(null == by){
			by= By.xpath(xpath);
		}
		final By byt = by;
		WebDriverWait wdw = new WebDriverWait(rw, seconds, Integer.parseInt(System.getProperty(Constant.POLLING)));
		ele = wdw.until(new ExpectedCondition<List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(byt);
			}
		});
		return ele;
	}

	/**
	 * 返回给定定位器的元素
	 * @param xpath
	 * @param seconds
	 * @return WebElement
	 */
	public WebElement searchUntilPresent(String xpath, int seconds) {
		WebElement ele = null;
		By by = null;
		ArrayList<String> l = getLocator(xpath);
		by = getBy(l);
		if(null == by){
			by= By.xpath(xpath);
		}
		
		try {
			final By byt = by;
			WebDriverWait wdw = new WebDriverWait(rw, seconds, Integer.parseInt(System.getProperty(Constant.POLLING)));
			ele = wdw.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(byt);
				}
			});
			return ele;
		} catch (TimeoutException te) {
			assertion.info("查找元素超时,阀值" + seconds + "秒");
			// logger.error("查找元素超时" + seconds + "秒");
			// logger.error(te.getMessage());
		} catch (NumberFormatException te) {
			// logger.error(te.getMessage());
			assertion.error(te.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass() + "  " + e.getClass().toString());
		}
		return null;
	}
	public By getBy(ArrayList<String> l){
		By by = null;
		String locatorType = l.get(0);
		String locatorValue = l.get(1);
		if (locatorType.equals("xpath")) {
			by = By.xpath(locatorValue);
		} else if (locatorType.equals("id")) {
			by = By.id(locatorValue);
		} else if (locatorType.equals("class")) {
			by = By.className(locatorValue);
		} else if (locatorType.equals("name")) {
			by = By.name(locatorValue);
		} else if (locatorType.equals("css")) {
			by = By.cssSelector(locatorValue);
		} else if (locatorType.equals("linktext")) {
			by = By.linkText(locatorValue);
		} else if (locatorType.equals("tagname")) {
			by = By.tagName(locatorValue);
		} else if (locatorType.equals("partialLinkText")) {
			by = By.partialLinkText(locatorValue);
		}
		return by;
	}
}
