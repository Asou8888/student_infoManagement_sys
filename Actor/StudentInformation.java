package Actor;

/*  class StudentInformation:
 *     members are the keys that storing in student_information database.
 *     extended by class StudentUser.
 *     which could also be used to exchange data among the system and database.
 */
public class StudentInformation {
	private String name;
	private String student_number;
	private Gender gender;
	private Academy academy;
	private String major;
	private String native_place;
	private String email;
	private String phone_number;
	public StudentInformation(String _name, String _student_number, Gender _gender, Academy _academy,
			String _major, String _native_place, String _email, String _phone_number) {
		if (_email == null || _email.equals("")) {
			this.email = "";
		} else {
			this.email = _email;
		}
		if (_phone_number == null || _phone_number.equals("")) {
			this.phone_number = "";
		} else {
			this.phone_number = _phone_number;
		}
		this.name = _name;
		this.student_number = _student_number;
		this.gender = _gender;
		this.academy = _academy;
		this.major = _major;
		this.native_place = _native_place;
	}
	public StudentInformation() {}
	public void Set_Name(String _name) {
	    this.name = _name;
	}
	public String get_Name() {
	    return this.name;
	}
	public void Set_StudentNumber(String _student_number) {
	    this.student_number = _student_number;
	}
	public String Get_StudentNumber() {
	    return this.student_number;
	}
	public void Set_Gender(Gender _gender) {
	    this.gender = _gender;
	}
	public Gender Get_Gender() {
	    return this.gender;
	}
	public void Set_Academy(Academy _academy) {
	    this.academy = _academy;
	}
	public Academy Get_Academy() {
	    return this.academy;
	}
	public void Set_Major(String _major) {
	    this.major = _major;
	}
	public String Get_Major() {
	    return this.major;
	}
	public void Set_NativePlace(String _native_place) {
	    this.native_place = _native_place;
	}
	public String Get_NativePlace() {
	    return this.native_place;
	}
	public void Set_Email(String _email) {
	    this.email = _email;
	}
	public String Get_Email() {
	    return this.email;
	}
	public void Set_PhoneNumber(String _phone_number) {
	    this.phone_number = _phone_number;
	}
	public String Get_PhoneNumber() {
	    return this.phone_number;
	}
}
