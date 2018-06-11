package GUI;
import java.util.regex.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Ctrl.JoinInCheckCtrl;

import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.Box;
import javax.swing.JComboBox;

public class Regist_UI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1159477541107564244L;
	private JButton regist_button, exit_button;
	private JLabel username_label, password_label, usertype_label;
	private JTextField username_input;
	private JPasswordField password_input;
	private JComboBox<String> usertype_chosen;
	public static String[] usertype = {"Student", "Administor"};
	public Regist_UI() {
		init();
	}
	private void init() {
		regist_button = new JButton("Regist");
		exit_button = new JButton("Exit");
		
		username_label = new JLabel("Your Username: ");
		password_label = new JLabel("Your Password: ");
		username_label.setHorizontalAlignment(SwingConstants.RIGHT);
		password_label.setHorizontalAlignment(SwingConstants.RIGHT);
		usertype_label = new JLabel("User Type: ");
		usertype_label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		username_input = new JTextField(20); // maximum length limit: 20
		password_input = new JPasswordField(20); // maximum length limit: 20
		
		usertype_chosen = new JComboBox<String>(usertype);
		usertype_chosen.setVisible(true);
		
		Box vbox = Box.createVerticalBox();
		JPanel jp1 = new JPanel();
		jp1.add(username_label);
		jp1.add(username_input);
		JPanel jp2 = new JPanel();
		jp2.add(password_label);
		jp2.add(password_input);
		JPanel jp3 = new JPanel();
		jp3.add(usertype_label);
		jp3.add(usertype_chosen);
		JPanel jp4 = new JPanel();
		jp4.add(regist_button);
		jp4.add(exit_button);
		vbox.add(jp1);
		vbox.add(jp2);
		vbox.add(jp3);
		vbox.add(jp4);
		regist_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Boolean empty = true, form_error = true;
				if (username_input.getText().equals("") || username_input.getText() == null) {
					JOptionPane.showMessageDialog(null, "The username cannot be empty!");
					username_input.setText("");
				} else if (String.valueOf(password_input.getPassword()).equals("")|| password_input.getPassword() == null) {
					JOptionPane.showMessageDialog(null, "The password cannot be empty!");
					password_input.setText("");
				} else {
					empty = false;
				}
				if (!empty) {
					if (!check_username_form(username_input.getText())) {
						JOptionPane.showMessageDialog(null, "The Form of username isn't correct.\nLength should between 6 to 20."
								+ "\nOnly Numbers, English Characters, and \'_\' are available.");
						username_input.setText("");
					} else if (!check_password_form(String.valueOf(password_input.getPassword()))) {
						JOptionPane.showMessageDialog(null, "The form of password isn't correct.\nLength should between 8-20."
								+ "\nOnly Numbers and English Characters are available");
						password_input.setText("");
					} else {
						form_error = false;
					}
				}
				if (!empty && !form_error) {
					new JoinInCheckCtrl(username_input.getText(), password_input.getPassword(), usertype_chosen.getSelectedItem().toString());
					dispose();
				}
			}
		});
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					dispose();
					new Login_UI();
					// new MemForm();
				}
			}
		});
		// regist_button.addActionListener(this);
		// exit_button.addActionListener(this);
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
		String regex = "^[A-Za-z][A-Za-z0-9_-]{6,19}$";
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
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Regist_UI();
	}
}
