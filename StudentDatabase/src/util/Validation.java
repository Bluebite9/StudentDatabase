package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

	public static String validateShortName(String name) throws DatabaseException {
		validateName(name);
		if (name.isEmpty() || name.length() > 10) {
			throw DatabaseException.invalidShortName;
		}
		
		return name;
	}

	public static String validateMediumName(String name) throws DatabaseException {
		validateName(name);
		if (name.isEmpty() || name.length() > 45) {
			throw DatabaseException.invalidMediumName;
		}
		
		return name;
	}

	public static String validateLongName(String name) throws DatabaseException {
		validateName(name);
		if (name.isEmpty() || name.length() > 100) {
			throw DatabaseException.invalidLongName;
		}
		
		return name;
	}

	public static void validateName(String name) throws DatabaseException {
		if (name.isEmpty() || !name.matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
			throw DatabaseException.invalidName;
		}
	}

	public static void validateList(@SuppressWarnings("rawtypes") List list) throws SQLException, DatabaseException {
		if (list.size() < 1) {
			throw DatabaseException.invalidList;
		}
	}

	public static void validateYear(int year) throws DatabaseException {
		if (year < 1 || year > 6) {
			throw DatabaseException.invalidYear;
		}
	}

	public static int validateStringYear(String year) throws DatabaseException {
		int intYear = 0;
		
		if (year.isEmpty()) {
			throw DatabaseException.invalidYear;
		}
		
		try {
			intYear = Integer.parseInt(year);
		} catch (Exception e) {
			throw DatabaseException.invalidYear;
		}
		
		return intYear;
	}

	public static void validateSemester(int semester) throws DatabaseException {
		if (semester != 1 && semester != 2) {
			throw DatabaseException.invalidSemester;
		}
	}

	public static int validateStringSemester(String semester) throws DatabaseException {
		int intSemester = 0;
		
		if (semester.isEmpty()) {
			throw DatabaseException.invalidSemester;
		}
		
		try {
			intSemester = Integer.parseInt(semester);
		} catch (Exception e) {
			throw DatabaseException.invalidSemester;
		}
		
		return intSemester;
	}

	public static String validateType(String type) throws DatabaseException {
		if (type.isEmpty() || type.toLowerCase() != "fundamentala" && type.toLowerCase() != "domeniu"
				&& type.toLowerCase() != "specialitate") {
			throw DatabaseException.invalidType;
		}
		
		return type;
	}

	public static void validateCredits(int credits) throws DatabaseException {
		if (credits < 1 || credits > 6) {
			throw DatabaseException.invalidCredits;
		}
	}

	public static int validateStringCredits(String credits) throws DatabaseException {
		int intCredits = 0;
		
		if (credits.isEmpty()) {
			throw DatabaseException.invalidCredits;
		}
		
		try {
			intCredits = Integer.parseInt(credits);
		} catch (Exception e) {
			throw DatabaseException.invalidCredits;
		}
		
		return intCredits;
	}

	public static String validateExamType(String examType) throws DatabaseException {
		if (examType.isEmpty() || examType != "verificare" && examType != "examen") {
			throw DatabaseException.invalidExamType;
		}
		
		return examType;
	}

	public static void validateMark(int mark) throws DatabaseException {
		if (mark < 1 || mark > 10) {
			throw DatabaseException.invalidMark;
		}
	}

	public static int validateStringMark(String mark) throws DatabaseException {
		int intMark = 0;
		
		if (mark.isEmpty()) {
			throw DatabaseException.invalidMark;
		}
		
		try {
			intMark = Integer.parseInt(mark);
		} catch (Exception e) {
			throw DatabaseException.invalidCredits;
		}
		
		return intMark;
	}

	public static String validateStringDate(String date) throws DatabaseException {
		if (date.isEmpty()) {
			throw DatabaseException.invalidDate;
		}
		
		String[] dateStringArray = date.split("-");
		int year = 0;
		int month = 0;
		int day = 0;

		if (dateStringArray.length != 3) {
			throw DatabaseException.invalidDate;
		}

		try {
			year = Integer.parseInt(dateStringArray[0]);
			month = Integer.parseInt(dateStringArray[1]);
			day = Integer.parseInt(dateStringArray[2]);
		} catch (Exception e) {
			throw DatabaseException.invalidDate;
		}

		if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR) || month < 1 || month > 12 || day < 0 || day > 31) {
			throw DatabaseException.invalidDate;
		}
		
		return date;
	}

	public static String validateSession(String session) throws DatabaseException {
		if (session.isEmpty() || (session != "iarna" && session != "vara" && session != "toamna")) {
			throw DatabaseException.invalidSession;
		}
		
		return session;
	}

	public static void validateNumberOfStudents(int numberOfStudents) throws DatabaseException {
		if (numberOfStudents < 0 || numberOfStudents > 60) {
			throw DatabaseException.invalidNumberOfStudents;
		}
	}

	public static int validateStringNumberOfStudents(String numberOfStudents) throws DatabaseException {
		if (numberOfStudents.isEmpty()) {
			throw DatabaseException.invalidNumberOfStudents;
		}
		
		int intNumberOfStudents = 0;
		
		try {
		intNumberOfStudents =	Integer.parseInt(numberOfStudents);
		} catch (Exception e) {
			throw DatabaseException.invalidNumberOfStudents;
		}
		
		return intNumberOfStudents;
	}

	public static String validateSubgroup(String subgroup) throws DatabaseException {
		if (subgroup.isEmpty() || (!subgroup.equals("a") && !subgroup.equals("b") && !subgroup.equals("c"))) {
			throw DatabaseException.invalidSubgroup;
		}
		
		return subgroup;
	}

	public static String validateScholarshipType(String scholarshipType) throws DatabaseException {
		if (scholarshipType.isEmpty() || (!scholarshipType.equals("Bursa sociala") && !scholarshipType.equals("Bursa de merit")
				&& !scholarshipType.equals("Bursa de performanta") && !scholarshipType.equals(""))) {
			throw DatabaseException.invalidScholarshipType;
		}
		
		return scholarshipType;
	}

	public static void validateCreditsNumber(int creditsNumber) throws DatabaseException {
		if (creditsNumber < 0 || creditsNumber > 500) {
			throw DatabaseException.invalidCreditsNumber;
		}
	}

	public static int validateStringCreditsNumber(String creditsNumber) throws DatabaseException {
		int intCreditsNumber = 0;
		
		if (creditsNumber.isEmpty()) {
			throw DatabaseException.invalidCreditsNumber;
		}
		
		try {
			intCreditsNumber = Integer.parseInt(creditsNumber);
		} catch (Exception e) {
			throw DatabaseException.invalidCreditsNumber;
		}
		
		return intCreditsNumber;
	}

	public static String validateAddress(String address) throws DatabaseException {
		if (address.isEmpty()) {
			throw DatabaseException.invalidAddress;
		}
		
		Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(address);
		if (matcher.find()) {
			throw DatabaseException.invalidAddress;
		}
		
		return address;
	}

	public static String validateDegree(String degree) throws DatabaseException {
		if (degree.isEmpty() || (!degree.equals("Prof. dr. ing.") && !degree.equals("Conf. dr. ing.") && degree.equals("S.l. dr. ing.")
				&& !degree.equals("S.l. dr.") && !degree.equals("S.l. dr. mat.") && !degree.equals("Asist. drd. mat. inf.")
				&& !degree.equals("Asist. drd. ing."))) {
			throw DatabaseException.invalidDegree;
		}
		
		return degree;
	}

	public static void validateBeginningYear(int beginningYear) throws DatabaseException {
		if (beginningYear < 1980) {
			throw DatabaseException.invalidBeginningYear;
		}
	}

	public static int validateStringBeginningYear(String beginningYear) throws DatabaseException {
		int intBeginningYear = 0;
		
		if (beginningYear.isEmpty()) {
			throw DatabaseException.invalidBeginningYear;
		}
		
		try {
			intBeginningYear = Integer.parseInt(beginningYear);
		} catch (Exception e) {
			throw DatabaseException.invalidBeginningYear;
		}
		
		return intBeginningYear;
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
		String stringId = "" + id;
		StudentDBHelper studentDBHelper = new StudentDBHelper();
		ArrayList<Student> student = studentDBHelper.getStudentById(stringId);

		validateList(student);
	}

	public static void findSubject(Connection conn, PreparedStatement pstm, ResultSet rs, int id)
			throws SQLException, DatabaseException {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectById(id);

		validateList(subjects);
	}

}
