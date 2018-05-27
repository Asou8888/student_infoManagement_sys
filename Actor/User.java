package Actor;
import java.lang.String;
import java.lang.Boolean;
import java.util.Date;

public abstract class User {
    private String username;
    private char[] password;
    /* Authentication:
     * True: Administrator
     * False: Student User
     */
    private Boolean Authentication;
    private Date Regist_Date;
    /* other information:
     * Real Name, type String
     * Phone Number, type char[11]
     * Address, type String
     * Detail, type String
     */
    public String Get_Username() {
    	return this.username;
    }
    public void Set_Username(String _username) {
    	this.username = _username;
    }
    protected char[] Get_Password() {
    	return this.password;
    }
    protected void Set_Password(char[] _password) {
    	this.password = _password;
    }
    protected Boolean Get_Authentication() {
    	return this.Authentication;
    }
    protected void Set_Authentication(Boolean _auth) {
    	this.Authentication = _auth;
    }
    public Date Get_Regist_Date() {
    	return this.Regist_Date;
    }
    public void Set_Regust_Date(Date _date) {
    	this.Regist_Date = _date;
    }
}
