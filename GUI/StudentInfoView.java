package GUI;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import Actor.Academy;
import Actor.Gender;
import Actor.StudentInformation;
import DAO.BaseDAO;
import DAO.DAO;
import DAO.StudentDAO;

public class StudentInfoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2294441206199116899L;
	private JLabel name_label, student_number_label, gender_label, academy_label, major_label,
        native_place_label, email_label, phone_number_label;
    private JTextField name_input, student_number_input, major_input,
        native_place_input, email_input, phone_number_input;
    private JButton submit_button, cancel_button;
    private JComboBox<String> gender_select, academy_select;
    private String[] gender_obj = {Gender.male.toString(), Gender.female.toString()};
    private String[] academy_obj = {Academy.Business_Adminstration.toString(), Academy.Communication_and_Design.toString(),
    Academy.DataScience_and_Computing.toString(), Academy.Electronic_Engineering.toString(), Academy.PublicHealth_and_PreventiveMedicine.toString()};
    
    private Operation operation;
    private StudentInformation student;
    /* the construct function with no parameters,
     * will be called by AddStudentCtrl.
     */
    public StudentInfoView(Operation _operation) {
    	this.operation = _operation;
        init();
    }
    
    /* the construct function with parameters(reading from database),
     * will be called by EditInfoCtrl.
     */
    public StudentInfoView(Operation _operation, StudentInformation _student) {
    	// StudentInformation student = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).query_student(_student_number);
    	this.operation = _operation;
    	this.student = _student;
    	init();
    }
    
    public void init() {
    	if (this.operation.equals(Operation.Adding_Student)) {
    		this.setTitle("Adding Student Operation");
    	} else if (this.operation.equals(Operation.Edit_Student_Info)) {
    		this.setTitle("Student Information Editing");
    	}
		this.setLayout(new GridLayout(9, 2));
		/*  Label Initialize  */
		name_label = new JLabel("Name :");
		name_label.setHorizontalAlignment(SwingConstants.LEFT);
		student_number_label = new JLabel("Student Number: ");
		student_number_label.setHorizontalAlignment(SwingConstants.LEFT);
		
		gender_select = new JComboBox<String>(gender_obj);
		// gender_select.addItem(gender_obj);
		gender_select.setVisible(true);
		gender_label = new JLabel("Gender :");
		gender_label.setHorizontalAlignment(SwingConstants.LEFT);
		
		academy_select = new JComboBox<String>(academy_obj);
		// academy_select.addItem(academy_obj);
		academy_select.setVisible(true);
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
		name_input = new JTextField();
		student_number_input = new JTextField();
		major_input = new JTextField();
		native_place_input = new JTextField();
		email_input = new JTextField();
		phone_number_input = new JTextField();
		
		if (this.operation.equals(Operation.Edit_Student_Info)) {
			set_student_information(student);
		}
		
		/*  Button Initialize  */
		submit_button = new JButton("Submit");
		cancel_button = new JButton("cancel");
		submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*  1. Check input form
				 *  2. Confirm
				 *  3. Check duplicate
				 *  4. Update database
				 *  5. show MemForm
				 */
				// check empty
				Boolean empty = true, form_error = true;
				if (name_input.getText() == null || name_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "The name label cannot be blanked!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (student_number_input.getText() == null || student_number_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "The student number label cannot be blanked!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (major_input.getText() == null || major_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "The major label cannot be blanked!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (native_place_input.getText() == null || native_place_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "The native place label cannot be blanked!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					empty = false;
				}
				// if make sure there's no blank label, check input form
				if (!empty) {
					if (!StudentInformation.check_student_number(student_number_input.getText())) {
						JOptionPane.showMessageDialog(null, "The form of student number isn't correct!", "Error", JOptionPane.ERROR_MESSAGE);
						student_number_input.setText("");
					} else {
						form_error = false;
					}
				}
				// construct a new StudentInformation class to store the new input student information
				if (!empty && !form_error) {
					int i = JOptionPane.showConfirmDialog(null, "Are you sure the information is correct?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						if (operation.equals(Operation.Adding_Student)) {
							student = new StudentInformation(name_input.getText(), student_number_input.getText(),
									Gender.valueOf(gender_select.getSelectedItem().toString()), Academy.valueOf(academy_select.getSelectedItem().toString()), major_input.getText(), 
									native_place_input.getText(), phone_number_input.getText(), email_input.getText());
							if (((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).add(student)) {
								MemForm.Update();
								dispose();
								JOptionPane.showMessageDialog(null, "The new student has been added to database successfully!");
							} else {
								MemForm.Update();
								dispose();
								JOptionPane.showMessageDialog(null, "Adding to database error! Maybe the Student Number you have input is duplicated.");
							}
							// After dispose(), the UI will return to MemForm.
						} else if (operation.equals(Operation.Edit_Student_Info)) {
							student = new StudentInformation(name_input.getText(), student_number_input.getText(),
									Gender.valueOf(gender_select.getSelectedItem().toString()), Academy.valueOf(academy_select.getSelectedItem().toString()), major_input.getText(), 
									native_place_input.getText(), phone_number_input.getText(), email_input.getText());
							if (((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).update(student)) {
								dispose();
								MemForm.Update();
								JOptionPane.showMessageDialog(null, "The new student has been added to database successfully!");
							} else {
								dispose();
								JOptionPane.showMessageDialog(null, "Adding to database error! Maybe the Student Number you have input is duplicated.");
							}
						}
					}
				}
			}
		});
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*  1. confirm
				 *  2. show Memform
				 */
				int i = JOptionPane.showConfirmDialog(null, "Are you sure to cancel this operation?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		this.add(name_label);
		this.add(name_input);
		this.add(student_number_label);
		this.add(student_number_input);
		this.add(gender_label);
		this.add(gender_select);
		this.add(academy_label);
		this.add(academy_select);
		this.add(major_label);
		this.add(major_input);
		this.add(native_place_label);
		this.add(native_place_input);
		this.add(phone_number_label);
		this.add(phone_number_input);
		this.add(email_label);
		this.add(email_input);
		this.add(submit_button);
		this.add(cancel_button);
		
		// this.setContentPane(vbox);
		this.pack();
		this.setLocation(500, 300);
		// this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void set_student_information(StudentInformation student) {
    	name_input.setText(student.get_Name());
    	student_number_input.setText(student.Get_StudentNumber());
    	student_number_input.setEditable(false);
    	major_input.setText(student.Get_Major());
        native_place_input.setText(student.Get_NativePlace());
        email_input.setText(student.Get_Email());
        phone_number_input.setText(student.Get_PhoneNumber());
        gender_select.setSelectedItem(student.Get_Gender().toString());
        academy_select.setSelectedItem(student.Get_Academy().toString());
    }
    public static void main(String[] args) {
    	// new StudentInfoView();
    }
}
