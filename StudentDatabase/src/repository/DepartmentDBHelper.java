package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Department;
import util.DBUtil;
import util.DatabaseException;

public class DepartmentDBHelper extends Driver {

	public ArrayList<Department> getAllDepartments() throws SQLException {
		this.openConnection();
		this.setRs(DBUtil.getAllFromTable("department", this.getConn(), this.getPstm()));

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

}
