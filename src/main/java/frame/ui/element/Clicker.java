package frame.ui.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.jdbc.TcSql;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Clicker extends BaseElement{
	Logger logger = LogManager.getLogger(Clicker.class);
	
	private WebElement button = null;

	public boolean clickable(String xpath){
		WebDriverWait wd = new WebDriverWait(rw,elementTimeout);
		button = wd.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		if(null == button){
			logger.error("元素不可点击locator="+xpath);
			return false;
		}
		logger.info("元素可点击locator＝"+xpath);
		return true;
	}
	
	public void clickByLocator(String xpath){
		if(clickable(xpath)){
			button.click();
			logger.info("点击元素 locator="+xpath+"成功");
			TcSql.updateDone("done", "点击元素 locator="+xpath+"成功");
		}else{
			logger.error("点击元素 locator="+xpath+"发生异常");
			TcSql.updateDone("fail", "点击元素 locator="+xpath+"发生异常");
		}
	}
	public void clickOnText(String text){
		WebElement we = findElement.searchUntilPresent("//*[contains(text(),'" + text + "')]", elementTimeout);
		try{
		we.click();
		TcSql.updateDone("done", "点击元素:"+text+":成功");
		}catch(Exception e){
			TcSql.updateDone("done", "点击元素:"+text+":失败，异常:"+e.getMessage());
		}
	}
}
