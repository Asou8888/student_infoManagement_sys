package DAO;
import Util.DBUtil;
import java.sql.SQLException;
import java.sql.ResultSet;

public abstract class BaseDAO {
	protected final DBUtil db = DBUtil.getDBUtil();
	protected ResultSet rs;
	private static BaseDAO baseDAO;
	public BaseDAO() {
		init();
	}
	private void init() {
		/* getDAO(), get DAO instance from userDAO()
		 * DAO types: StudentDAO, AdministorDAO
		 * or only one type UserDAO 
		 */
	}
	public static synchronized BaseDAO get_ability_DAO() {
		if (baseDAO == null) {
			baseDAO = UserDAO.getInstance();
		}
		return baseDAO;
	}
	protected void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			/* Once an exception has been thrown out,
			 * the system will shut down with,
			 * the connection and statement of database is still running.
			 * A 'finally' makes sure when the system shut off abnormally,
			 * the connection to the database would still be closed.
			 */
			db.close();
		}
	}
}
