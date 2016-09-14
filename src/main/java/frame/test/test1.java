package frame.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.framework.image.GenerateGif;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.madgag.gif.fmsware.AnimatedGifEncoder;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import frame.tools.file.IniFile;
import frame.ui.driver.RemoteChromeDriver;

public class test1 {
	WebDriver webdriver;

	@BeforeClass
	public void init(){
		RemoteChromeDriver d = new RemoteChromeDriver();
		IniFile ini = new IniFile();
		HashMap<String,String> a = ini.getConfig();
		webdriver = d.genDriver(a);
	}
	@Test
	public void oo() throws IOException{
		GenerateGif gg = new GenerateGif();
		
		webdriver.get("http://www.baidu.com");
		File image = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File("1.png"));
		BufferedImage src1 = ImageIO.read(new File("1.png"));
		long start = System.currentTimeMillis();
		WebElement ele = null ;
		try{
		 ele = webdriver.findElement(By.id("kw"));
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			long end = System.currentTimeMillis();
			System.out.print((end - start) / 1000 / 60);
			System.out.println(" 分钟");
		}
		
		ele.sendKeys("荷兰");
		image = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File("2.png"));
        BufferedImage src2 = ImageIO.read(new File("2.png"));
        webdriver.findElement(By.xpath("//div[@id='content_left']/div[1]/div[1]/h3/a")).click();
        image = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File("3.png"));
        BufferedImage src3 = ImageIO.read(new File("3.png"));
        
        //AnimatedGifEncoder e = new AnimatedGifEncoder(); 
//        e.setRepeat(0); 
//        e.start("baidu.gif"); 
//        e.setDelay(300); // 1 frame per sec 
//        e.addFrame(src1); 
//        e.setDelay(100); 
//        e.addFrame(src2); 
//        e.setDelay(100); 
//      e.addFrame(src3); 
//        e.finish();
	}
}
