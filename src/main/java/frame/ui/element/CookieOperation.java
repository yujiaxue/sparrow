package frame.ui.element;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CookieOperation extends BaseElement{

	public CookieOperation(RemoteWebDriver rw) {
		super(rw);
	}
	/**
	 * 设置cookie
	 * @param key cookie's name
	 * @param value cookie's value
	 */
	public void addCookie(String key,String value){
		Cookie c = new Cookie(key, value);
		rw.manage().addCookie(c);
	}
	/**
	 * 删除指定cookie
	 * @param key 删除cookie的name
	 */
	public void removeCookie(String key){
		Cookie cookie = rw.manage().getCookieNamed(key);
		rw.manage().deleteCookie(cookie);
	}
	public void deleteAllCookie(){
		rw.manage().deleteAllCookies();
	}
	public String getCookieByName(String key){
		return rw.manage().getCookieNamed(key).getValue();
	}
	public Set<Cookie> getAllCookie(){
		return rw.manage().getCookies();
	}

}
