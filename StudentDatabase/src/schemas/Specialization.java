package schemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseException;
import util.Validation;

public class Specialization {

	private int id, domain;
	private String name;
	private boolean active;

	public Specialization(int id, String name, int domain) {
		this.setId(id);
		this.setName(name);
		this.setDomain(domain);
		this.setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDomain() {
		return domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String toString() {
		return this.getName();
	}

	public void validate(Connection conn, PreparedStatement pstm, ResultSet rs)
			throws SQLException, DatabaseException {
		Validation.validateMediumName(this.getName());
		Validation.findDomain(conn, pstm, rs, this.getDomain());
	}

}
