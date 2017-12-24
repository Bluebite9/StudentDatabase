package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Faculty;
import util.DBUtil;
import util.DatabaseException;

public class FacultyDBHelper extends Driver {

	public ArrayList<Faculty> getAllFaculties() throws SQLException {
		this.openConnection();

		this.setRs(DBUtil.getAllFromTable("faculty", this.getConn(), this.getPstm()));
		;
		ArrayList<Faculty> faculties = this.createFacultiesFromResultSet(this.getRs());
		this.closeAll();

		return faculties;
	}

	public ArrayList<Faculty> getFacultyById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from faculty where faculty_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Faculty> faculties = this.createFacultiesFromResultSet(this.getRs());
		this.closeAll();

		return faculties;
	}

	public ArrayList<Faculty> getFacultiesByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from faculty where faculty_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Faculty> faculties = this.createFacultiesFromResultSet(this.getRs());
		this.closeAll();

		return faculties;
	}

	public ArrayList<Faculty> getFacultiesByAddress(String address) throws SQLException {
		String newAddress = "%" + address + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from faculty where faculty_address like ?"));
		this.getPstm().setString(1, newAddress);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Faculty> faculties = this.createFacultiesFromResultSet(this.getRs());
		this.closeAll();

		return faculties;
	}

	public ArrayList<Faculty> createFacultiesFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		while (rs.next()) {
			faculties
					.add(new Faculty(rs.getInt("faculty_id"), rs.getString("faculty_name"), rs.getString("faculty_address")));
		}

		return faculties;
	}

	public int insert(Faculty faculty) throws DatabaseException, SQLException {
		faculty.validate();
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("insert into faculty (faculty_name, faculty_address) values (?, ?);"));
		this.getPstm().setString(1, faculty.getName());
		this.getPstm().setString(2, faculty.getAddress());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.faculty where faculty_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

}