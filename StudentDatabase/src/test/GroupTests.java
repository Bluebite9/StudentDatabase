package test;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.GroupDBHelper;
import schemas.Group;
import util.DBUtil;

public class GroupTests {

	public static void allTests() {
		getAllGroups();
		getGroupById();
		getGroupsByName();
		getGroupsByNumberOfStudents();
		getGroupsBySpecialization();
		getGroupsByYear();
		insertAndPermanentDelteDepartment();
	}

	private static void getAllGroups() {
		try {
			GroupDBHelper groupDBHelper = new GroupDBHelper();
			ArrayList<Group> groups = groupDBHelper.getAllGroups();

			if (groups.size() != 1) {
				throw new Exception("Groups are too many or too few");
			}

			DBUtil.printNames(groups);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getGroupById() {
		try {
			GroupDBHelper groupDBHelper = new GroupDBHelper();
			ArrayList<Group> groups = groupDBHelper.getGroupById(1);

			if (groups.size() != 1) {
				throw new Exception("Groups are too many or too few");
			}

			DBUtil.printNames(groups);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getGroupsByName() {
		try {
			GroupDBHelper groupDBHelper = new GroupDBHelper();
			ArrayList<Group> groups = groupDBHelper.getGroupsByName("22C11");

			if (groups.size() != 1) {
				throw new Exception("Groups are too many or too few");
			}

			DBUtil.printNames(groups);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getGroupsBySpecialization() {
		try {
			GroupDBHelper groupDBHelper = new GroupDBHelper();
			ArrayList<Group> groups = groupDBHelper.getGroupsBySpecialization(1);

			if (groups.size() != 1) {
				throw new Exception("Groups are too many or too few");
			}

			DBUtil.printNames(groups);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getGroupsByYear() {
		try {
			GroupDBHelper groupDBHelper = new GroupDBHelper();
			ArrayList<Group> groups = groupDBHelper.getGroupsByYear(1);

			if (groups.size() != 1) {
				throw new Exception("Groups are too many or too few");
			}

			DBUtil.printNames(groups);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getGroupsByNumberOfStudents() {
		try {
			GroupDBHelper groupDBHelper = new GroupDBHelper();
			ArrayList<Group> groups = groupDBHelper.getGroupsByNumberOfStudents(20);

			if (groups.size() != 1) {
				throw new Exception("Groups are too many or too few");
			}

			DBUtil.printNames(groups);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertAndPermanentDelteDepartment() {
		try {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getLastId(ArrayList<Group> list) {
		return list.get(list.size() - 1).getId();
	}

}
