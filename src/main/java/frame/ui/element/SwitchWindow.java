package frame.ui.element;

import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwitchWindow extends BaseElement{

	Logger logger = LogManager.getLogger(SwitchWindow.class);
	
	public SwitchWindow(RemoteWebDriver rw) {
		super(rw);
	}
	/**
	 * 切换至第 number个 窗口
	 * @param number
	 */
	public void switchWindow(int number){
		while(true){
			int count = rw.getWindowHandles().size();
			if(number > count){
				logger.error("没有该window");
				Assert.fail("没有该window");
			}else{
				rw.switchTo().window(rw.getWindowHandles().toArray()[number-1].toString());
				break;
			}
		}
	}
	/**
	 * 切换至指定名称的tab
	 * @param windowHandle
	 */
	public void switchWindow(String windowHandle){
		logger.info("切换至窗口"+windowHandle);
		rw.switchTo().window(windowHandle);
	}
	/**
	 * 获取当前窗口
	 * @return
	 */
	public String getWindowHandle(){
		logger.info("获取当前窗口句柄");
		return rw.getWindowHandle();
	}

	
}
