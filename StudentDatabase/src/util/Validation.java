package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import repository.DepartmentDBHelper;
import repository.DomainDBHelper;
import repository.FacultyDBHelper;
import repository.GroupDBHelper;
import repository.MarkDBHelper;
import repository.ProfessorDBHelper;
import repository.SpecializationDBHelper;
import repository.StudentDBHelper;
import repository.SubjectDBHelper;
import schemas.Department;
import schemas.Domain;
import schemas.Faculty;
import schemas.Group;
import schemas.Mark;
import schemas.Professor;
import schemas.Specialization;
import schemas.Student;
import schemas.Subject;

public class Validation {

	private static DatabaseException invalidName = new DatabaseException("Invalid name");
	private static DatabaseException invalidList = new DatabaseException("Invalid list");
	private static DatabaseException invalidYear = new DatabaseException("Invalid year");
	private static DatabaseException invalidSemester = new DatabaseException("Invalid semester");
	private static DatabaseException invalidType = new DatabaseException("Invalid type");
	private static DatabaseException invalidCredits = new DatabaseException("Invalid credits");
	private static DatabaseException invalidExamType = new DatabaseException("Invalid exam type");
	private static DatabaseException invalidMark = new DatabaseException("Invalid mark");
	private static DatabaseException invalidDate = new DatabaseException("Invalid date");
	private static DatabaseException invalidSession = new DatabaseException("Invalid session");
	private static DatabaseException invalidNumberOfStudents = new DatabaseException("Invalid number of students");
	private static DatabaseException invalidSubgroup = new DatabaseException("Invalid subgroup");
	private static DatabaseException invalidScholarshipType = new DatabaseException("Invalid scholarship type");
	private static DatabaseException invalidCreditsNumber = new DatabaseException("Invalid credits number");
	private static DatabaseException invalidAddress = new DatabaseException("Invalid address");
	private static DatabaseException invalidDegree = new DatabaseException("Invalid degree");
	private static DatabaseException invalidBeginningYear = new DatabaseException("Invalid beginning year");
	private static DatabaseException invalidShortName = new DatabaseException("Name too long. Maximum is 10 characters");
	private static DatabaseException invalidMediumName = new DatabaseException("Name too long. Maximum is 45 characters");
	private static DatabaseException invalidLongName = new DatabaseException("Name too long. Maximum is 100 characters");

	public static void validateShortName(String name) throws DatabaseException {
		validateName(name);
		if (name.length() > 10) {
			throw invalidShortName;
		}
	}

	public static void validateMediumName(String name) throws DatabaseException {
		validateName(name);
		if (name.length() > 45) {
			throw invalidMediumName;
		}
	}

	public static void validateLongName(String name) throws DatabaseException {
		validateName(name);
		if (name.length() > 100) {
			throw invalidLongName;
		}
	}

	public static void validateName(String name) throws DatabaseException {
		if (!name.matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
			throw invalidName;
		}
	}

	public static void validateList(@SuppressWarnings("rawtypes") List list) throws SQLException, DatabaseException {
		if (list.size() < 1) {
			throw invalidList;
		}
	}

	public static void validateYear(int year) throws DatabaseException {
		if (year < 1 || year > 6) {
			throw invalidYear;
		}
	}

	public static void validateSemester(int semester) throws DatabaseException {
		if (semester != 1 && semester != 2) {
			throw invalidSemester;
		}
	}

	public static void validateType(String type) throws DatabaseException {
		if (type.toLowerCase() != "fundamentala" && type.toLowerCase() != "domeniu"
				&& type.toLowerCase() != "specialitate") {
			throw invalidType;
		}
	}

	public static void validateCredits(int credits) throws DatabaseException {
		if (credits < 1 || credits > 6) {
			throw invalidCredits;
		}
	}

	public static void validateExamType(String examType) throws DatabaseException {
		if (examType != "verificare" && examType != "examen") {
			throw invalidExamType;
		}
	}

	public static void validateMark(int mark) throws DatabaseException {
		if (mark < 1 || mark > 10) {
			throw invalidMark;
		}
	}

	public static void validateDate(java.sql.Date date) throws DatabaseException {
		java.util.Date dateToValidate = new GregorianCalendar(1900, 0, 1).getTime();
		if (date.before(new Date(dateToValidate.getTime()))) {
			throw invalidDate;
		}
	}

	public static void validateSession(String session) throws DatabaseException {
		if (session != "iarna" && session != "vara" && session != "toamna") {
			throw invalidSession;
		}
	}

	public static void validateNumberOfStudents(int numberOfStudents) throws DatabaseException {
		if (numberOfStudents < 0 || numberOfStudents > 60) {
			throw invalidNumberOfStudents;
		}
	}

	public static void validateSubgroup(String subgroup) throws DatabaseException {
		if (subgroup != "a" && subgroup != "b" && subgroup != "c") {
			throw invalidSubgroup;
		}
	}

	public static void validateScholarshipType(String scholarshipType) throws DatabaseException {
		if (scholarshipType != "Bursa sociala" && scholarshipType != "Bursa de merit"
				&& scholarshipType != "Bursa de performanta") {
			throw invalidScholarshipType;
		}
	}

	public static void validateCreditsNumber(int creditsNumber) throws DatabaseException {
		if (creditsNumber < 0 || creditsNumber > 500) {
			throw invalidCreditsNumber;
		}
	}

	public static void validateAddress(String address) throws DatabaseException {
		Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(address);
		if (matcher.find()) {
			throw invalidAddress;
		}
	}

	public static void validateDegree(String degree) throws DatabaseException {
		if (degree != "Prof. dr. ing." && degree != "Conf. dr. ing." && degree != "S.l. dr. ing." && degree != "S.l. dr."
				&& degree != "S.l. dr. mat." && degree != "Asist. drd. mat. inf." && degree != "Asist. drd. ing.") {
			throw invalidDegree;
		}
	}

	public static void validateBeginningYear(int beginningYear) throws DatabaseException {
		if (beginningYear < 1980) {
			throw invalidBeginningYear;
		}
	}

	public static void findDepartment(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
		ArrayList<Department> departments = departmentDBHelper.getDepartmentById(id);

		validateList(departments);
	}

	public static void findDomain(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		DomainDBHelper domainDBHelper = new DomainDBHelper();
		ArrayList<Domain> domains = domainDBHelper.getDomainById(id);

		validateList(domains);
	}

	public static void findFaculty(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
		ArrayList<Faculty> faculties = facultyDBHelper.getFacultyById(id);

		validateList(faculties);
	}

	public static void findGroup(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		ArrayList<Group> groups = groupDBHelper.getGroupById(id);

		validateList(groups);
	}

	public static void findMark(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> marks = markDBHelper.getMarkById(id);

		validateList(marks);
	}

	public static void findProfessor(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorById(id);

		validateList(professors);
	}

	public static void findSpecialization(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
		ArrayList<Specialization> specializations = specializationDBHelper.getSpecializationById(id);

		validateList(specializations);
	}

	public static void findStudent(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		StudentDBHelper studentDBHelper = new StudentDBHelper();
		ArrayList<Student> student = studentDBHelper.getStudentById(id);

		validateList(student);
	}

	public static void findSubject(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectById(id);

		validateList(subjects);
	}

}
