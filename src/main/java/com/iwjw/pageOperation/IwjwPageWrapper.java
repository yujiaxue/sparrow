package com.iwjw.pageOperation;

import org.openqa.selenium.WebElement;

import frame.ui.element.BaseElement;

public class IwjwPageWrapper extends BaseElement {
	
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
		WebElement we= findElement.searchUntilPresent(xpath, this.elementTimeout); 
		we.click();
	}
	
	
	/**
	 * 返回textXpath定位元素的文本，返回UIxpath定位元素的li个数；两者比较相等
	 * @param textXpath
	 * @param ULxpath
	 */
	public void assertTextEqualsUIListCount(String textXpath,String ULxpath){
		WebElement we = findElement.searchUntilPresent(textXpath, elementTimeout);
		String text = we.getText().trim();
		int count = findElement.searchsUntilPresent(ULxpath, elementTimeout).size();
		assertion.assertEquals(Integer.parseInt(text), count);
	}
	
	
	
	
	
	
	
	

}
