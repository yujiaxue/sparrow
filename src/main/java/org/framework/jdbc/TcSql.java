package org.framework.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frame.ui.facade.Browser;

public class TcSql {

	private static final Logger logger = LogManager.getLogger(Browser.class);

	/**
	 * 更新执行记录
	 * 
	 * @param status
	 * @param exeid
	 * @param caseid
	 * @param stepid
	 */
	public static void updateStatus(String status, int exeid, int caseid, int stepid, String log) {
		try {
			JdbcFactory jf = JdbcFactory.getConn();
			jf.update("update execution set status=?,log=? where executeid=? and caseid=? and stepid=?", status, log,
					exeid, caseid, stepid);
		} catch (Exception e) {
			logger.info("updateStatus .." + e.getMessage().toString());
		}
	}

	/**
	 * 更新Task执行记录
	 * 
	 * @param status
	 * @param taskid
	 */
	public static void updateTaskStatus(String status, int taskid) {
		JdbcFactory jf = JdbcFactory.getConn();
		jf.updateTask("update tasks set status=? where id=?", status, taskid);
	}
	public static void updateExcute(int exeid,int passnum,int failnum,int taskid,int excutetime,String status){
		JdbcFactory jf = JdbcFactory.getConn();
		jf.updateExcute("update excute set successcase=?,failcase=?,excutetime=?,status=? where taskid=? and id=?", 
				 passnum, failnum,taskid,status,exeid ,excutetime);
	}

	public static void updateDone(String status, String log) {
		updateStatus(status, Integer.parseInt(System.getProperty("executeid")),
				Integer.parseInt(System.getProperty("caseid")), Integer.parseInt(System.getProperty("stepid")), log);
	}

	public static void updateImgFile(String file) {
		try {
			JdbcFactory jf = JdbcFactory.getConn();
			jf.updateImg("update execution set imageurl=? where executeid=? and caseid=? and stepid=?", file,
					Integer.parseInt(System.getProperty("executeid")), Integer.parseInt(System.getProperty("caseid")),
					Integer.parseInt(System.getProperty("stepid")));
		} catch (Exception e) {
			logger.info("updateImgFile .." + e.getMessage().toString());
		}
	}
}
