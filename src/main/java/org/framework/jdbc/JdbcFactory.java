package org.framework.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcFactory {

	Logger logger = LogManager.getLogger(JdbcFactory.class);

	public static JdbcFactory jf = new JdbcFactory();
	public String driver = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://10.7.243.110:3306/UIAUTO?characterEncoding=utf-8";
	public String username = "root";
	public String password = "123456";
	public Connection conn = null;
	public PreparedStatement stmt = null;

	public static JdbcFactory getConn() {
		return jf;
	}

	public JdbcFactory() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Insert(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String sql, String status,String log, int exeid, int caseid, int stepid) {
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setInt(3, exeid);
			stmt.setInt(4, caseid);
			stmt.setInt(5, stepid);
			stmt.setString(2, log);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updateTask(String sql, String status, int taskid) {
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setInt(2, taskid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void updateImg(String sql, String imgfile, int exeid, int caseid, int stepid) {
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, imgfile);
			stmt.setInt(2, exeid);
			stmt.setInt(3, caseid);
			stmt.setInt(4, stepid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void updateExcute(String sql,int passnum,int failnum,int taskid,String status,int excuid,int excutetime){
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, passnum);
			stmt.setInt(2, failnum);
			stmt.setInt(3, excutetime);
			stmt.setString(4, status);
			stmt.setInt(5, taskid);
			stmt.setInt(6, excuid);
			stmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		jf.updateExcute("update excute set successcase=?,failcase=?,excutetime=?,status=? where taskid=? and id=?", 
				1, 3, 8, "finish", 19, 113);
	}
}