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
			int row = stmt.executeUpdate();
			logger.info("db操作更新记录 " + row + "行");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					conn.close();
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
			int row = stmt.executeUpdate();
			logger.info("db操作更新记录 " + row + "行");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}