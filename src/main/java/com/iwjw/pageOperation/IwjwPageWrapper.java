package com.iwjw.pageOperation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import frame.ui.assertion.Assertion;
import frame.ui.element.BaseElement;
import frame.ui.element.FindElement;

public class IwjwPageWrapper extends BaseElement {
	FindElement fe ;
	Assertion assertion;
	public IwjwPageWrapper(WebDriver rw){
		super(rw);
		fe = new FindElement(rw);
		assertion = new Assertion(rw);
	}
	
	public void checkModelCity(String xpath){
		
	}
	
	public void loginByCookie(){
		
	}
	
	public void loginByMobile(){
		
	}
	
	/**
	 * 给定tips的父级定位器 根据索引选择tips并点击
	 * @param xpath tips的父级定位器
	 * @param index tips的索引从1开始
	 */
	public void searchByTips(String xpath,int index){
		WebElement we= fe.searchUntilPresent(xpath, this.elementTimeout); 
		we.click();
	}
	
	
	/**
	 * 返回textXpath定位元素的文本，返回UIxpath定位元素的li个数；两者比较相等
	 * @param textXpath
	 * @param ULxpath
	 */
	public void assertTextEqualsUIListCount(String textXpath,String ULxpath){
		WebElement we = fe.searchUntilPresent(textXpath, elementTimeout);
		String text = we.getText().trim();
		int count = fe.searchsUntilPresent(ULxpath, elementTimeout).size();
		assertion.assertEquals(Integer.parseInt(text), count);
	}
	
	
	
	
	
	
	
	

}
