package Actor;

public class AdminUser extends User {
	/*
	private String name;
	private String email;
	private String phone_number;
	public void Set_Name(String _name) {
	    this.name = _name;
	}
	public String get_Name() {
	    return this.name;
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
	*/
	public AdminUser() {
		init();
	}
	void init() {
		/*  read information from database
    	 *  Authentication should be initial to false
    	 */
    	Set_Authentication(true);
	}
}
