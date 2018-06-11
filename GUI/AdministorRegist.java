package GUI;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.BaseDAO;
import DAO.DAO;
import DAO.UserDAO;

public class AdministorRegist extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6995831053250271611L;
	private JLabel keyword_label;
	private JTextField keyword_input;
	private JPanel keyword_panel, button_panel;
	private JButton submit_button, cancel_button;
	private static String KEYWORD = "12345678";
	public AdministorRegist(String _username, char[] _password) {
		init(_username, _password);
	}
	void init(String username, char[] password) {
		this.setTitle("Administor Registing");
		this.setLayout(new GridLayout(2, 1));
		keyword_label = new JLabel("Keyword :");
		keyword_label.setHorizontalAlignment(SwingConstants.LEFT);
		keyword_input = new JTextField(30);
		keyword_panel = new JPanel();
		keyword_panel.add(keyword_label);
		keyword_panel.add(keyword_input);
		
		submit_button = new JButton("Submit");
		submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (keyword_input.getText().equals(KEYWORD)) {
					if (insert_data(username, password, true)) {
						dispose();
						JOptionPane.showMessageDialog(null, "Regist Passed!");
						new MemForm();
					} else {
						dispose();
						JOptionPane.showMessageDialog(null, "Database Linking Failure!", "Error", JOptionPane.ERROR_MESSAGE);
						new Regist_UI();
					}
				}
			}
		});
		cancel_button = new JButton("Cancel");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*  1. confirm
				 *  2. show Memform
				 */
				int i = JOptionPane.showConfirmDialog(null, "Are you sure to cancel this operation?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					/* first dispose the confirm dialog,
					 * then dispose the AddStudentCtrl
					 */
					dispose();
					new Regist_UI();
				}
			}
		});
		button_panel = new JPanel();
		button_panel.add(submit_button);
		button_panel.add(cancel_button);
		this.add(keyword_panel);
		this.add(button_panel);
		this.pack();
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	private Boolean insert_data(String username, char[] password, Boolean authentication) {
		UserDAO userDAO = (UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO);
		return userDAO.update_for_regist(username, String.valueOf(password), authentication);
	}
	public static void main(String[] args) {
		// new AdministorRegist();
	}
}
