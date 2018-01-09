package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Faculty;
import util.DatabaseException;
import util.Util;

public class FacultyDBHelper extends Driver {

	public ArrayList<Faculty> getAllFaculties() throws SQLException {
		this.openConnection();

		this.setRs(Util.getAllFromTable("faculty", this.getConn(), this.getPstm()));
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

	public int update(Faculty faculty) throws SQLException, DatabaseException {
		this.openConnection();
		faculty.validate();

		this.setPstm(this.getConn().prepareStatement(
				"update students.faculty set faculty_name = ?, faculty_address = ?, faculty_active where faculty_id = ?"));
		this.getPstm().setString(1, faculty.getName());
		this.getPstm().setString(2, faculty.getAddress());
		this.getPstm().setBoolean(3, faculty.isActive());
		this.getPstm().setInt(4, faculty.getId());
		int update = this.getPstm().executeUpdate();

		return update;
	}

	public ArrayList<Faculty> find(String name, String address, String active) throws SQLException {
		ArrayList<Faculty> facultyName = new ArrayList<>();
		ArrayList<Faculty> facultyAddress = new ArrayList<>();
		ArrayList<Faculty> faculties = getAllFaculties();

		boolean bActive = true;

		if (active.equals("Nu")) {
			bActive = false;
		}

		if (!name.isEmpty()) {
			facultyName = getFacultiesByName(name);
			faculties = intersection(faculties, facultyName);
		}
		if (!address.isEmpty()) {
			facultyAddress = getFacultiesByAddress(address);
			faculties = intersection(faculties, facultyAddress);
		}

		for (Faculty faculty : faculties) {
			if (faculty.isActive() != bActive) {
				faculties.remove(faculty);
			}
		}

		return faculties;
	}

	public Faculty getLastFacultyRow() throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from students.faculty order by faculty_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Faculty> faculties = this.createFacultiesFromResultSet(this.getRs());
		this.closeAll();

		return faculties.get(0);
	}

	private ArrayList<Faculty> intersection(ArrayList<Faculty> list1, ArrayList<Faculty> list2) {
		ArrayList<Faculty> list = new ArrayList<Faculty>();

		for (Faculty faculty1 : list1) {
			for (Faculty faculty2 : list2) {
				if (faculty1.getId() == faculty2.getId()) {
					list.add(faculty1);
				}
			}
		}

		return list;
	}

}