package test;

import java.util.ArrayList;

import repository.GroupDBHelper;
import schemas.Group;
import util.DBUtil;

public class GroupTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------GROUP TEST--------------------");
		getAllGroups();
		getGroupById();
		getGroupsByName();
		getGroupsByNumberOfStudents();
		getGroupsBySpecialization();
		getGroupsByYear();
		insertAndPermanentDelteDepartment();
		System.out.println();
	}

	private static void getAllGroups() throws Exception {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		ArrayList<Group> groups = groupDBHelper.getAllGroups();

		if (groups.size() != 1) {
			throw new Exception("Groups are too many or too few");
		}

		DBUtil.printNames(groups);
	}

	private static void getGroupById() throws Exception {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		ArrayList<Group> groups = groupDBHelper.getGroupById(1);

		if (groups.size() != 1) {
			throw new Exception("Groups are too many or too few");
		}

		DBUtil.printNames(groups);
	}

	private static void getGroupsByName() throws Exception {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		ArrayList<Group> groups = groupDBHelper.getGroupsByName("22C11");

		if (groups.size() != 1) {
			throw new Exception("Groups are too many or too few");
		}

		DBUtil.printNames(groups);
	}

	private static void getGroupsBySpecialization() throws Exception {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		ArrayList<Group> groups = groupDBHelper.getGroupsBySpecialization(1);

		if (groups.size() != 1) {
			throw new Exception("Groups are too many or too few");
		}

		DBUtil.printNames(groups);
	}

	private static void getGroupsByYear() throws Exception {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		ArrayList<Group> groups = groupDBHelper.getGroupsByYear(1);

		if (groups.size() != 1) {
			throw new Exception("Groups are too many or too few");
		}

		DBUtil.printNames(groups);
	}

	private static void getGroupsByNumberOfStudents() throws Exception {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		ArrayList<Group> groups = groupDBHelper.getGroupsByNumberOfStudents(20);

		if (groups.size() != 1) {
			throw new Exception("Groups are too many or too few");
		}

		DBUtil.printNames(groups);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
		GroupDBHelper groupDBHelper = new GroupDBHelper();
		Group group = new Group(0, "fdes", 1, 1, 1);

		int inserted = groupDBHelper.insert(group);

		if (inserted == 1) {
			System.out.println("Group inserted");
			ArrayList<Group> groups = groupDBHelper.getAllGroups();
			int lastId = getLastId(groups);
			int deleted = groupDBHelper.permanentDelete(lastId);

			if (deleted == 1) {
				System.out.println("Group deleted");
			} else {
				throw new Exception("Group not deleted");
			}
		} else {
			throw new Exception("Group not inserted");
		}
	}

	private static int getLastId(ArrayList<Group> list) {
		return list.get(list.size() - 1).getId();
	}

}
