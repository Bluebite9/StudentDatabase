package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Domain;
import util.Util;
import util.DatabaseException;

public class DomainDBHelper extends Driver {

	public ArrayList<Domain> getAllDomains() throws SQLException {
		this.openConnection();
		this.setRs(Util.getAllFromTable("domain", this.getConn(), this.getPstm()));

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

	public ArrayList<Domain> getDomainsByFacultyName(String faculty) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from domain join students.faculty on faculty_id = domain_faculty where faculty_name = ?"));
		this.getPstm().setString(1, faculty);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Domain> domains = this.createDomainsFromResultSet(this.getRs());
		this.closeAll();

		return domains;
	}

	public Domain getLastDomainRow() throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from students.domain order by domain_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Domain> domains = this.createDomainsFromResultSet(this.getRs());
		this.closeAll();

		return domains.get(0);
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

	public ArrayList<Domain> find(String name, String faculty, String active) throws SQLException {
		ArrayList<Domain> domainName = new ArrayList<>();
		ArrayList<Domain> domainFaculty = new ArrayList<>();
		ArrayList<Domain> domains = getAllDomains();

		boolean bActive = true;

		if (active.equals("Nu")) {
			bActive = false;
		}

		if (!name.isEmpty()) {
			domainName = getDomainsByName(name);
			domains = intersection(domains, domainName);
		}
		if (!faculty.isEmpty()) {
			domainFaculty = getDomainsByFacultyName(faculty);
			domains = intersection(domains, domainFaculty);
		}

		for (Domain domain : domains) {
			if (domain.isActive() != bActive) {
				domains.remove(domain);
			}
		}

		return domains;
	}

	public ArrayList<Domain> intersection(ArrayList<Domain> list1, ArrayList<Domain> list2) {
		ArrayList<Domain> list = new ArrayList<Domain>();

		for (Domain domain1 : list1) {
			for (Domain domain2 : list2) {
				if (domain1.getId() == domain2.getId()) {
					list.add(domain1);
				}
			}
		}

		return list;
	}

	public int update(Domain domain) throws SQLException, DatabaseException {
		this.openConnection();
		domain.validate(getConn(), getPstm(), getRs());

		this.setPstm(this.getConn().prepareStatement(
				"update students.domain set domain_name = ?, domain_faculty = ?, domain_active = ? where domain_id = ?"));
		this.getPstm().setString(1, domain.getName());
		this.getPstm().setInt(2, domain.getFaculty());
		this.getPstm().setBoolean(3, domain.isActive());
		this.getPstm().setInt(4, domain.getId());
		int update = this.getPstm().executeUpdate();

		return update;
	}

}
