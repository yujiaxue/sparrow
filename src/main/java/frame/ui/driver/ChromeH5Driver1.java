package frame.ui.driver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeH5Driver1 {

	public static WebDriver genDriver(){
		
		 ChromeDriverService chromeServer = new ChromeDriverService.Builder().usingDriverExecutable(new File("tools/chromedriver")).usingAnyFreePort().build();
		  try {
		   chromeServer.start();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 

		  // 以iPad模式启动Chrome，并访问网易门户
		  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		  capabilities.setCapability("chrome.switches", Arrays.asList("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1"));


		  WebDriver wb = new RemoteWebDriver(chromeServer.getUrl(), capabilities);
		  wb.get("https://m.iwjw.com");

		  try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		  return wb;
		  //chromeServer.stop();
		 }
	
	public static void main(String[] args) {
		genDriver();
	}
}
