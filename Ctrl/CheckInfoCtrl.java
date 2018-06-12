package Ctrl;
import GUI.MemForm;
import GUI.InfoForm;
import Actor.StudentInformation;
import javax.swing.JOptionPane;
import DAO.*;

public class CheckInfoCtrl {
    public CheckInfoCtrl() {
    	init();
    }
    public void init() {
    	StudentInformation student = query_personal_info(MemForm.get_username());
    	if (student != null) {
    		/* showing personal information frame  */
    		new InfoForm(student);
    	} else {
    		/* showing error information  */
    		JOptionPane.showMessageDialog(null, "No such student's information in database", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    private StudentInformation query_personal_info(String username) {
    	String student_number = ((UserDAO)BaseDAO.get_ability_DAO(DAO.UserDAO)).query_for_student_number(username);
    	StudentInformation student = null;
    	if (!(student_number == null || student_number.equals(""))) {
    		student = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).query_student(student_number);
    	}
    	return student;
    }
}
