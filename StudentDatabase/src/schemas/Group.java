package schemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseException;
import util.Validation;

public class Group {

	private int id, specialization, year, numberOfStudents;
	private String name;
	private boolean active;

	public Group(int id, String name, int specialization, int year, int numberOfStudents) {
		this.setId(id);
		this.setName(name);
		this.setSpecialization(specialization);
		this.setYear(year);
		this.setNumberOfStudents(numberOfStudents);
		this.setActive(true);
	}

	public Group(String id, String name, String specialization, String year, String numberOfStudents)
			throws DatabaseException {
		this.setId(Integer.parseInt(id));
		this.setName(Validation.validateShortName(name));
		try {
			this.setSpecialization(Integer.parseInt(specialization));
		} catch (Exception e) {
			throw new DatabaseException("Invalid specialization");
		}
		this.setYear(Validation.validateStringYear(year));
		this.setNumberOfStudents(Validation.validateStringNumberOfStudents(numberOfStudents));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpecialization() {
		return specialization;
	}

	public void setSpecialization(int specialization) {
		this.specialization = specialization;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
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
		Validation.validateShortName(this.getName());
		Validation.findSpecialization(conn, pstm, rs, this.getSpecialization());
		Validation.validateYear(this.getYear());
		Validation.validateNumberOfStudents(this.getNumberOfStudents());
	}

}
