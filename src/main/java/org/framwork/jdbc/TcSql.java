package org.framwork.jdbc;

public class TcSql {
	
	/**
	 * 更新执行记录
	 * @param exeid
	 * @param caseid
	 * @param stepid
	 */
	public static void updateStatus(int exeid,int caseid,int stepid){
		JdbcFactory jf = JdbcFactory.getConn();
		String status = "done";
		jf.update("update execution set status=? where executeid=? and caseid=? and stepid=?",status,exeid,caseid,stepid);
	}
}
