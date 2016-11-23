package org.framework.image;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.jdbc.TcSql;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import frame.ui.element.BaseElement;

public class ScreenShot extends BaseElement{

	Logger logger = LogManager.getLogger(ScreenShot.class);
	
	public ScreenShot(WebDriver rw) {
		super(rw);
	}

	public void screenShotAsFile(){
		
	}
	
	/**
	 * 截屏功能 返回图片名称
	 */
	public void saveScreenShot() {
		String fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".png";
		String picPath = config.get("logPath");
		File f = new File(picPath);
		if (!f.exists()) {
			f.mkdir();
		}
		String file = picPath.concat(fileName);
		File imgfile = ((TakesScreenshot) rw).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(imgfile, new File(file));
			TcSql.updateImgFile(file);
		} catch (IOException e) {
			logger.info(e.getMessage().toString());
		}
	}
}
