package schemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseException;
import util.Validation;

public class Domain {

	private int id, faculty;
	private String name;
	private boolean active;

	public Domain(int id, String name, int faculty) {
		this.setId(id);
		this.setName(name);
		this.setFaculty(faculty);
		this.setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFaculty() {
		return faculty;
	}

	public void setFaculty(int faculty) {
		this.faculty = faculty;
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
	
	public void validate(Connection conn, PreparedStatement pstm, ResultSet rs) throws DatabaseException, SQLException {
		Validation.validateName(this.getName());
		Validation.findFaculty(conn, pstm, rs, this.getFaculty());
	}

}
