package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import repository.MarkDBHelper;
import schemas.Mark;
import util.DBUtil;

public class MarkTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------MARK TESTS--------------------");
		getAllMarks();
		getMarkByDate();
		getMarkById();
		getMarkByMark();
		getMarkBySession();
		getMarkByStudent();
		getMarkBySubject();
		insertAndPermanentDelteDepartment();
		System.out.println();
	}

	private static void getAllMarks() throws Exception {
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> mark = markDBHelper.getAllMarks();

		if (mark.size() != 1) {
			throw new Exception("Marks are too many or too few");
		}

		DBUtil.printNames(mark);
	}

	private static void getMarkById() throws Exception {
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> mark = markDBHelper.getMarkById(1);

		if (mark.size() != 1) {
			throw new Exception("Marks are too many or too few");
		}

		DBUtil.printNames(mark);
	}

	private static void getMarkByStudent() throws Exception {
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> mark = markDBHelper.getMarksByStudent(3);

		if (mark.size() != 1) {
			throw new Exception("Marks are too many or too few");
		}

		DBUtil.printNames(mark);
	}

	private static void getMarkByMark() throws Exception {
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> mark = markDBHelper.getMarksByMark(10);

		if (mark.size() != 1) {
			throw new Exception("Marks are too many or too few");
		}

		DBUtil.printNames(mark);
	}

	private static void getMarkBySubject() throws Exception {
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> mark = markDBHelper.getMarksBySubject(1);

		if (mark.size() != 1) {
			throw new Exception("Marks are too many or too few");
		}

		DBUtil.printNames(mark);
	}

	private static void getMarkBySession() throws Exception {
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> mark = markDBHelper.getMarksBySession("iarna");

		if (mark.size() != 1) {
			throw new Exception("Marks are too many or too few");
		}

		DBUtil.printNames(mark);
	}

	private static void getMarkByDate() throws Exception {
		java.util.Date date = new GregorianCalendar(2018, 0, 20).getTime();
		MarkDBHelper markDBHelper = new MarkDBHelper();
		ArrayList<Mark> mark = markDBHelper.getMarksByDate(new Date(date.getTime()));

		if (mark.size() != 1) {
			throw new Exception("Marks are too many or too few");
		}

		DBUtil.printNames(mark);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
		java.util.Date date = new GregorianCalendar(2017, 0, 9).getTime();
		MarkDBHelper markDBHelper = new MarkDBHelper();
		Mark mark = new Mark(0, 1, 10, 1, "iarna", new Date(date.getTime()));

		int inserted = markDBHelper.insert(mark);

		if (inserted == 1) {
			System.out.println("Mark inserted");
			ArrayList<Mark> marks = markDBHelper.getAllMarks();
			int lastId = getLastId(marks);
			int deleted = markDBHelper.permanentDelete(lastId);

			if (deleted == 1) {
				System.out.println("Mark deleted");
			} else {
				throw new Exception("Mark not deleted");
			}
		} else {
			throw new Exception("Mark not inserted");
		}
	}

	private static int getLastId(ArrayList<Mark> list) {
		return list.get(list.size() - 1).getId();
	}

}
