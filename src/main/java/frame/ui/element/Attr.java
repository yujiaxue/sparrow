package frame.ui.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Attr extends BaseElement{

	public Attr(WebDriver rw) {
		super(rw);
	}

	public int getElementCount(String locator){
		return 0;
	}
	
//	public String getText(String locator){
//		WebElement we= findElement.searchUntilPresent(locator, elementTimeout);
//		 String text = we.getText().trim();
//		return "";
//	}
	
}
