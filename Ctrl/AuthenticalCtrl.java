package Ctrl;
import DAO.UserDAO;
import DAO.BaseDAO;
import DAO.DAO;
import Actor.User;
import Actor.AdminUser;
import Actor.StudentUser;

public class AuthenticalCtrl {
	public static User get_ability_user(String username) {
		User user;
		if (check_authentical(username)) {
			user = new AdminUser();
			user.Set_Username(username);
		} else {
			user = new StudentUser();
			user.Set_Username(username);
		}
		return user;
	}
	public static Boolean check_authentical(String username) {
		return ((UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO)).query_for_authentication(username);
	}
}
