package frame.ui.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class Alert extends BaseElement {
	WebDriver rw;

	Logger logger = LogManager.getLogger();


	public void dismiss() {
		try {
			rw.switchTo().alert().dismiss();
			logger.info("取消弹出框");
		} catch (WebDriverException e) {
			logger.info("取消弹出框错误:" + e.getMessage());
			assertion.error("取消弹出框错误:" + e.getMessage());
		}
	}

	public void accept() {
		try {
			rw.switchTo().alert().accept();
			logger.info("确认弹出框");
		} catch (WebDriverException e) {
			logger.info("确认弹出框错误:" + e.getMessage());
			assertion.error("确认弹出框错误:" + e.getMessage());
		}
	}

	public void sendkeys(String text) {
		try {
			rw.switchTo().alert().sendKeys(text);
			logger.info("输入" + text + "至弹出框成功");
		} catch (WebDriverException e) {
			logger.info("输入" + text + "至弹出框失败:" + e.getMessage());
			assertion.error("输入" + text + "至弹出框失败:" + e.getMessage());
		}
	}

	public String getText() {
		String text ="";
		try{
		 text = rw.switchTo().alert().getText();
		 logger.info("获取alert文本成功"+text);
		}catch(WebDriverException e){
			logger.info("获取alert文本失败:"+e.getMessage());
			assertion.error("获取alert文本失败:"+e.getMessage());
		}
		return text;
	}

}
