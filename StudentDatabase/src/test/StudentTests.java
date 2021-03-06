package test;

import java.util.ArrayList;

import repository.StudentDBHelper;
import schemas.Student;
import util.Util;

public class StudentTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------STUDENT TESTS--------------------");
		getAllStudents();
		getStudentById();
		getStudentsByBeginningYear();
		getStudentsByBirthDate();
		getStudentsByCreditsNumber();
		getStudentsByDebt();
		getStudentsByGroup();
		getStudentsByName();
		getStudentsByNameAndSurname();
		getStudentsByScholarshipType();
		getStudentsBySurname();
		insertAndPermanentDelteDepartment();
		System.out.println();
	}

	private static void getAllStudents() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getAllStudents();

		if (students.size() != 3) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentById() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentById("1");

		if (students.size() != 1) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByName() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByName("Emilian");

		if (students.size() != 1) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsBySurname() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentBySurname("Munteanu");

		if (students.size() != 1) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByBirthDate() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByBirthDate("1997-01-09");

		if (students.size() != 1) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByGroup() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByGroup("1");

		if (students.size() != 3) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByNameAndSurname() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByNameAndSurname("Emilian", "Munteanu");

		if (students.size() != 1) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByBeginningYear() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByBeginningYear("2017");

		if (students.size() != 3) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByCreditsNumber() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByCreditsNumber("0");

		if (students.size() != 3) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByScholarshipType() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByScholarshipType("performanta");

		if (students.size() != 1) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void getStudentsByDebt() throws Exception {
		StudentDBHelper studentsDBHelper = new StudentDBHelper();
		ArrayList<Student> students = studentsDBHelper.getStudentByDept("0");

		if (students.size() != 3) {
			throw new Exception("Students are too many or too few");
		}

		Util.printNames(students);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
		StudentDBHelper studentDBHelper = new StudentDBHelper();
		Student student = new Student(0, "bla bla", "daa", "1997-01-09", 1, "a", 2017, 1, "Bursa de merit", false, 0);

		int inserted = studentDBHelper.insert(student);

		if (inserted == 1) {
			System.out.println("Student inserted");
			ArrayList<Student> departments = studentDBHelper.getAllStudents();
			int lastId = getLastId(departments);
			int deleted = studentDBHelper.permanentDelete(lastId);

			if (deleted == 1) {
				System.out.println("Student deleted");
			} else {
				throw new Exception("Student not deleted");
			}
		} else {
			throw new Exception("Student not inserted");
		}
	}

	private static int getLastId(ArrayList<Student> list) {
		return list.get(list.size() - 1).getId();
	}

}
