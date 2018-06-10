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
import DAO.BaseDAO;
import DAO.DAO;
import DAO.StudentDAO;

public class QueryStudentView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3373834696400400311L;
	private JLabel name_label, student_number_label, gender_label, academy_label;
    private JTextField name_input, student_number_input;
    private JButton query_button, cancel_button;
    private JComboBox<String> gender_select, academy_select;
    private String[] gender_obj = {"None", Gender.male.toString(), Gender.female.toString()};
    private String[] academy_obj = {"None", Academy.Business_Adminstration.toString(), Academy.Communication_and_Design.toString(),
        Academy.DataScience_and_Computing.toString(), Academy.Electronic_Engineering.toString(), Academy.PublicHealth_and_PreventiveMedicine.toString()};
    public QueryStudentView() {
    	init();
    }
    public void init() {
    	this.setTitle("Query Student Operation");
    	this.setLayout(new GridLayout(5, 2));
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
		/*  TextField Initialize  */
		name_input = new JTextField();
		student_number_input = new JTextField();
		/*  Button Initialize  */
		query_button = new JButton("Submit");
		cancel_button = new JButton("cancel");
		
		/*  Button Action  */
		query_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String query_condition = "";
				String param = "";
				if (!(student_number_input.getText().equals("") || student_number_input.getText() == null)) {
					if (query_condition.equals("") || query_condition == null) query_condition += "student_number=?";
					else query_condition += " and student_number=?";
					if (param.equals("") || param == null) param += student_number_input.getText();
					else param += "," + student_number_input.getText();
				}
				if (!(name_input.getText().equals("") || name_input.getText() == null)) {
					if (query_condition.equals("") || query_condition == null) query_condition += "name=?";
					else query_condition += " and name=?";
					if (param.equals("") || param == null) param += name_input.getText();
					else param += "," + name_input.getText();
				}
				if (!gender_select.getSelectedItem().equals("None")) {
					if (query_condition.equals("") || query_condition == null) query_condition += "gender=?";
					else query_condition += " and gender=?";
					if (param.equals("") || param == null) param += gender_select.getSelectedItem().toString();
					else param += "," + gender_select.getSelectedItem().toString();
				}
				if (!academy_select.getSelectedItem().equals("None")) {
					if (query_condition.equals("") || query_condition == null) query_condition += "academy=?";
					else query_condition += " and academy=?";
					if (param.equals("") || param == null) param += academy_select.getSelectedItem().toString();
					else param += "," + academy_select.getSelectedItem().toString();
				}
				MemForm.CurrPageNum = 1;
				MemForm.result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(MemForm.CurrPageNum, query_condition, param);
				MemForm.initJTable(MemForm.information_table, MemForm.result);
				dispose();
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
					// new MemForm();
				}
			}
		});
		
		this.add(student_number_label);
		this.add(student_number_input);
		this.add(name_label);
		this.add(name_input);
		this.add(gender_label);
		this.add(gender_select);
		this.add(academy_label);
		this.add(academy_select);
		this.add(query_button);
		this.add(cancel_button);
		
		this.pack();
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
