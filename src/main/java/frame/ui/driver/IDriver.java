package frame.ui.driver;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

public interface IDriver {
	WebDriver genDriver(HashMap<String,String> config);

}
