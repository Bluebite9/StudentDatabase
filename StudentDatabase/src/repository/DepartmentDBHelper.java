package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Department;
import util.Util;
import util.DatabaseException;

public class DepartmentDBHelper extends Driver {

	public ArrayList<Department> getAllDepartments() throws SQLException {
		this.openConnection();
		this.setRs(Util.getAllFromTable("department", this.getConn(), this.getPstm()));

		ArrayList<Department> departments = this.createDepartmentsFromResultSet(this.getRs());
		this.closeAll();

		return departments;
	}

	public ArrayList<Department> getDepartmentById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from department where department_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Department> departments = this.createDepartmentsFromResultSet(this.getRs());
		this.closeAll();

		return departments;
	}

	public ArrayList<Department> getDepartmentsByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from department where department_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Department> departments = this.createDepartmentsFromResultSet(this.getRs());
		this.closeAll();

		return departments;
	}

	public ArrayList<Department> getDepartmentsByFacultyName(String faculty) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.department join students.faculty on faculty_id = department_faculty where faculty_name like ?"));
		this.getPstm().setString(1, faculty);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Department> students = this.createDepartmentsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Department> getDepartmentsByFaculty(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from department where department_faculty like ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Department> departments = this.createDepartmentsFromResultSet(this.getRs());
		this.closeAll();

		return departments;
	}

	public ArrayList<Department> createDepartmentsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Department> departments = new ArrayList<Department>();
		while (rs.next()) {
			departments.add(
					new Department(rs.getInt("department_id"), rs.getString("department_name"), rs.getInt("department_faculty")));
		}

		return departments;
	}

	public int insert(Department department) throws DatabaseException, SQLException {
		this.openConnection();
		department.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn()
				.prepareStatement("insert into students.department(department_name, department_faculty) values (?,?);"));
		this.getPstm().setString(1, department.getName());
		this.getPstm().setInt(2, department.getFaculty());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.department where department_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

	public ArrayList<Department> find(String name, String faculty, String active) throws SQLException {
		ArrayList<Department> departmentName = new ArrayList<>();
		ArrayList<Department> departmentFaculty = new ArrayList<>();
		ArrayList<Department> departments = getAllDepartments();

		boolean bActive = true;
		
		if (active.equals("Nu")) {
			bActive = false;
		}
		
		if (!name.isEmpty()) {
			departmentName = getDepartmentsByName(name);
			departments = intersection(departments, departmentName);
		}
		if (!faculty.isEmpty()) {
			departmentFaculty = getDepartmentsByFacultyName(faculty);
			departments = intersection(departments, departmentFaculty);
		}
		
		for (Department department : departments) {
			if (bActive != department.isActive()) {
				departments.remove(department);
			}
		}

		return departments;
	}

	public ArrayList<Department> intersection(ArrayList<Department> list1, ArrayList<Department> list2) {
		ArrayList<Department> list = new ArrayList<Department>();

		for (Department department1 : list1) {
			for (Department department2 : list2) {
				if (department1.getId() == department2.getId()) {
					list.add(department1);
				}
			}
		}

		return list;
	}

	public Department getLastDomainRow() throws SQLException {
		this.openConnection();
		this.setPstm(
				this.getConn().prepareStatement("select * from students.department order by department_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Department> department = this.createDepartmentsFromResultSet(this.getRs());
		this.closeAll();

		return department.get(0);
	}

	public int update(Department department) throws SQLException, DatabaseException {
		this.openConnection();
		department.validate(getConn(), getPstm(), getRs());

		this.setPstm(
				this.getConn().prepareStatement("update students.department set department_name = ?, department_faculty = ?, department_active = ? where department_id = ?"));
		this.getPstm().setString(1, department.getName());
		this.getPstm().setInt(2, department.getFaculty());
		this.getPstm().setBoolean(3, department.isActive());
		this.getPstm().setInt(4, department.getId());
		int update = this.getPstm().executeUpdate();

		return update;
	}

}
