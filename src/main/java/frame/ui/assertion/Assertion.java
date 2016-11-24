package frame.ui.assertion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.jdbc.TcSql;
import org.testng.Assert;

import frame.ui.element.BaseElement;

public class Assertion extends BaseElement {

	public static final Logger logger = LogManager.getLogger(Assertion.class);
//	public static int exeid = Integer.parseInt(System.getProperty("executeid"));
//	public static int caseid = Integer.parseInt(System.getProperty("caseid"));
//	public static int stepid = Integer.parseInt(System.getProperty("stepid"));

	public void info(String msg) {
		logger.info(msg);
		TcSql.updateDone("done", msg);
	}

	public void error(String msg) throws AssertionError {
		logger.error(msg);
		TcSql.updateDone("fail", msg);
		ss.saveScreenShot();
		Assert.fail(msg);
	}

	public void pass(String msg) {
		logger.info(msg);
		TcSql.updateDone("success", msg);
	}

	public void assertContains(String actual, String expect) {
		if (actual.contains(expect)) {
			logger.info(actual + " 包含 " + expect + " success");
			TcSql.updateDone("done", actual + " 包含 " + expect + " success");
		} else {
			error(actual + " 包含 " + expect + " fail");
		}
	}

	public void assertEquals(String actual, String expect) {
		if (actual.equals(expect)) {
			logger.info(actual + " 等于 " + expect + " success");
			TcSql.updateDone("done", actual + " 等于 " + expect + " success");
		} else {
			error(actual + " 不等于 " + expect + " fail");
		}
	}

	public void assertEquals(int actual, int expect) {
		if (actual == expect) {
			logger.info(actual + " 等于 " + expect + " success");
			TcSql.updateDone("done", actual + " 等于 " + expect + " success");
		} else {
			error(actual + " 不等于 " + expect + " fail");
		}
	}

	public static void assertViewExist(String xpath) {

	}

	public void assertTrue(Boolean condition) {
		if (condition) {
			TcSql.updateDone("success", "断言布尔表达式为真成功");
		} else {
			error("断言布尔表达式为真失败");
		}
	}

	public void assertFalse(Boolean condition) {
		if (condition) {
			error("断言布尔表达式为假失败");
		} else {
			TcSql.updateDone("success", "断言布尔表达式为假成功");
		}
	}
}
