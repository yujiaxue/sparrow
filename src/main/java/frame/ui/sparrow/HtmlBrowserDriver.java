package frame.ui.sparrow;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlBrowserDriver {
	public static void main(String[] args) {
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.get("http://home.51cto.com/home");
		System.out.println(driver.getPageSource());
	}
	
}
