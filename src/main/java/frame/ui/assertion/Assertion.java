package frame.ui.assertion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class Assertion {
	public static final Logger logger = LogManager.getLogger(Assertion.class);
	
	public static void info(String msg){
		logger.info(msg);
	}
	
	public static void error(String msg) throws AssertionError{
		logger.error(msg);
		Assert.fail(msg);
	}
}
