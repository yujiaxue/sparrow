package frame.ui.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.jdbc.TcSql;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import frame.ui.assertion.Assertion;

public class SwitchWindow extends BaseElement {

	Logger logger = LogManager.getLogger(SwitchWindow.class);
	Assertion assertion;
	FindElement fe;

	public SwitchWindow(WebDriver rw) {
		super(rw);
		assertion = new Assertion(rw);
		fe = new FindElement(rw);
	}

	/**
	 * 切换至第 number个 窗口
	 * 
	 * @param number
	 */
	public void switchWindow(int number) {
		while (true) {
			int count = rw.getWindowHandles().size();
			if (number > count) {
				logger.error("没有该window");
				assertion.error("没有该window");
			} else {
				rw.switchTo().window(rw.getWindowHandles().toArray()[number - 1].toString());
				break;
			}
		}
	}

	/**
	 * 切换至指定名称的tab
	 * 
	 * @param windowHandle
	 */
	public void switchWindowByString(String windowHandle) {
		logger.info("切换至窗口" + windowHandle);
		rw.switchTo().window(windowHandle);
	}

	/**
	 * 获取当前窗口
	 * 
	 * @return
	 */
	public String getWindowHandle() {
		logger.info("获取当前窗口句柄");
		return rw.getWindowHandle();
	}

	public void switchToIframeByEle(String locator) {
		WebElement we = fe.searchUntilPresent(locator, Integer.parseInt(config.get("elementTimeout")));
		try {
			rw.switchTo().frame(we);
			TcSql.updateDone("done", "切换窗口至" + locator);
		} catch (WebDriverException e) {
			assertion.error(e.getMessage());
		}
	}

	public void exitFrame() {
		try {
			rw.switchTo().defaultContent();
		} catch (WebDriverException e) {
			assertion.error(e.getMessage());
		}
	}

}
