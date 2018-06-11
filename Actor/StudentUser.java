package Actor;

public class StudentUser extends User {
	private StudentInformation student_information;
	public StudentUser() {
		init();
	}
    public StudentUser(String _name, String _student_number, Gender _gender, 
    		Academy _academy, String _major, String _native_place, String _email, String _phone_number) {
    	student_information = new StudentInformation();
    	filling_student_information(_name, _student_number, _gender, 
        		_academy, _major, _native_place, _email, _phone_number);
    }
    public StudentUser(StudentInformation _student_information) {
    	this.student_information = _student_information;
    }
    private void init() {
    	/*  read information from database
    	 *  Authentication should be initial to false
    	 */
    	student_information = new StudentInformation();
    	Set_Authentication(false);
    }
    public StudentInformation get_student_information() {
    	return student_information;
    }
    private void filling_student_information(String _name, String _student_number, Gender _gender, 
    		Academy _academy, String _major, String _native_place, String _email, String _phone_number) {
    	if (_email == null || _email.equals("")) {
    		student_information.Set_Email("");
    	} else {
        	student_information.Set_Email(_email);
    	}
    	if (_phone_number == null || _phone_number.equals("")) {
    		student_information.Set_PhoneNumber("");
    	} else {
        	student_information.Set_PhoneNumber(_phone_number);
    	}
    	student_information.Set_Academy(_academy);
    	student_information.Set_Gender(_gender);
    	student_information.Set_Major(_major);
    	student_information.Set_Name(_name);
    	student_information.Set_NativePlace(_native_place);
    	student_information.Set_StudentNumber(_student_number);
    }
}
