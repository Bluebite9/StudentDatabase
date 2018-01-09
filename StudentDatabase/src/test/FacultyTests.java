package test;

import java.util.ArrayList;

import repository.FacultyDBHelper;
import schemas.Faculty;
import util.Util;

public class FacultyTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------FACULTY TESTS--------------------");
		getAllFaculties();
		getFacultyById();
		getFacultyByName();
		getFacultyByAddress();
		insertAndPermanentDelteDepartment();
		System.out.println();
	}

	private static void getAllFaculties() throws Exception {
		FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
		ArrayList<Faculty> faculties = facultyDBHelper.getAllFaculties();

		if (faculties.size() != 1) {
			throw new Exception("Faculties are too many or too few");
		}

		Util.printNames(faculties);
	}

	private static void getFacultyById() throws Exception {
		FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
		ArrayList<Faculty> faculties = facultyDBHelper.getFacultyById(1);

		if (faculties.size() != 1) {
			throw new Exception("Faculties are too many or too few");
		}

		Util.printNames(faculties);
	}

	private static void getFacultyByName() throws Exception {
		FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
		ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByName("Facultatea");

		if (faculties.size() != 1) {
			throw new Exception("Faculties are too many or too few");
		}

		Util.printNames(faculties);
	}

	private static void getFacultyByAddress() throws Exception {
		FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
		ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByAddress("Stiintei");

		if (faculties.size() != 1) {
			throw new Exception("Faculties are too many or too few");
		}

		Util.printNames(faculties);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
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
	}

	private static int getLastId(ArrayList<Faculty> list) {
		return list.get(list.size() - 1).getId();
	}

}
