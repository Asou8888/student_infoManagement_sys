package GUI;
import java.util.regex.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import DAO.BaseDAO;
import GUI.MemForm;

import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.Box;
import DAO.UserDAO;
import DAO.DAO;

public class Regist_UI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 2L;
	private JButton regist_button, exit_button;
	private JLabel username_label, password_label;
	private JTextField username_input;
	private JPasswordField password_input;
	public Regist_UI() {
		init();
	}
	private void init() {
		regist_button = new JButton("Regist");
		exit_button = new JButton("Exit");
		username_label = new JLabel("Your Username: ");
		password_label = new JLabel("Your Password: ");
		username_input = new JTextField(20); // maximum length limit: 20
		password_input = new JPasswordField(20); // maximum length limit: 20
		username_label.setHorizontalAlignment(SwingConstants.RIGHT);
		password_label.setHorizontalAlignment(SwingConstants.RIGHT);
		Box vbox = Box.createVerticalBox();
		JPanel jp1 = new JPanel();
		jp1.add(username_label);
		jp1.add(username_input);
		JPanel jp2 = new JPanel();
		jp2.add(password_label);
		jp2.add(password_input);
		JPanel jp3 = new JPanel();
		jp3.add(regist_button);
		jp3.add(exit_button);
		vbox.add(jp1);
		vbox.add(jp2);
		vbox.add(jp3);
		regist_button.addActionListener(this);
		exit_button.addActionListener(this);
		this.setContentPane(vbox);
		this.setTitle("Registing");
		this.setLocation(500, 300);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// check the form of input username
	private Boolean check_username_form(String username) {
		String regex = "^[A-Za-z][A-Za-z0-9_-]{0,19}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);
		return matcher.matches();
	}
	// check the form of input password
	private Boolean check_password_form(String password) {
		String regex = "[A-Za-z0-9]{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	private Boolean check_duplicate(String username) {
		UserDAO userDAO = (UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO);
		return userDAO.query_for_regist(username);
		/*try {
			// 1. Using Class.forName() method to load Driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("SQL Driver load success!");
			String url = "jdbc:mysql://localhost:3306/test"; // JDBC's URL
			String rootname = "root";
			String pwd = "root";
			// 2. Build up link
			// Use DriverManager.getConnection() to avail a Connection object
			Connection conn = null;
			
			try {
				// link database
				conn = DriverManager.getConnection(url, rootname, pwd);
				System.out.println("Linking to database sucess");
				Boolean empty;
				// Update SQL
				String sql = "select username from user_information where username = " + username;
				// create PreparedStatement Object
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					empty = false;
				} else {
					empty = true;
				}
				rs.close();
				pstmt.close();
				conn.close();
				return empty;
			} catch(SQLException exception) {
				exception.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
			return false;
		}*/
	}
	// Not written yet, the user information class
	private Boolean insert_data(String username, String password) {
		UserDAO userDAO = (UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO);
		return userDAO.update_for_regist(username, password);
		/*try {
			// 1. Using Class.forName() method to load Driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("SQL Driver load success!");
			String url = "jdbc:mysql://localhost:3306/test"; // JDBC's URL
			String rootname = "root";
			String pwd = "root";
			// 2. Build up link
			// Use DriverManager.getConnection() to avail a Connection object
			Connection conn = null;
			
			try {
				// link database
				conn = DriverManager.getConnection(url, rootname, pwd);
				System.out.println("Linking to database sucess");
				// Update SQL
				String sql = "insert into user_information values(" + username + ',' + password + ")";
				// create PreparedStatement Object
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				conn.close();
			} catch(SQLException exception) {
				exception.printStackTrace();
			}
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		*/
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exit_button) {
			int i = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else if (event.getSource() == regist_button) {
			/* 1. check the form of input user name and password
			 * 2. check duplicate, to find if the user name existed
			 * 3. Update database
			 */
			if (!check_username_form(username_input.getText())) {
				JOptionPane.showConfirmDialog(null, "Formula of Username doesn't satisfied requirement!");
				username_input.setText("");
				password_input.setText("");
			} else if (!check_password_form(String.valueOf(password_input.getPassword()))) {
				JOptionPane.showConfirmDialog(null, "Formula of Password doesn't satisfied requirement!\npassword requirement: 8 to 20 digits or characters");
				password_input.setText("");
			}
			if (!check_duplicate(username_input.getText())) {
				/* if the following consequences have been satisfied:
				 *     1. new username and password satisfied system's requirement.
				 *     2. no duplicate username in database
				 * then running insert_data() to insert the new user's data to database.
				 * otherwise,
				 * confirm that the username is existed.
				 */
				if (insert_data(username_input.getText(), String.valueOf(password_input.getPassword()))) {
					dispose();
					/* showing the registing success confirm,
					 * and perform the MemForm UI.
					 */
					JOptionPane.showConfirmDialog(null, "Regist Success! Welcome to Student Information System!");
					new MemForm();
				} else {
					/* if inserting failure,
					 * confirm 'connecting to database error'.
					 */
					JOptionPane.showConfirmDialog(null, "Connection to database error! Please try again!");
				}
				System.exit(0);
			} else {
				JOptionPane.showConfirmDialog(null, "This Username is existed! Please try another one!");
				username_input.setText("");
				password_input.setText("");
			}
		}
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Regist_UI();
	}
}
