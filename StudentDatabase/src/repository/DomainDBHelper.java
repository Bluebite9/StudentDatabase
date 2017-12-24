package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Domain;
import util.DBUtil;
import util.DatabaseException;

public class DomainDBHelper extends Driver {

	public ArrayList<Domain> getAllDomains() throws SQLException {
		this.openConnection();
		this.setRs(DBUtil.getAllFromTable("domain", this.getConn(), this.getPstm()));

		ArrayList<Domain> domains = this.createDomainsFromResultSet(this.getRs());
		this.closeAll();

		return domains;
	}

	public ArrayList<Domain> getDomainById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from domain where domain_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Domain> domains = this.createDomainsFromResultSet(this.getRs());
		this.closeAll();

		return domains;
	}

	public ArrayList<Domain> getDomainsByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from domain where domain_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Domain> domains = this.createDomainsFromResultSet(this.getRs());
		this.closeAll();

		return domains;
	}

	public ArrayList<Domain> getDomainsByFaculty(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from domain where domain_faculty = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Domain> domains = this.createDomainsFromResultSet(this.getRs());
		this.closeAll();

		return domains;
	}

	public ArrayList<Domain> createDomainsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Domain> domains = new ArrayList<Domain>();
		while (rs.next()) {
			domains.add(new Domain(rs.getInt("domain_id"), rs.getString("domain_name"), rs.getInt("domain_faculty")));
		}

		return domains;
	}

	public int insert(Domain domain) throws DatabaseException, SQLException {
		this.openConnection();
		domain.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement("insert into domain (domain_name, domain_faculty) values (? ,?)"));
		this.getPstm().setString(1, domain.getName());
		this.getPstm().setInt(2, domain.getFaculty());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.domain where domain_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

}
