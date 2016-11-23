package frame.ui.element;

import org.openqa.selenium.WebElement;

public class Attr extends BaseElement{

	public int getElementCount(String locator){
		return 0;
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
		//TcSql.updateDone("done", "获取元素文本"+text);
		return text;
	}
	
//	public String getText(String locator){
//		WebElement we= findElement.searchUntilPresent(locator, elementTimeout);
//		 String text = we.getText().trim();
//		return "";
//	}
	
}
