package test;

import java.util.ArrayList;

import repository.SubjectDBHelper;
import schemas.Subject;
import util.Util;

public class SubjectTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------SUBJECT TESTS--------------------");
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
		System.out.println();
	}

	private static void getAllSubjects() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getAllSubjects();

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectById() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectById(1);

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByName() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper
				.getSubjectsByName("Programarea Calculatoarelor in Limbaj de Programare");

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsBySpecialization() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySpecialization(1);

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByYear() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySpecialization(1);

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByType() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByType("specialitate");

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsBySemester() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySemester("1");

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByLecturer() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByLecturer(1);

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByLabProf() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByLabProf(1);

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsBySeminProf() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsBySeminProf(1);

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByCredits() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByCredits("6");

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByExamType() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByExamType("examen");

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void getSubjectsByOptional() throws Exception {
		SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
		ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByOptional("false");

		if (subjects.size() != 1) {
			throw new Exception("Subjects are too many or too few");
		}

		Util.printNames(subjects);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
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
	}

	private static int getLastId(ArrayList<Subject> list) {
		return list.get(list.size() - 1).getId();
	}

}
