package test;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.FacultyDBHelper;
import schemas.Faculty;
import util.DBUtil;

public class FacultyTests {

	public static void allTests() {
		getAllFaculties();
		getFacultyById();
		getFacultyByName();
		getFacultyByAddress();
		insertAndPermanentDelteDepartment();
	}

	private static void getAllFaculties() {
		try {
			FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
			ArrayList<Faculty> faculties = facultyDBHelper.getAllFaculties();

			if (faculties.size() != 1) {
				throw new Exception("Faculties are too many or too few");
			}

			DBUtil.printNames(faculties);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getFacultyById() {
		try {
			FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
			ArrayList<Faculty> faculties = facultyDBHelper.getFacultyById(1);

			if (faculties.size() != 1) {
				throw new Exception("Faculties are too many or too few");
			}

			DBUtil.printNames(faculties);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getFacultyByName() {
		try {
			FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
			ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByName("Facultatea");

			if (faculties.size() != 1) {
				throw new Exception("Faculties are too many or too few");
			}

			DBUtil.printNames(faculties);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getFacultyByAddress() {
		try {
			FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
			ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByAddress("Stiintei");

			if (faculties.size() != 1) {
				throw new Exception("Faculties are too many or too few");
			}

			DBUtil.printNames(faculties);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertAndPermanentDelteDepartment() {
		try {
			FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
			Faculty faculty = new Faculty(0, "bla bla", "bksajg");

			int inserted = facultyDBHelper.insert(faculty);

			if (inserted == 1) {
				System.out.println("Faculty inserted");
				ArrayList<Faculty> faculties = facultyDBHelper.getAllFaculties();
				int lastId = getLastId(faculties);
				int deleted = facultyDBHelper.permanentDelete(lastId);

				if (deleted == 1) {
					System.out.println("Faculty deleted");
				} else {
					throw new Exception("Faculty not deleted");
				}
			} else {
				throw new Exception("Faculty not inserted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getLastId(ArrayList<Faculty> list) {
		return list.get(list.size() - 1).getId();
	}

}
