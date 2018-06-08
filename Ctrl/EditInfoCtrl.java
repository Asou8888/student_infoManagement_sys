package Ctrl;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.MemForm;
import GUI.StudentInfoView;
import Actor.StudentInformation;
import DAO.BaseDAO;
import DAO.DAO;
import DAO.StudentDAO;

public class EditInfoCtrl extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5160151503465999622L;
	private StudentInformation student;
	private JTextField student_number_input;
	private JLabel student_number_label;
	private JPanel student_number_panel, button_panel;
	private JButton edit_button, cancel_button;
	
	public EditInfoCtrl() {
    	init();
    }
    void init() {
    	this.setTitle("Editing Student Information");
    	this.setLayout(new GridLayout(2, 1));
		student_number_label = new JLabel("Student Number :");
		student_number_label.setHorizontalAlignment(SwingConstants.LEFT);
		student_number_input = new JTextField(30);
		student_number_panel = new JPanel();
		student_number_panel.add(student_number_label);
		student_number_panel.add(student_number_input);
		
		edit_button = new JButton("Edit");
		edit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Boolean empty = true, form_error = true;
				if (student_number_input.getText().equals("") || student_number_input.getText() == null) {
					JOptionPane.showMessageDialog(null, "Student Number cannot be empty!", "error", JOptionPane.ERROR_MESSAGE);
				} else {
					empty = false;
				}
				if (!empty) {
					if (!StudentInformation.check_student_number(student_number_input.getText())) {
						JOptionPane.showMessageDialog(null, "The form of Student Number isn't correct!", "Error", JOptionPane.ERROR_MESSAGE);
						student_number_input.setText("");
					} else {
						form_error = true;
					}
				}
				if (!empty && !form_error) {
					student = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).query_student(student_number_input.getText());
					if (student == null) {
						JOptionPane.showMessageDialog(null, "The student doesn't exist!");
						dispose();
					} else {
						dispose();
						new StudentInfoView(student);
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
					// dispose();
					dispose();
					new MemForm();
				}
			}
		});
		button_panel = new JPanel();
		button_panel.add(edit_button);
		button_panel.add(cancel_button);
		this.add(student_number_panel);
		this.add(button_panel);
		this.pack();
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
    	new EditInfoCtrl();
    }
}
