package frame.ui.element;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class Selector  extends BaseElement{


	public void selectByVisibleText(String selectLocator, String optionText){
		WebElement element = findElement.searchUntilPresent(selectLocator, elementTimeout);
		try {
			Select select = new Select(element);
			select.selectByVisibleText(optionText);
			assertion.info(selectLocator +"选择成功"+optionText );
		} catch (WebDriverException e) {
			assertion.error(selectLocator +"选择失败"+optionText +" ;"+e.getMessage());
		}
	}
	public void selectByIndex(String selectLocator,int index){
		WebElement element = findElement.searchUntilPresent(selectLocator, elementTimeout);
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			assertion.info(selectLocator +"选择成功index="+index );
		} catch (WebDriverException e) {
			assertion.error(selectLocator +"选择失败index="+index +" ;"+e.getMessage());
		}
	}
	public void selectByValue(String selectLocator,String value){
		WebElement element = findElement.searchUntilPresent(selectLocator, elementTimeout);
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			assertion.info(selectLocator +"选择成功value="+value );
		} catch (WebDriverException e) {
			assertion.error(selectLocator +"选择失败value="+value +" ;"+e.getMessage());
		}
	}
}
