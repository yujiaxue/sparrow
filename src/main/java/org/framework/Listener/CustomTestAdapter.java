package org.framework.Listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomTestAdapter extends TestListenerAdapter{

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		//System.out.println("testName is " + tr.getName() + " method is "+ tr.getMethod());
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);
		System.out.println("当前运行的用例是   。。。。"+testContext.getName());
	}
	
	
	
}
