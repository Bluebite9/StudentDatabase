package test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import repository.StudentDBHelper;
import schemas.Student;
import util.DBUtil;

public class StudentTests {

	public static void allTests() {
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
	}

	private static void getAllStudents() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getAllStudents();

			if (students.size() != 3) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentById() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentById(1);

			if (students.size() != 1) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByName() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByName("Emilian");

			if (students.size() != 1) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsBySurname() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentBySurname("Munteanu");

			if (students.size() != 1) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByBirthDate() {
		java.util.Date date = new GregorianCalendar(1997, 0, 9).getTime();
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByBirthDate(new Date(date.getTime()));

			if (students.size() != 1) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByGroup() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByGroup(1);

			if (students.size() != 3) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByNameAndSurname() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByNameAndSurname("Emilian", "Munteanu");

			if (students.size() != 1) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByBeginningYear() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByBeginningYear(2017);

			if (students.size() != 3) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByCreditsNumber() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByCreditsNumber(0);

			if (students.size() != 3) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByScholarshipType() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByScholarshipType("performanta");

			if (students.size() != 1) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getStudentsByDebt() {
		try {
			StudentDBHelper studentsDBHelper = new StudentDBHelper();
			ArrayList<Student> students = studentsDBHelper.getStudentByDept(false);

			if (students.size() != 3) {
				throw new Exception("Students are too many or too few");
			}

			DBUtil.printNames(students);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertAndPermanentDelteDepartment() {
		java.util.Date date = new GregorianCalendar(1997, 0, 9).getTime();
		try {
			StudentDBHelper studentDBHelper = new StudentDBHelper();
			Student student = new Student(0, "bla bla", "daa", new Date(date.getTime()), 1, "a", 2017, 1, "Bursa de merit", false, 0);

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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getLastId(ArrayList<Student> list) {
		return list.get(list.size() - 1).getId();
	}

}
