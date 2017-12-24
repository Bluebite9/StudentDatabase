package test;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.SubjectDBHelper;
import schemas.Subject;
import util.DBUtil;

public class SubjectTests {

	public static void allTests() {
		getAllSubjects();
		getSubjectById();
		getSubjectsByCredits();
		getSubjectsByExamType();
		getSubjectsByLabProf();
		getSubjectsByLecturer();
		getSubjectsByName();
		getSubjectsByOptional();
		getSubjectsBySemester();
		getSubjectsBySeminProf();
		getSubjectsBySpecialization();
		getSubjectsByType();
		getSubjectsByYear();
		insertAndPermanentDelteDepartment();
	}

	private static void getAllSubjects() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getAllSubjects();

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectById() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectById(1);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByName() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper
					.getSubjectsByName("Programarea Calculatoarelor in Limbaj de Programare");

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsBySpecialization() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySpecialization(1);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByYear() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySpecialization(1);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByType() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByType("specialitate");

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsBySemester() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySemester(1);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByLecturer() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByLecturer(1);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByLabProf() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByLabProf(1);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsBySeminProf() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySeminProf(1);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByCredits() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByCredits(6);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByExamType() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByExamType("examen");

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSubjectsByOptional() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByOptional(false);

			if (subjects.size() != 1) {
				throw new Exception("Subjects are too many or too few");
			}

			DBUtil.printNames(subjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertAndPermanentDelteDepartment() {
		try {
			SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
			Subject subject = new Subject(0, "bla bla", 1, 1, "specialitate", 1, 1, 1, 1, 1, "examen", false);

			int inserted = subjectDBHelper.insert(subject);

			if (inserted == 1) {
				System.out.println("Subject inserted");
				ArrayList<Subject> subjects = subjectDBHelper.getAllSubjects();
				int lastId = getLastId(subjects);
				int deleted = subjectDBHelper.permanentDelete(lastId);

				if (deleted == 1) {
					System.out.println("Subject deleted");
				} else {
					throw new Exception("Subject not deleted");
				}
			} else {
				throw new Exception("Subject not inserted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getLastId(ArrayList<Subject> list) {
		return list.get(list.size() - 1).getId();
	}

}
