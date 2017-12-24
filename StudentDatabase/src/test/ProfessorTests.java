package test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import repository.ProfessorDBHelper;
import schemas.Professor;
import util.DBUtil;

public class ProfessorTests {

	public static void allTests() {
		getAllProfessors();
		getProfessorById();
		getProfessorsByBirthDate();
		getProfessorsByDegree();
		getProfessorsByDepartment();
		getProfessorsByName();
		getProfessorsByNameAndSurname();
		getProfessorsBySurname();
		insertAndPermanentDelteDepartment();
	}

	private static void getAllProfessors() {
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getAllProfessors();

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getProfessorById() {
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getProfessorById(1);

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getProfessorsByName() {
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getProfessorByName("Luminita");

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getProfessorsBySurname() {
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getProfessorBySurname("Dumitriu");

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getProfessorsByBirthDate() {
		java.util.Date date = new GregorianCalendar(1960, 9, 10).getTime();
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getProfessorByBirthDate(new Date(date.getTime()));

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getProfessorsByNameAndSurname() {
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getProfessorByNameAndSurname("Luminita", "Dumitriu");

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getProfessorsByDepartment() {
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getProfessorByDepartment(1);

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getProfessorsByDegree() {
		try {
			ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
			ArrayList<Professor> professors = professorDBHelper.getProfessorByDegree("Prof. dr. ing.");

			if (professors.size() != 1) {
				throw new Exception("Professors are too many or too few");
			}

			DBUtil.printNames(professors);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertAndPermanentDelteDepartment() {
		java.util.Date date = new GregorianCalendar(2017, 0, 9).getTime();
		try {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getLastId(ArrayList<Professor> list) {
		return list.get(list.size() - 1).getId();
	}
}