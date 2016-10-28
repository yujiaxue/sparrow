package frame.ui.assertion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.sun.xml.internal.ws.policy.AssertionSet;


public class Assertion {
	public static final Logger logger = LogManager.getLogger(Assertion.class);
	
	public static void info(String msg){
		logger.info(msg);
	}
	
	public static void error(String msg) throws AssertionError{
		logger.error(msg);
		Assert.fail(msg);
	}
	
	public static void assertContains(String actual,String expect){
		if(actual.contains(expect)){
			logger.info(actual+" 包含 " + expect +" success");
		}else{
			error(actual+" 包含 " + expect +" fail");
		}
	}
	public static void assertEquals(String actual,String expect){
		if(actual.equals(expect)){
			logger.info(actual + " 等于 " + expect + " success");
		}else{
			error(actual + " 不等于 " + expect + " fail");
		}
	}
	public static void assertEquals(int actual,int expect){
		if(actual == expect){
			logger.info(actual + " 等于 " + expect + " success");
		}else{
			error(actual + " 不等于 " + expect + " fail");
		}
	}
}
