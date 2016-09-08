package org.framework.Listener;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

public class MyRetry implements IRetryAnalyzer{

	private int retryCount = 1;
	private static int retryMaxCount = 3;
	
	
	public boolean retry(ITestResult result) {
		
		Logger logger = Logger.getLogger(MyRetry.class);
		logger.error("计数器数字是 。。。"+ retryCount);
		if(retryCount < retryMaxCount){
			logger.info(result.getTestClass().getName() + " retry count is "+ retryCount);
			System.out.println(result.getTestClass().getName() + " retry count is "+ retryCount);
			Reporter.setCurrentTestResult(result);
			retryCount++;
			return true;
		}
		return false;
	}
	
	

}
