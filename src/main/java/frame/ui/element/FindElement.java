package frame.ui.element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import frame.tools.file.Constant;
import frame.ui.assertion.Assertion;

public class FindElement extends BaseElement {
	Logger logger = Logger.getLogger(FindElement.class);

	public FindElement(WebDriver rw) {
		super(rw);
	}
	public WebElement searchByImplicity(String xpath){
		WebElement ele = null;
		try{
			ele = rw.findElement(By.xpath(xpath));
		}catch(NoSuchElementException e){
			Assertion.error(e.getMessage());
		}catch(TimeoutException e){
			Assertion.error(e.getMessage());
		}
		return ele;
	}

	public WebElement searchUntilPresent(String xpath,int seconds) {
		WebElement ele = null;
		try {
			final String templ = xpath;
			WebDriverWait wdw = new WebDriverWait(rw, seconds,
					Integer.parseInt(System.getProperty(Constant.POLLING)));
			ele = wdw.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(templ));
				}
			});
			return ele;
		} catch (TimeoutException te) {
			Assertion.info("查找元素超时,阀值" + seconds + "秒");
			//logger.error("查找元素超时" + seconds + "秒");
			//logger.error(te.getMessage());
		}catch(NumberFormatException te){
			//logger.error(te.getMessage());
			Assertion.error(te.getMessage());
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getClass()+ "  "+ e.getClass().toString());
		}
		return null;
	}
}
