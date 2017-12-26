package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import repository.ProfessorDBHelper;
import schemas.Professor;
import util.DBUtil;

public class ProfessorTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------PROFESSORS TEST--------------------");
		getAllProfessors();
		getProfessorById();
		getProfessorsByBirthDate();
		getProfessorsByDegree();
		getProfessorsByDepartment();
		getProfessorsByName();
		getProfessorsByNameAndSurname();
		getProfessorsBySurname();
		insertAndPermanentDelteDepartment();
		System.out.println();
	}

	private static void getAllProfessors() throws Exception {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getAllProfessors();

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void getProfessorById() throws Exception {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorById(1);

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void getProfessorsByName() throws Exception {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorByName("Luminita");

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void getProfessorsBySurname() throws Exception {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorBySurname("Dumitriu");

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void getProfessorsByBirthDate() throws Exception {
		java.util.Date date = new GregorianCalendar(1960, 9, 10).getTime();
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorByBirthDate(new Date(date.getTime()));

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void getProfessorsByNameAndSurname() throws Exception {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorByNameAndSurname("Luminita", "Dumitriu");

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void getProfessorsByDepartment() throws Exception {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorByDepartment(1);

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void getProfessorsByDegree() throws Exception {
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		ArrayList<Professor> professors = professorDBHelper.getProfessorByDegree("Prof. dr. ing.");

		if (professors.size() != 1) {
			throw new Exception("Professors are too many or too few");
		}

		DBUtil.printNames(professors);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
		java.util.Date date = new GregorianCalendar(2017, 0, 9).getTime();
		ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
		Professor professor = new Professor(0, "da", "da", new Date(date.getTime()), 1, "Prof. dr. ing.");

		int inserted = professorDBHelper.insert(professor);

		if (inserted == 1) {
			System.out.println("Professor inserted");
			ArrayList<Professor> professors = professorDBHelper.getAllProfessors();
			int lastId = getLastId(professors);
			int deleted = professorDBHelper.permanentDelete(lastId);

			if (deleted == 1) {
				System.out.println("Professor deleted");
			} else {
				throw new Exception("Professor not deleted");
			}
		} else {
			throw new Exception("Professor not inserted");
		}
	}

	private static int getLastId(ArrayList<Professor> list) {
		return list.get(list.size() - 1).getId();
	}
}
