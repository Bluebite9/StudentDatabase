package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Group;
import util.DBUtil;
import util.DatabaseException;

public class GroupDBHelper extends Driver {

	public ArrayList<Group> getAllGroups() throws SQLException {
		this.openConnection();

		this.setRs(DBUtil.getAllFromTable("`group`", this.getConn(), this.getPstm()));

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> getGroupById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from `group` where group_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> getGroupsByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from `group` where group_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());
		;

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> getGroupsBySpecialization(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from `group` where group_specialization = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> getGroupsByYear(int year) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from `group` where group_year = ?"));
		this.getPstm().setInt(1, year);
		this.setRs(this.getPstm().executeQuery());
		;

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> getGroupsByNumberOfStudents(int numberOfStudents) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from `group` where group_numberOfStudents = ?"));
		this.getPstm().setInt(1, numberOfStudents);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> createGroupsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Group> groups = new ArrayList<Group>();
		while (rs.next()) {
			groups.add(new Group(rs.getInt("group_id"), rs.getString("group_name"), rs.getInt("group_specialization"),
					rs.getInt("group_year"), rs.getInt("group_numberOfStudents")));
		}

		return groups;
	}

	public int insert(Group group) throws DatabaseException, SQLException {
		this.openConnection();
		group.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement(
				"insert into students.group (group_name, group_specialization, group_year, group_numberOfStudents) values (?, ?, ?, ?);"));
		this.getPstm().setString(1, group.getName());
		this.getPstm().setInt(2, group.getSpecialization());
		this.getPstm().setInt(3, group.getYear());
		this.getPstm().setInt(4, group.getNumberOfStudents());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.group where group_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

}