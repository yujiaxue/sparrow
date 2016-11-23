package frame.ui.element;

import java.util.Map;

import org.framework.image.ScreenShot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.iwjw.pageOperation.IwjwPageWrapper;

import frame.ui.assertion.Assertion;

public class BaseElement {

	protected static WebDriver rw;
	protected static Actions action;
	protected static JavascriptExecutor javascriptExeccutor;
	protected static int elementTimeout = 30;
	protected static Map<String, String> config;
	public static UIWindow uiwindow;
	public static SwitchWindow switchwindow;
	public static Clicker clicker;
	public static FindElement findElement;
	public static Attr attr;
	public static IwjwPageWrapper ipw;
	public static Assertion assertion;
	public static Selector selector;
	public static Alert alert;
	public static CookieOperation co;
	public static ScreenShot ss;

	public BaseElement() {

	}

	public BaseElement(WebDriver rw) {
		super();
		BaseElement.rw = rw;
		action = new Actions(BaseElement.rw);
		javascriptExeccutor = (JavascriptExecutor) BaseElement.rw;
	}

	/**
	 * 设置显式等待时间
	 * 
	 * @param seconds
	 * @return
	 */
	public void setElementTimeout(int seconds) {
		BaseElement.elementTimeout = seconds;
	}

	public void setConfig(Map<String, String> config) {
		BaseElement.config = config;
	}

	public void setUiwindow(UIWindow uiwindow) {
		BaseElement.uiwindow = uiwindow;
	}

	public void setSwitchwindow(SwitchWindow switchwindow) {
		BaseElement.switchwindow = switchwindow;
	}

	public void setClicker(Clicker clicker) {
		BaseElement.clicker = clicker;
	}

	public void setFindElement(FindElement findElement) {
		BaseElement.findElement = findElement;
	}

	public void setAttr(Attr attr) {
		BaseElement.attr = attr;
	}

	public void setIpw(IwjwPageWrapper ipw) {
		BaseElement.ipw = ipw;
	}

	public void setAssertion(Assertion assertion) {
		BaseElement.assertion = assertion;
	}

	public void setSelector(Selector selector) {
		BaseElement.selector = selector;
	}

	public void setAlert(Alert alert) {
		BaseElement.alert = alert;
	}

	public void setCo(CookieOperation co) {
		BaseElement.co = co;
	}

	public void setScreenShot(ScreenShot screenShot) {
		BaseElement.ss = screenShot;
	}
}
