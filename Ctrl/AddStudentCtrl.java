package Ctrl;
import GUI.StudentInfoView;

/*
 *  written by Asou,
 *  2018/06/05
 */
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
public class AddStudentCtrl {
	public AddStudentCtrl() {
		init();
	}
	void init() {
		new StudentInfoView();
	}
	public static void main(String[] args) {
		new AddStudentCtrl();
	}
}
