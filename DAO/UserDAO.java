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
	public Boolean update_for_regist(String username, String password) {
		String sql = "insert into user_information(username, password) values(?, ?)";
		String[] param = {username, password};
		int result = db.excute_update(sql, param);
		if (result < 0) {
			return false;
		} else {
			return true;
		}
	}
}
