package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Specialization;
import util.Util;
import util.DatabaseException;

public class SpecializationDBHelper extends Driver {

	public ArrayList<Specialization> getAllSpecializations() throws SQLException {
		this.openConnection();

		this.setRs(Util.getAllFromTable("specialization", this.getConn(), this.getPstm()));

		ArrayList<Specialization> specializations = this.createSpecializationsFromResultSet(this.getRs());
		this.closeAll();

		return specializations;
	}

	public ArrayList<Specialization> getSpecializationById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from specialization where specialization_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Specialization> specializations = this.createSpecializationsFromResultSet(this.getRs());
		this.closeAll();

		return specializations;
	}

	public ArrayList<Specialization> getSpecializationsByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from specialization where specialization_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Specialization> specializations = this.createSpecializationsFromResultSet(this.getRs());
		this.closeAll();

		return specializations;
	}

	public ArrayList<Specialization> getSpecializationsByDomain(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from specialization where specialization_domain = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Specialization> specializations = this.createSpecializationsFromResultSet(this.getRs());
		this.closeAll();

		return specializations;
	}

	public ArrayList<Specialization> createSpecializationsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Specialization> specializations = new ArrayList<Specialization>();
		while (rs.next()) {
			specializations.add(new Specialization(rs.getInt("specialization_id"), rs.getString("specialization_name"),
					rs.getInt("specialization_domain")));
		}

		return specializations;
	}

	public int insert(Specialization specialization) throws SQLException, DatabaseException {
		this.openConnection();
		specialization.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement(
				"insert into students.specialization (specialization_name, specialization_domain) values (?, ?);"));
		this.getPstm().setString(1, specialization.getName());
		this.getPstm().setInt(2, specialization.getDomain());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.specialization where specialization_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

	public ArrayList<Specialization> find(String name, String domain, String active) throws SQLException {
		ArrayList<Specialization> specializationName = new ArrayList<>();
		ArrayList<Specialization> specializationDomain = new ArrayList<>();
		ArrayList<Specialization> specializations = getAllSpecializations();

		boolean bActive = true;

		if (active.equals("Nu")) {
			bActive = false;
		}

		if (!name.isEmpty()) {
			specializationName = getSpecializationsByName(name);
			specializations = intersection(specializations, specializationName);
		}
		if (!domain.isEmpty()) {
			specializationDomain = getSpecializationsByDomainName(domain);
			specializations = intersection(specializations, specializationDomain);
		}

		for (Specialization specialization : specializations) {
			if (specialization.isActive() != bActive) {
				specializations.remove(specialization);
			}
		}

		return specializations;
	}

	public int update(Specialization specialization) throws SQLException, DatabaseException {
		this.openConnection();
		specialization.validate(getConn(), getPstm(), getRs());

		this.setPstm(this.getConn().prepareStatement(
				"update students.specialization set specialization_name = ?, specialization_domain = ?, specialization_active = ? where specialization_id = ?"));
		this.getPstm().setString(1, specialization.getName());
		this.getPstm().setInt(2, specialization.getDomain());
		this.getPstm().setBoolean(3, specialization.isActive());
		this.getPstm().setInt(4, specialization.getId());
		int update = this.getPstm().executeUpdate();

		return update;
	}

	private ArrayList<Specialization> getSpecializationsByDomainName(String domain) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.specialization join students.domain on domain_id = specialization_domain where domain_name like ?"));
		this.getPstm().setString(1, domain);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Specialization> specializations = this.createSpecializationsFromResultSet(this.getRs());
		this.closeAll();

		return specializations;
	}

	public ArrayList<Specialization> intersection(ArrayList<Specialization> list1, ArrayList<Specialization> list2) {
		ArrayList<Specialization> list = new ArrayList<Specialization>();

		for (Specialization specialization1 : list1) {
			for (Specialization specialization2 : list2) {
				if (specialization1.getId() == specialization2.getId()) {
					list.add(specialization1);
				}
			}
		}

		return list;
	}

	public Specialization getLastSpecializationRow() throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn()
				.prepareStatement("select * from students.specialization order by specialization_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Specialization> specializations = this.createSpecializationsFromResultSet(this.getRs());
		this.closeAll();

		return specializations.get(0);
	}

}
