package GUI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Actor.StudentInformation;

import java.awt.GridLayout;

public class InfoForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8855882689459233739L;
	private StudentInformation student;
	private JLabel student_number_label, name_label, gender_label, academy_label, major_label, 
	        native_place_label, phone_number_label, email_label;
	private JTextField student_number, name, gender, academy, major, 
    native_place, phone_number, email;
	public InfoForm(StudentInformation _student) {
		this.student = _student;
		init();
	}
	public void init() {
		this.setTitle("Personal Information");
		this.setLayout(new GridLayout(8, 2));
		student_number_label = new JLabel("Student Number :");
		name_label = new JLabel("Name :");
		gender_label = new JLabel("Gender :");
		academy_label = new JLabel("Academy :");
		major_label = new JLabel("Major :");
		native_place_label = new JLabel("Native Place :");
		phone_number_label = new JLabel("Phone Number :");
		email_label = new JLabel("Email :");
		
		student_number = new JTextField(student.Get_StudentNumber());
		student_number.setEditable(false);
		name = new JTextField(student.get_Name());
		name.setEditable(false);
		gender = new JTextField(student.Get_Gender().toString());
		gender.setEditable(false);
		academy = new JTextField(student.Get_Academy().toString());
		academy.setEditable(false);
		major = new JTextField(student.Get_Major());
		major.setEditable(false);
		native_place = new JTextField(student.Get_NativePlace());
		native_place.setEditable(false);
		phone_number = new JTextField(student.Get_PhoneNumber());
		phone_number.setEditable(false);
		email = new JTextField(student.Get_Email());
		email.setEditable(false);
		
		this.add(student_number_label);
		this.add(student_number);
		this.add(name_label);
		this.add(name);
		this.add(gender_label);
		this.add(gender);
		this.add(academy_label);
		this.add(academy);
		this.add(major_label);
		this.add(major);
		this.add(native_place_label);
		this.add(native_place);
		this.add(phone_number_label);
		this.add(phone_number);
		this.add(email_label);
		this.add(email);
		
		this.pack();
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
