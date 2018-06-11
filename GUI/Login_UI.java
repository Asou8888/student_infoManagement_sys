package GUI;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.Box;

import DAO.BaseDAO;
import DAO.UserDAO;
import DAO.DAO;

public class Login_UI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton login_button, exit_button;
	private JLabel username_label, password_label;
	private JTextField username_input;
	private JPasswordField password_input;
	// initialize UI
	public Login_UI() {
		init();
	}
	private void init() {
		login_button = new JButton("Log In");
		exit_button = new JButton("Exit");
		username_label = new JLabel("Username: "); // Start with character, consist of characters, digits and _, -
		password_label = new JLabel("Password: "); // 8-20 digits or characters
		username_input = new JTextField(20); // maximum length limit: 20
		password_input = new JPasswordField(20); // maximum length limit: 20
		// password_input.addKeyListener(new LoginListener());
		username_label.setHorizontalAlignment(SwingConstants.LEFT);
		password_label.setHorizontalAlignment(SwingConstants.LEFT);
		Box vbox = Box.createVerticalBox();
		JPanel jp1 = new JPanel();
		jp1.add(username_label);
		jp1.add(username_input);
		JPanel jp2 = new JPanel();
		jp2.add(password_label);
		jp2.add(password_input);
		JPanel jp3 = new JPanel();
		jp3.add(login_button);
		jp3.add(exit_button);
		vbox.add(jp1);
		vbox.add(jp2);
		vbox.add(jp3);
		login_button.addActionListener(this);
		/*login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				check_login_information(username_input.getText(), String.valueOf(password_input.getPassword()));
			}
		});
		*/
		exit_button.addActionListener(this);
		/*
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		*/
		this.setContentPane(vbox);
		this.setTitle("Log In");
		this.setLocation(500, 300);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*private class LoginListener() extends KeyAdapter {
		public void KeyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_ENTER) {
				check_login_information(username_input.getText(), String.valueOf(password_input.getPassword()));
			}
		}
	}
	*/
	private Boolean check_login_information() {
		UserDAO userDAO = (UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO);
		if (userDAO.query_for_login(username_input.getText(), String.valueOf(password_input.getPassword()))) {
			return true;
		} else {
			return false;
		}
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exit_button) {
			int i = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else if (event.getSource() == login_button) {
			// search the username in database, and check the password
			/*
			if (username_input.getText().equals("admin") && String.valueOf(password_input.getPassword()).equals("admin")) {
				JOptionPane.showConfirmDialog(null, "Log in Success! Welcome to Student Information System!");
				System.exit(0);
			} else {
				JOptionPane.showConfirmDialog(null, "Username or Password error! Please try again!");
				username_input.setText("");
				password_input.setText("");
			}
			*/
			if (check_login_information()) {
				dispose();
				/* show MemForm
				 * save the login information
				 */
				new MemForm(username_input.getText());
				JOptionPane.showMessageDialog(null,  "Login Sucessfully! Welcome to Student Information System!");
			} else {
				JOptionPane.showConfirmDialog(null, "Username or Password error! Please try again!");
				username_input.setText("");
				password_input.setText("");
			}
		}
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Login_UI();
	}
}
