package test;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.DepartmentDBHelper;
import schemas.Department;
import util.DBUtil;

public class DepartmentTests {

	public static void allTests() {
		getAllDepartments();
		getDepartmentById();
		getDepartmentByName();
		getDepartmentByFaculty();
		insertAndPermanentDelteDepartment();
	}

	private static void getAllDepartments() {
		try {
			DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
			ArrayList<Department> departments = departmentDBHelper.getAllDepartments();

			if (departments.size() != 1) {
				throw new Exception("Departments are too many or too few");
			}

			DBUtil.printNames(departments);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getDepartmentById() {
		try {
			DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
			ArrayList<Department> departments = departmentDBHelper.getDepartmentById(1);

			if (departments.size() != 1) {
				throw new Exception("Departments are too many or too few");
			}

			DBUtil.printNames(departments);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getDepartmentByName() {
		try {
			DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
			ArrayList<Department> departments = departmentDBHelper.getDepartmentsByName("Calculatoare");

			if (departments.size() != 1) {
				throw new Exception("Departments are too many or too few");
			}

			DBUtil.printNames(departments);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getDepartmentByFaculty() {
		try {
			DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
			ArrayList<Department> departments = departmentDBHelper.getDepartmentsByFaculty(1);

			if (departments.size() != 1) {
				throw new Exception("Departments are too many or too few");
			}

			DBUtil.printNames(departments);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void insertAndPermanentDelteDepartment() {
		try {
			DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
			Department department = new Department(0, "bla bla", 1);
			
			int inserted = departmentDBHelper.insert(department);
			
			if (inserted == 1) {
				System.out.println("Department inserted");
				ArrayList<Department> departments = departmentDBHelper.getAllDepartments();
				int lastId = getLastId(departments);
				int deleted = departmentDBHelper.permanentDelete(lastId);
				
				if (deleted == 1) {
					System.out.println("Department deleted");
				} else {
					throw new Exception("Department not deleted");
				}
			} else {
				throw new Exception("Department not inserted");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int getLastId(ArrayList<Department> list) {
		return list.get(list.size() - 1).getId();
	}

}
