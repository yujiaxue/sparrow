package frame.ui.element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.ScreenshotListener;

public class BaseElement {

	protected WebDriver rw;
	protected Actions action;
	protected JavascriptExecutor javascriptExeccutor;
	protected int elementTimeout = 30;
	public BaseElement(WebDriver rw) {
		super();
		this.rw = rw;
		this.action = new Actions(this.rw);
		this.javascriptExeccutor = (JavascriptExecutor)this.rw;
	}
	/**
	 * 设置显式等待时间
	 * @param seconds
	 * @return
	 */
	public int setElementTimeout(int seconds){
		this.elementTimeout = seconds;
		return this.elementTimeout;
	}
	
	
}
