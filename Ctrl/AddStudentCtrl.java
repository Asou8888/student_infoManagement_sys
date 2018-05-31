package Ctrl;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Actor.Academy;
import Actor.Gender;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * class StudentInformation {
	private String name;
	private String student_number;
	private Gender gender;
	private Academy academy;
	private String major;
	private String native_place;
	private String email;
	private String phone_number;
 */
public class AddStudentCtrl extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6487332240609699170L;
	private JPanel name_panel, student_number_panel, gender_panel, academy_panel, major_panel,
	               native_place_panel, email_panel, phone_number_panel, button_panel;
	private JLabel name_label, student_number_label, gender_label, academy_label, major_label,
	               native_place_label, email_label, phone_number_label;
	private JTextField name_input, student_number_input, gender_input, academy_input, major_input,
	                   native_place_input, email_input, phone_number_input;
	private JButton add_button, cancel_button;
	private Box vbox;
	public AddStudentCtrl() {
		init();
	}
	void init() {
		this.setTitle("Adding Student Operation");
		this.setLayout(new GridLayout());
		/*  Label Initialize  */
		name_label = new JLabel("Name :");
		name_label.setHorizontalAlignment(SwingConstants.LEFT);
		student_number_label = new JLabel("Student Number: ");
		student_number_label.setHorizontalAlignment(SwingConstants.LEFT);
		gender_label = new JLabel("Gender :");
		gender_label.setHorizontalAlignment(SwingConstants.LEFT);
		academy_label = new JLabel("Academy :");
		academy_label.setHorizontalAlignment(SwingConstants.LEFT);
		major_label = new JLabel("Major :");
		major_label.setHorizontalAlignment(SwingConstants.LEFT);
		native_place_label = new JLabel("Native Place :");
		native_place_label.setHorizontalAlignment(SwingConstants.LEFT);
		email_label = new JLabel("Email :");
		email_label.setHorizontalAlignment(SwingConstants.LEFT);
		phone_number_label = new JLabel("Phone Number :");
		phone_number_label.setHorizontalAlignment(SwingConstants.LEFT);
		
		/*  TextField Initialize  */
		name_input = new JTextField(30);
		student_number_input = new JTextField(30);
		gender_input = new JTextField(30);
		academy_input = new JTextField(30);
		major_input = new JTextField(30);
		native_place_input = new JTextField(30);
		email_input = new JTextField(30);
		phone_number_input = new JTextField(30);
		
		/*  Panel Initialize  */
		name_panel = new JPanel();
		name_panel.add(name_label);
		name_panel.add(name_input);
		
		student_number_panel = new JPanel();
		student_number_panel.add(student_number_label);
		student_number_panel.add(student_number_input);
		
		gender_panel = new JPanel();
		gender_panel.add(gender_label);
		gender_panel.add(gender_input);
		
		academy_panel = new JPanel();
		academy_panel.add(academy_label);
		academy_panel.add(academy_input);
		
		major_panel = new JPanel();
		major_panel.add(major_label);
		major_panel.add(major_input);
		
		native_place_panel = new JPanel();
		native_place_panel.add(native_place_label);
		native_place_panel.add(native_place_input);
		
		email_panel = new JPanel();
		email_panel.add(email_label);
		email_panel.add(email_input);
		
		phone_number_panel = new JPanel();
		phone_number_panel.add(phone_number_label);
		phone_number_panel.add(phone_number_input);
		
		button_panel = new JPanel();
		
		/*  Button Initialize  */
		add_button = new JButton("Add");
		cancel_button = new JButton("cancel");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*  1. Check input form
				 *  2. Confirm
				 *  3. Check duplicate
				 *  4. Update database
				 *  5. show MemForm
				 */
			}
		});
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*  1. confirm
				 *  2. show Memform
				 */
			}
		});
		button_panel.add(add_button);
		button_panel.add(cancel_button);
		
		/*  Box Initialize  */
		vbox = Box.createVerticalBox();
		vbox.add(name_panel);
		vbox.add(student_number_panel);
		vbox.add(gender_panel);
		vbox.add(academy_panel);
		vbox.add(major_panel);
		vbox.add(native_place_panel);
		vbox.add(email_panel);
		vbox.add(phone_number_panel);
		vbox.add(button_panel);
		
		this.setContentPane(vbox);
		this.pack();
		this.setLocation(500, 300);
		// this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new AddStudentCtrl();
	}
}
