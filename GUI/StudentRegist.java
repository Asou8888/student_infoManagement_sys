package GUI;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.BaseDAO;
import DAO.DAO;
import DAO.UserDAO;
import Actor.StudentInformation;
import DAO.StudentDAO;
// import DAO.StudentDAO;
// import Actor.StudentInformation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StudentRegist extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 484910954645174742L;
	private JLabel student_number_label, inform_label;
	private JTextField student_number_input;
	private JPanel student_number_panel, button_panel;
	private JButton submit_button, cancel_button;
	public StudentRegist(String _username, char[] _password) {
		init(_username, _password);
	}
	public void init(String username, char[] password) {
		this.setTitle("Student User Registing");
		this.setLayout(new GridLayout(3, 1));
		inform_label = new JLabel("If you are a student, please input your student number in the following textfield.");
		student_number_label = new JLabel("Student Number :");
		student_number_label.setHorizontalAlignment(SwingConstants.LEFT);
		student_number_input = new JTextField(30);
	    student_number_panel = new JPanel();
		student_number_panel.add(student_number_label);
		student_number_panel.add(student_number_input);
		
		submit_button = new JButton("Submit");
		submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (insert_data(username, password, false)) {
					dispose();
					new MemForm(username);
					if (!(student_number_input.getText().equals("") || student_number_input.getText() == null)) {
						StudentInformation student = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).query_student(student_number_input.getText());
						if (student == null) {
							JOptionPane.showMessageDialog(null, "No such student found!");
						} else {
							if (((UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO)).update_for_linking(username, student_number_input.getText())) {
								JOptionPane.showMessageDialog(null, "Build link between user and student succeeded!");
							} else {
								JOptionPane.showMessageDialog(null, "Database link error!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Regist Passed!");
				} else {
					dispose();
					JOptionPane.showMessageDialog(null, "Database Linking Failure!", "Error", JOptionPane.ERROR_MESSAGE);
					new Regist_UI();
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
		this.add(inform_label);
		this.add(student_number_panel);
		this.add(button_panel);
		this.pack();
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private Boolean insert_data(String username, char[] password, Boolean authentication) {
		UserDAO userDAO = (UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO);
		return userDAO.update_for_regist(username, String.valueOf(password), authentication);
	}
}
