package frame.ui.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import frame.ui.assertion.Assertion;


public class Selector  extends BaseElement{

	FindElement fe;
	Assertion assertion;
	public Selector(WebDriver rw) {
		super(rw);
		fe=new FindElement(rw);
		assertion = new Assertion(rw);
	}

	public void selectByVisibleText(String selectLocator, String optionText){
		WebElement element = fe.searchUntilPresent(selectLocator, elementTimeout);
		try {
			Select select = new Select(element);
			select.selectByVisibleText(optionText);
			assertion.info(selectLocator +"选择成功"+optionText );
		} catch (WebDriverException e) {
			assertion.error(selectLocator +"选择失败"+optionText +" ;"+e.getMessage());
		}
	}
	public void selectByIndex(String selectLocator,int index){
		WebElement element = fe.searchUntilPresent(selectLocator, elementTimeout);
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			assertion.info(selectLocator +"选择成功index="+index );
		} catch (WebDriverException e) {
			assertion.error(selectLocator +"选择失败index="+index +" ;"+e.getMessage());
		}
	}
	public void selectByValue(String selectLocator,String value){
		WebElement element = fe.searchUntilPresent(selectLocator, elementTimeout);
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			assertion.info(selectLocator +"选择成功value="+value );
		} catch (WebDriverException e) {
			assertion.error(selectLocator +"选择失败value="+value +" ;"+e.getMessage());
		}
	}
}
