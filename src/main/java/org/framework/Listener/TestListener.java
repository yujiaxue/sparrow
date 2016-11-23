package org.framework.Listener;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import frame.ui.report.LogParse;

public class TestListener implements ITestListener {

	Logger logger = LogManager.getLogger(TestListener.class);
	private long startMillions;
	private long endMillions;
	private long testStartMillions;
	private long testEndMillions;
	
	@Override
	public void onFinish(ITestContext arg0) {
		endMillions = System.currentTimeMillis();
		long totalMillions = endMillions - startMillions;
		int timeCost = (int) (totalMillions / 1000 / 60);
		//logger.info("onFinish----"+timeCost +"----分钟");
		//newThread();
	}

	

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		float time = calTestMillison();
		//logger.info(arg0.getName()+ "失败 time cost is "+ time + "秒钟");
		//log("onTestEnd----"+arg0.getTestClass().getName() + "----"+ arg0.getName()+"----"+arg0.getMethod().getDescription()+"----failed----"+time+"----"+"秒钟");

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		float time = calTestMillison();
		//logger.info(arg0.getName()+ "跳过 time cost is "+ time + "秒钟");
		log("onTestEnd----"+arg0.getTestClass().getName() + "----"+ arg0.getName()+"----"+arg0.getMethod().getDescription()+"----skipped----"+time+"----"+"秒钟");

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Object[] a = arg0.getParameters();
		testStartMillions = System.currentTimeMillis();
		//logger.info("onTestStart----"+arg0.getTestClass().getName() + "----"+ arg0.getName()+"----"+arg0.getMethod().getDescription()+"----"+a[0]);
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		float time = calTestMillison();
		//log("onTestEnd----"+arg0.getTestClass().getName() + "----"+ arg0.getName()+"----"+arg0.getMethod().getDescription()+"----success----"+time+"----"+"秒钟");
		//logger.info(arg0.getName()+ "成功 time cost is "+ time + "秒钟");
		
	}
	public void log(String a){
		logger.info(a);
	}
	private void newThread(){
		LogThread lt = new LogThread();
		lt.start();
	}

	public float calTestMillison(){
		testEndMillions = System.currentTimeMillis();
		long testTotalMillions = testEndMillions- testStartMillions;
		double testMillions = testTotalMillions / 1000.0 ;
		BigDecimal bd = new BigDecimal((double)testMillions); 
		bd = bd.setScale(1,4); 
		return bd.floatValue();
	}

	@Override
	public void onStart(ITestContext arg0) {
		startMillions = System.currentTimeMillis();
		//logger.info("-----onstart--"+arg0.getName()+"--------------");
	}

	
}
class LogThread extends Thread {
	public LogThread(){
		
	}
	
	public void run() {
		LogParse.parseLog();
	}

	
}
