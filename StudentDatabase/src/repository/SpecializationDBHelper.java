package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Specialization;
import util.DBUtil;
import util.DatabaseException;

public class SpecializationDBHelper extends Driver {

	public ArrayList<Specialization> getAllSpecializations() throws SQLException {
		this.openConnection();

		this.setRs(DBUtil.getAllFromTable("specialization", this.getConn(), this.getPstm()));

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

		this.setPstm(this.getConn().prepareStatement("insert into students.specialization (specialization_name, specialization_domain) values (?, ?);"));
		this.getPstm().setString(1, specialization.getName());
		this.getPstm().setInt(2, specialization.getDomain());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement( "delete from students.specialization where specialization_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

}
