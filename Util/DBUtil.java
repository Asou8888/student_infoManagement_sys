package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static DBUtil db;
	private Connection conn;
	private PreparedStatement prestate;
	private ResultSet rs;
	private DBUtil() {}
	public static DBUtil getDBUtil() {
		if (db == null) {
			db = new DBUtil();
		}
		return db;
	}
	public Connection get_connection() {
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				/*  test is a name of database table  */
				String url = "jdbc:mysql://localhost:3306/RUNOOB"; // JDBC's URL
				String rootname = "root";
				String pwd = "123456";
				conn = DriverManager.getConnection(url, rootname, pwd);
			}
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return conn;
	}
	public int execute_update(String sql) {
		int result = -1;
		if (get_connection() == null) {
			return result;
		}
		try {
			prestate = conn.prepareStatement(sql);
			result = prestate.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return result;
	}
	public int excute_update(String sql, Object[] obj) {
		int result = -1;
		if (get_connection() == null) {
			return result;
		}
		try {
			prestate = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				prestate.setObject(i + 1, obj[i]);
			}
			result = prestate.executeUpdate();
			close();
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
		return result;
	}
	public ResultSet execute_query(String sql) {
		if (get_connection() == null) {
			return null;
		}
		try {
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rs;
	}
	public ResultSet execute_query(String sql, Object[] obj) {
		if (get_connection() == null) {
			return null;
		}
		try {
			prestate = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				prestate.setObject(i + 1, obj[i]);
			}
			rs = prestate.executeQuery();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rs;
	}
	public void close() {
		try {
			if (rs != null) rs.close();
			if (prestate != null) prestate.close();
			if (conn != null) conn.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DBUtil dbutil = new DBUtil();
		// System.out.printf("%d", dbutil.get_connection());
	}
}
