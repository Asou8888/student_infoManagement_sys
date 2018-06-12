package DAO;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {
	private static UserDAO userDAO = null;
	// return an instance of class UserDAO
	public static synchronized UserDAO getInstance() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}
	// Query for login
	public Boolean query_for_login(String username, String password) {
		Boolean result = false;
		if (username.length() == 0 || password.length() == 0) return false;
		String sql = "select * from user_information where username=? and password=?";
		String[] param = {username, password};
		// rs is the result set from BaseDAO
		rs = db.execute_query(sql, param);
		try {
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
	public Boolean query_for_regist(String username) {
		Boolean result = true;
		String sql = "select * from user_information where username=?";
		String[] param = {username};
		rs = db.execute_query(sql, param);
		try {
			if (rs.next()) {
				result = false;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
	/*  not sure if it's right  */
	public Boolean update_for_regist(String username, String password, Boolean authentication) {
		String sql = "insert into user_information(username, password, authentication) values(?, ?, ?)";
		Object[] param = {username, password, authentication};
		int result = db.excute_update(sql, param);
		if (result < 0) {
			return false;
		} else {
			return true;
		}
	}
	public Boolean query_for_authentication(String username) {
		Boolean result = false;
		String sql = "select authentication from user_information where username=?";
		String[] param = {username};
		rs = db.execute_query(sql, param);
		try {
			if (rs.next()) result = rs.getBoolean("authentication");
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
	public String query_for_student_number(String username) {
		String sql = "select student_number from user_student_link where username=?";
		Object[] param = {username};
		String student_number = null;
		rs = db.execute_query(sql, param);
		try {
			if (rs.next()) {
				student_number = rs.getString("student_number");
			} else {
				student_number = "";
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			destroy();
		}
		return student_number;
	}
	public Boolean update_for_linking(String username, String student_number) {
		String sql = "insert into user_student_link(username, student_number) values(?,?)";
		Object[] param = {username, student_number};
		int result = db.excute_update(sql, param);
		if (result < 0) {
			return false;
		} else {
			return true;
		}
	}
	public static void main(String[] args) {
		new UserDAO();
	}
}
