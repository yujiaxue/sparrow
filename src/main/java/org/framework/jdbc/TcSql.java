package org.framework.jdbc;

public class TcSql {

	/**
	 * 更新执行记录
	 * 
	 * @param status
	 * @param exeid
	 * @param caseid
	 * @param stepid
	 */
	public static void updateStatus(String status, int exeid, int caseid, int stepid, String log) {
		JdbcFactory jf = JdbcFactory.getConn();
		jf.update("update execution set status=?,log=? where executeid=? and caseid=? and stepid=?", status, log, exeid,
				caseid,stepid);
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

	public static void updateDone(String status,String log) {
		updateStatus(status, Integer.parseInt(System.getProperty("executeid")),
				Integer.parseInt(System.getProperty("caseid")), Integer.parseInt(System.getProperty("stepid")), log);
	}
}
