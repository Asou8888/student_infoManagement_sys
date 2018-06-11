package Ctrl;

import DAO.BaseDAO;
import DAO.DAO;
import DAO.UserDAO;
import GUI.Regist_UI;
import GUI.AdministorRegist;
import GUI.StudentRegist;

import javax.swing.JOptionPane;

public class JoinInCheckCtrl {
	private String username;
	private char[] password;
	private Boolean authentication;
	public JoinInCheckCtrl(String _username, char[] _password, String _usertype) {
		this.username = _username;
		this.password = _password;
		if (_usertype.equals(Regist_UI.usertype[0])) {
			authentication = false; // not an administor
		} else {
			authentication = true; // an administor
		}
		init();
	}
	public void init() {
		if (!check_duplicate(this.username)) {
			JOptionPane.showMessageDialog(null, "Username Duplicated!");
			new Regist_UI();
		} else {
			if (this.authentication) {
				new AdministorRegist(this.username, this.password);
			} else {
				new StudentRegist(this.username, this.password);
			}
		}
	}
	private Boolean check_duplicate(String username) {
		UserDAO userDAO = (UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO);
		return userDAO.query_for_regist(username);
	}
}
