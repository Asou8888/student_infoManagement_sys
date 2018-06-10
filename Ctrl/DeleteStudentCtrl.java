package Ctrl;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

import Actor.StudentInformation;
import DAO.BaseDAO;
import DAO.StudentDAO;
import DAO.DAO;
import GUI.MemForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStudentCtrl extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7712228683103660878L;
	private JLabel student_number_label;
	private JTextField student_number_input;
	private JPanel student_number_panel, button_panel;
	private JButton delete_button, cancel_button;
	public DeleteStudentCtrl() {
		init();
	}
	void init() {
		this.setTitle("Deleting Student Operation");
		this.setLayout(new GridLayout(2, 1));
		student_number_label = new JLabel("Student Number :");
		student_number_label.setHorizontalAlignment(SwingConstants.LEFT);
		student_number_input = new JTextField(30);
		student_number_panel = new JPanel();
		student_number_panel.add(student_number_label);
		student_number_panel.add(student_number_input);
		
		delete_button = new JButton("Delete");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/* delete student usecase:
				 *     1. user inputs student number, and confirms.
				 *     2. check form of the input student_numer.
				 *     3. util query the student information in database.
				 *     4. give feedback information.
				 *     5. inform user the operation result.
				 *     6. update MemForm.
				 */
				Boolean empty = true, form_error = true;
				if (student_number_input.getText() == null || student_number_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Student Number cannot be empty!", "error", JOptionPane.ERROR_MESSAGE);
				} else {
					empty = false;
				}
				if (!empty) {
					if (!StudentInformation.check_student_number(student_number_input.getText())) {
						JOptionPane.showMessageDialog(null, "The form of Student Number isn't correct!", "Error", JOptionPane.ERROR_MESSAGE);
						student_number_input.setText("");
					} else {
						form_error = false;
					}
				}
				if (!empty && !form_error) {
					int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						if (((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).delete(student_number_input.getText())) {
							MemForm.Update();
							dispose();
							JOptionPane.showMessageDialog(null, "The delete operation succeeded!");
						} else {
							dispose();
							JOptionPane.showMessageDialog(null, "The student doesn't exist!");
						}
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
				}
			}
		});
		button_panel = new JPanel();
		button_panel.add(delete_button);
		button_panel.add(cancel_button);
		this.add(student_number_panel);
		this.add(button_panel);
		this.pack();
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new DeleteStudentCtrl();
	}
}
