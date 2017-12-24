package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Student;
import util.DBUtil;
import util.DatabaseException;

public class StudentDBHelper extends Driver {

	public ArrayList<Student> getAllStudents() throws SQLException {
		this.openConnection();

		this.setRs(DBUtil.getAllFromTable("student", this.getConn(), this.getPstm()));
		;

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_id = ?"));
		;
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentBySurname(String surname) throws SQLException {
		String newSurname = "%" + surname + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_surname like ?"));
		this.getPstm().setString(1, newSurname);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByNameAndSurname(String name, String surname) throws SQLException {
		String newName = "%" + name + "%";
		String newSurname = "%" + surname + "%";
		this.openConnection();
		this.setPstm(
				this.getConn().prepareStatement("select * from student where student_name like ? and student_surname like ?"));
		this.getPstm().setString(1, newName);
		this.getPstm().setString(2, newSurname);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByBirthDate(Date birthDate) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_birthdate = ?"));
		this.getPstm().setDate(1, birthDate);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByBeginningYear(int beginningYear) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_beginningYear = ?"));
		this.getPstm().setInt(1, beginningYear);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByCreditsNumber(int creditsNumber) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_creditsNumber = ?"));
		;
		this.getPstm().setInt(1, creditsNumber);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByScholarshipType(String scholarshipType) throws SQLException {
		String newScholarship = "%" + scholarshipType + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_scholarshipType like ?"));
		this.getPstm().setString(1, newScholarship);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByDept(boolean debt) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_debtor = ?"));
		this.getPstm().setBoolean(1, debt);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByGroup(int group) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_group = ?"));
		this.getPstm().setInt(1, group);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> createStudentsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Student> students = new ArrayList<Student>();
		while (rs.next()) {
			students.add(new Student(rs.getInt("student_id"), rs.getString("student_name"), rs.getString("student_surname"),
					rs.getDate("student_birthdate"), rs.getInt("student_group"), rs.getString("student_subgroup"),
					rs.getInt("student_beginningYear"), rs.getInt("student_currentYear"), rs.getString("student_scholarshipType"),
					rs.getBoolean("student_debtor"), rs.getInt("student_creditsNumber")));
		}

		return students;
	}

	public int insert(Student student) throws SQLException, DatabaseException {
		this.openConnection();
		student.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement("insert into students.student(student_name, student_surname, student_birthdate, student_group, student_subgroup, student_beginningYear, student_currentYear, student_scholarshipType, student_debtor, student_creditsNumber) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"));
		this.getPstm().setString(1, student.getName());
		this.getPstm().setString(2, student.getSurname());
		this.getPstm().setDate(3, student.getBirthDate());
		this.getPstm().setInt(4, student.getGroup());
		this.getPstm().setString(5, student.getSubgroup());
		this.getPstm().setInt(6, student.getBeginningYear());
		this.getPstm().setInt(7, student.getCurrentYear());
		this.getPstm().setString(8, student.getScholarshipType());
		this.getPstm().setBoolean(9, student.isDebtor());
		this.getPstm().setInt(10, student.getCreditsNumber());
		int inserted = this.getPstm().executeUpdate();

		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.student where student_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

}
