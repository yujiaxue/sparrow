package frame.ui.element;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class BaseElement {

	protected WebDriver rw;
	protected Actions action;
	protected JavascriptExecutor javascriptExeccutor;
	protected int elementTimeout = 30;
	protected Map<String,String> config;
	
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
	public void setElementTimeout(int seconds){
		this.elementTimeout = seconds;
	}
	

	/**
	 * 
	 * @param config
	 */
	public void setConfig(Map<String, String> config) {
		this.config = config;
	}
	
}
