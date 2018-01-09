package test;

import java.util.ArrayList;

import repository.DepartmentDBHelper;
import schemas.Department;
import util.Util;

public class DepartmentTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------DEPARTMENT TESTS--------------------");
		getAllDepartments();
		getDepartmentById();
		getDepartmentByName();
		getDepartmentByFaculty();
		insertAndPermanentDelteDepartment();
		System.out.println();
	}

	private static void getAllDepartments() throws Exception {
		DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
		ArrayList<Department> departments = departmentDBHelper.getAllDepartments();

		if (departments.size() != 1) {
			throw new Exception("Departments are too many or too few");
		}

		Util.printNames(departments);
	}

	private static void getDepartmentById() throws Exception {
		DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
		ArrayList<Department> departments = departmentDBHelper.getDepartmentById(1);

		if (departments.size() != 1) {
			throw new Exception("Departments are too many or too few");
		}

		Util.printNames(departments);
	}

	private static void getDepartmentByName() throws Exception {
		DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
		ArrayList<Department> departments = departmentDBHelper.getDepartmentsByName("departamentul");

		if (departments.size() != 1) {
			throw new Exception("Departments are too many or too few");
		}
		
		Util.printNames(departments);
	}

	private static void getDepartmentByFaculty() throws Exception {
		DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
		ArrayList<Department> departments = departmentDBHelper.getDepartmentsByFaculty(1);

		if (departments.size() != 1) {
			throw new Exception("Departments are too many or too few");
		}

		Util.printNames(departments);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
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
	}

	private static int getLastId(ArrayList<Department> list) {
		return list.get(list.size() - 1).getId();
	}

}
