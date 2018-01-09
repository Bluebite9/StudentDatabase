package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Student;
import util.Util;
import util.DatabaseException;

public class StudentDBHelper extends Driver {

	public ArrayList<Student> getAllStudents() throws SQLException {
		this.openConnection();

		this.setRs(Util.getAllFromTable("student", this.getConn(), this.getPstm()));
		;

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentById(String id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_id = ?"));
		;
		this.getPstm().setString(1, id);
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

	public ArrayList<Student> getStudentByBirthDate(String birthDate) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_birthdate = ?"));
		this.getPstm().setString(1, birthDate);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByBeginningYear(String beginningYear) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_beginningYear = ?"));
		this.getPstm().setString(1, beginningYear);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByCurrentYear(String currentYear) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_currentYear = ?"));
		this.getPstm().setString(1, currentYear);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByCreditsNumber(String creditsNumber) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_creditsNumber = ?"));
		;
		this.getPstm().setString(1, creditsNumber);
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

	public ArrayList<Student> getStudentByDept(String debt) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_debtor = ?"));
		this.getPstm().setString(1, debt);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByGroup(String group) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from student where student_group = ?"));
		this.getPstm().setString(1, group);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByGroupName(String group) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from student join students.group on group_id = student_group where group_name like ?"));
		this.getPstm().setString(1, group);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentBySpecialization(String specialization) throws SQLException {
		String newSpecialization = "%" + specialization + "%";
		this.openConnection();
		this.setPstm(this.getConn()
				.prepareStatement("select * from students.student " + "join students.group on student_group = group_id "
						+ "join students.specialization on specialization_id = group_specialization "
						+ "where specialization_name like ?;"));
		this.getPstm().setString(1, newSpecialization);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByDomain(String domain) throws SQLException {
		String newDomain = "%" + domain + "%";
		this.openConnection();
		this.setPstm(this.getConn()
				.prepareStatement("select * from students.student join students.group on student_group = group_id "
						+ "join students.specialization on specialization_id = group_specialization join students.domain on domain_id = specialization_domain "
						+ "where domain_name like ?;"));
		this.getPstm().setString(1, newDomain);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Student> getStudentByFaculty(String faculty) throws SQLException {
		String newFaculty = "%" + faculty + "%";
		this.openConnection();
		this.setPstm(this.getConn()
				.prepareStatement("select * from students.student join students.group on student_group = group_id "
						+ "join students.specialization on specialization_id = group_specialization join students.domain on domain_id = specialization_domain join students.faculty on faculty_id = domain_faculty"
						+ "where specialization_name like ?;"));
		this.getPstm().setString(1, newFaculty);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public Student getLastStudentRow() throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from students.student order by student_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Student> students = this.createStudentsFromResultSet(this.getRs());
		this.closeAll();

		return students.get(0);
	}

	public ArrayList<Student> createStudentsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Student> students = new ArrayList<Student>();
		while (rs.next()) {
			students.add(new Student(rs.getInt("student_id"), rs.getString("student_name"), rs.getString("student_surname"),
					rs.getString("student_birthdate"), rs.getInt("student_group"), rs.getString("student_subgroup"),
					rs.getInt("student_beginningYear"), rs.getInt("student_currentYear"), rs.getString("student_scholarshipType"),
					rs.getBoolean("student_debtor"), rs.getInt("student_creditsNumber")));
		}

		return students;
	}

	public int insert(Student student) throws SQLException, DatabaseException {
		this.openConnection();
		student.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement(
				"insert into students.student(student_name, student_surname, student_birthdate, student_group, student_subgroup, student_beginningYear, student_currentYear, student_scholarshipType, student_debtor, student_creditsNumber) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"));
		this.getPstm().setString(1, student.getName());
		this.getPstm().setString(2, student.getSurname());
		this.getPstm().setString(3, student.getBirthDate());
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

	public int update(Student student) throws SQLException, DatabaseException {
		this.openConnection();
		student.validate(getConn(), getPstm(), getRs());

		this.setPstm(this.getConn().prepareStatement(
				"update students.student set student_name = ?, student_surname = ?, student_birthdate = ?, student_group = ?, student_subgroup = ?, student_beginningYear = ?, student_currentYear = ?, student_scholarshipType = ?, student_debtor = ?, student_creditsNumber = ?, student_active = ? where student_id = ?"));
		this.getPstm().setString(1, student.getName());
		this.getPstm().setString(2, student.getSurname());
		this.getPstm().setString(3, student.getBirthDate());
		this.getPstm().setInt(4, student.getGroup());
		this.getPstm().setString(5, student.getSubgroup());
		this.getPstm().setInt(6, student.getBeginningYear());
		this.getPstm().setInt(7, student.getCurrentYear());
		this.getPstm().setString(8, student.getScholarshipType());
		this.getPstm().setBoolean(9, student.isDebtor());
		this.getPstm().setInt(10, student.getCreditsNumber());
		this.getPstm().setBoolean(11, student.isActive());
		this.getPstm().setInt(12, student.getId());
		int update = this.getPstm().executeUpdate();

		return update;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.student where student_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

	public ArrayList<Student> find(String name, String surname, String birthDate, String credits, String debtor,
			String scholarshipType, String beginningYear, String currentYear, String specialization, String domain,
			String faculty, String group, String active) throws SQLException {
		ArrayList<Student> studentName = new ArrayList<>();
		ArrayList<Student> studentSurname = new ArrayList<>();
		ArrayList<Student> studentBirthdate = new ArrayList<>();
		ArrayList<Student> studentCredits = new ArrayList<>();
		ArrayList<Student> studentDebtor = new ArrayList<>();
		ArrayList<Student> studentScholarship = new ArrayList<>();
		ArrayList<Student> studentBeginningYear = new ArrayList<>();
		ArrayList<Student> studentCurrentYear = new ArrayList<>();
		ArrayList<Student> studentGroup = new ArrayList<>();
		ArrayList<Student> studentSpecialization = new ArrayList<>();
		ArrayList<Student> studentDomain = new ArrayList<>();
		ArrayList<Student> studentFaculty = new ArrayList<>();
		ArrayList<Student> students = getAllStudents();
		
		boolean bActive = true;
		
		if (active.equals("Nu")) {
			bActive = false;
		}

		if (!name.isEmpty()) {
			studentName = getStudentByName(name);
			students = intersection(students, studentName);
		}
		if (!surname.isEmpty()) {
			studentSurname = getStudentBySurname(surname);
			students = intersection(students, studentSurname);
		}
		if (!birthDate.isEmpty()) {
			studentBirthdate = getStudentByBirthDate(birthDate);
			students = intersection(students, studentBirthdate);
		}
		if (!credits.isEmpty()) {
			studentCredits = getStudentByCreditsNumber(credits);
			students = intersection(students, studentCredits);
		}
		if (!debtor.isEmpty()) {
			if (debtor.toLowerCase() == "da") {
				studentDebtor = getStudentByDept("1");
			}
			if (debtor.toLowerCase() == "nu") {
				studentDebtor = getStudentByDept("0");
			}
			students = intersection(students, studentDebtor);
		}
		if (!scholarshipType.isEmpty()) {
			studentScholarship = getStudentByScholarshipType(scholarshipType);
			students = intersection(students, studentScholarship);
		}
		if (!beginningYear.isEmpty()) {
			studentBeginningYear = getStudentByBeginningYear(beginningYear);
			students = intersection(students, studentBeginningYear);
		}
		if (!currentYear.isEmpty()) {
			studentCurrentYear = getStudentByCurrentYear(currentYear);
			students = intersection(students, studentCurrentYear);
		}
		if (!group.isEmpty()) {
			studentGroup = getStudentByGroupName(group);
			students = intersection(students, studentGroup);
		}
		if (!specialization.isEmpty()) {
			studentSpecialization = getStudentBySpecialization(specialization);
			students = intersection(students, studentSpecialization);
		}
		if (!domain.isEmpty()) {
			studentDomain = getStudentByDomain(domain);
			students = intersection(students, studentDomain);
		}
		if (!faculty.isEmpty()) {
			studentFaculty = getStudentByFaculty(faculty);
			students = intersection(students, studentFaculty);
		}
		
		for (Student student : students) {
			if (student.isActive() != bActive) {
				students.remove(student);
			}
		}

		return students;
	}

	public ArrayList<Student> intersection(ArrayList<Student> list1, ArrayList<Student> list2) {
		ArrayList<Student> list = new ArrayList<Student>();

		for (Student student1 : list1) {
			for (Student student2 : list2) {
				if (student1.getId() == student2.getId()) {
					list.add(student1);
				}
			}
		}

		return list;
	}

}
