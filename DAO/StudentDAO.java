package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;

import Actor.Academy;
import Actor.Gender;
import Actor.StudentInformation;
import java.util.List;
import java.util.ArrayList;

public class StudentDAO extends BaseDAO {
	private static StudentDAO studentDAO = null;
	private final int fieldNum = 9;
	private final int showNum = 15;
	public static synchronized StudentDAO getInstance() {
		if (studentDAO == null) {
			studentDAO = new StudentDAO();
		}
		return studentDAO;
	}
	/* query by student number,
	 * 1. checking the form of student number, 
	 * 2. do querying.
	 */
	private Boolean query_by_studentNumber(String student_number) throws SQLException {
		Boolean result = false;
		if (student_number.equals("") || student_number == null) {
			return result;
		}
		String sql = "select * from student_information where student_number=?";
		String[] param = {student_number};
		rs = db.execute_query(sql, param);
		if (rs.next()) {
			result = true;
		}
		return result;
	}
	// update
	public Boolean update(StudentInformation student) {
		Boolean result = false;
		if (student == null) {
			return result;
		}
		try {
			// check duplicate
			if (!query_by_studentNumber(student.Get_StudentNumber())) {
				return result;
			}
			/* update, table name: student_information
			 * storing the basic information of a student,
			 * which is including in class StudentUser.
			 */
			String sql = "update student_information set academy=?, email=?, gender=?, major=?, name=?, native_place=?, phone_number=?, student_number=?";
			String param[] = {student.Get_Academy().toString(), student.Get_Email(), student.Get_Gender().toString(), student.get_Name(), student.Get_NativePlace(), student.Get_PhoneNumber(), student.Get_StudentNumber()};
			int rowCount = db.excute_update(sql, param);
			if (rowCount == 1) result = true;
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
	// delete
	public Boolean delete(StudentInformation student) {
		Boolean result = false;
		if (student == null) {
			return result;
		}
		String sql = "select * from student_information where student_number=?";
		String[] param = {student.Get_StudentNumber()};
		int rowCount = db.excute_update(sql, param);
		if (rowCount == 1) result = true;
		destroy();
		return result;
	}
	// add
	public Boolean add(StudentInformation student) {
		Boolean result = false;
		if (student == null) {
			return result;
		}
		try {
			if (query_by_studentNumber(student.Get_StudentNumber())) {
				return result;
			}
			String sql = "insert into student_information(academy, email, gender, major, name, native_place, phone_number, student_number) values(?,?,?,?,?,?,?,?)";
			String[] param = {student.Get_Academy().toString(), student.Get_Email(), student.Get_Gender().toString(), student.Get_Major(), student.get_Name(), student.Get_NativePlace(), student.Get_PhoneNumber(), student.Get_StudentNumber()};
			if (db.excute_update(sql, param) == 1) {
				result = true;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
	// query, perform the students information in database on the Center Panel of MemForm
	public String[][] list(int pageNum) {
		String[][] result = null;
		if (pageNum < 1) {
			return result;
		}
		List<StudentInformation> students = new ArrayList<StudentInformation>();
		int beginNum = (pageNum - 1) * showNum;
		String sql = "select * from student_information limit ?, ?";
		Integer[] param = {beginNum, showNum};
		rs = db.execute_query(sql, param);
		try {
			while (rs.next()) {
				//  add rs records to the list
				build_student_information_list(rs, students);
			}
			if (students.size() > 0) {
				result = new String[students.size()][fieldNum];
				for (int j = 0; j < students.size(); j++) {
					// add list element into String[][]
					build_student_information_result(result, students, j);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
	private void build_student_information_list(ResultSet rs, List<StudentInformation> student_list) throws SQLException {
		StudentInformation student = new StudentInformation();
		student.Set_Name(rs.getString("name"));
		student.Set_StudentNumber(rs.getString("student_number"));
		student.Set_Gender(Gender.valueOf("gender"));
		student.Set_Academy(Academy.valueOf(rs.getString("academy")));
		student.Set_Major(rs.getString("major"));
		student.Set_NativePlace(rs.getString("native_place"));
		student.Set_Email(rs.getString("email"));
		student.Set_PhoneNumber("phone_number");
		student_list.add(student);
	}
	private void build_student_information_result(String[][] result, List<StudentInformation> student_list, int j) throws SQLException {
		StudentInformation student = student_list.get(j);
		result[j][0] = student.Get_StudentNumber();
		result[j][1] = student.get_Name();
		result[j][2] = student.Get_Gender().toString();
		result[j][3] = student.Get_Academy().toString();
		result[j][4] = student.Get_Major();
		result[j][5] = student.Get_NativePlace();
		result[j][6] = student.Get_PhoneNumber();
		result[j][7] = student.Get_Email();
	}
}
