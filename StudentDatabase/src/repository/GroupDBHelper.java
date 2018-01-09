package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Group;
import util.DatabaseException;
import util.Util;

public class GroupDBHelper extends Driver {

	public ArrayList<Group> getAllGroups() throws SQLException {
		this.openConnection();

		this.setRs(Util.getAllFromTable("`group`", this.getConn(), this.getPstm()));

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

	public ArrayList<Group> getGroupsByYear(String year) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from `group` where group_year = ?"));
		this.getPstm().setString(1, year);
		this.setRs(this.getPstm().executeQuery());
		;

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> getGroupsByNumberOfStudents(String numberOfStudents) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from `group` where group_numberOfStudents = ?"));
		this.getPstm().setString(1, numberOfStudents);
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

	public Group getLastGroupRow() throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from students.group order by group_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups.get(0);
	}

	public ArrayList<Group> find(String name, String specialization, String year, String numberOfStudents, String faculty,
			String domain, String active) throws SQLException {
		ArrayList<Group> groupName = new ArrayList<>();
		ArrayList<Group> groupSpecialization = new ArrayList<>();
		ArrayList<Group> groupYear = new ArrayList<>();
		ArrayList<Group> groupNumberOfStudents = new ArrayList<>();
		ArrayList<Group> groupFaculty = new ArrayList<>();
		ArrayList<Group> groupDomain = new ArrayList<>();
		ArrayList<Group> groups = getAllGroups();

		boolean bActive = true;

		if (active.equals("Nu")) {
			bActive = false;
		}

		if (!name.isEmpty()) {
			groupName = getGroupsByName(name);
			groups = intersection(groups, groupName);
		}
		if (!specialization.isEmpty()) {
			groupSpecialization = getGroupBySpecializationName(specialization);
			groups = intersection(groups, groupSpecialization);
		}
		if (!year.isEmpty()) {
			groupYear = getGroupsByYear(year);
			groups = intersection(groups, groupYear);
		}
		if (!numberOfStudents.isEmpty()) {
			groupNumberOfStudents = getGroupsByNumberOfStudents(numberOfStudents);
			groups = intersection(groups, groupNumberOfStudents);
		}
		if (!faculty.isEmpty()) {
			groupFaculty = getGroupByFacultyName(faculty);
			groups = intersection(groups, groupFaculty);
		}
		if (!domain.isEmpty()) {
			groupDomain = getGroupByDomainName(domain);
			groups = intersection(groups, groupDomain);
		}

		for (Group group : groups) {
			if (group.isActive() != bActive) {
				groups.remove(group);
			}
		}

		return groups;
	}

	private ArrayList<Group> getGroupByFacultyName(String faculty) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.group join students.specialization on specialization_id = group_specialization join students.domain on domain_id = specialization_domain join students.faculty on faculty_id = domain_faculty where faculty_name like ?"));
		this.getPstm().setString(1, faculty);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	private ArrayList<Group> getGroupByDomainName(String domain) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.group join students.specialization on specialization_id = group_specialization join students.domain on domain_id = specialization_id where domain_name like ?"));
		this.getPstm().setString(1, domain);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	private ArrayList<Group> getGroupBySpecializationName(String specialization) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.group join students.specialization on specialization_id = group_specialization where specialization_name like ?"));
		this.getPstm().setString(1, specialization);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Group> groups = this.createGroupsFromResultSet(this.getRs());
		this.closeAll();

		return groups;
	}

	public ArrayList<Group> intersection(ArrayList<Group> list1, ArrayList<Group> list2) {
		ArrayList<Group> list = new ArrayList<Group>();

		for (Group group1 : list1) {
			for (Group group2 : list2) {
				if (group1.getId() == group2.getId()) {
					list.add(group1);
				}
			}
		}

		return list;
	}

	public int update(Group group) throws SQLException, DatabaseException {
		this.openConnection();
		group.validate(getConn(), getPstm(), getRs());

		this.setPstm(this.getConn().prepareStatement(
				"update students.group set group_name = ?, group_specialization = ?, group_year = ?, group_numberOfStudents = ?, group_active = ? where group_id = ?"));
		this.getPstm().setString(1, group.getName());
		this.getPstm().setInt(2, group.getSpecialization());
		this.getPstm().setInt(3, group.getYear());
		this.getPstm().setInt(4, group.getNumberOfStudents());
		this.getPstm().setBoolean(5, group.isActive());
		this.getPstm().setInt(6, group.getId());
		int update = this.getPstm().executeUpdate();

		return update;
	}

}