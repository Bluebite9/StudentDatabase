package schemas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseException;
import util.Validation;

public class Professor {

	private int id, department;
	private String name, surname, degree;
	private Date birthDate;
	private boolean active;

	public Professor(int id, String name, String surname, Date birthDate, int department, String degree) {
		this.setId(id);
		this.setName(name);
		this.setSurname(surname);
		this.setBirthDate(birthDate);
		this.setDepartment(department);
		this.setDegree(degree);
		this.setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String toString() {
		return this.getName() + " " + this.getSurname();
	}

	public void validate(Connection conn, PreparedStatement pstm, ResultSet rs)
			throws DatabaseException, SQLException {
		Validation.validateMediumName(this.getName());
		Validation.validateMediumName(this.getSurname());
		Validation.validateDegree(this.getDegree());
		Validation.validateDate(this.getBirthDate());
		Validation.findDepartment(conn, pstm, rs, this.getDepartment());
	}

}
