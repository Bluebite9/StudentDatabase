package schemas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseException;
import util.Validation;

public class Student {

	private int id, group, beginningYear, currentYear, creditsNumber;
	private String name, surname, subgroup, scholarshipType;
	private boolean debtor, active;
	private Date birthDate;

	public Student(int id, String name, String surname, Date birthDate, int group, String subgroup, int beginningYear,
			int currentYear, String scholarshipType, boolean debtor, int creditsNumber) {
		this.setId(id);
		this.setName(name);
		this.setSurname(surname);
		this.setBirthDate(birthDate);
		this.setGroup(group);
		this.setSubgroup(subgroup);
		this.setBeginningYear(beginningYear);
		this.setCurrentYear(currentYear);
		this.setScholarshipType(scholarshipType);
		this.setDebtor(debtor);
		this.setCreditsNumber(creditsNumber);
		this.setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getBeginningYear() {
		return beginningYear;
	}

	public void setBeginningYear(int beginningYear) {
		this.beginningYear = beginningYear;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public int getCreditsNumber() {
		return creditsNumber;
	}

	public void setCreditsNumber(int creditsNumber) {
		this.creditsNumber = creditsNumber;
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

	public String getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(String subgroup) {
		this.subgroup = subgroup;
	}

	public String getScholarshipType() {
		return scholarshipType;
	}

	public void setScholarshipType(String scholarshipType) {
		this.scholarshipType = scholarshipType;
	}

	public boolean isDebtor() {
		return debtor;
	}

	public void setDebtor(boolean debtor) {
		this.debtor = debtor;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String toString() {
		return this.getName() + " " + this.getSurname();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void validate(Connection conn, PreparedStatement pstm, ResultSet rs)
			throws SQLException, DatabaseException {
		Validation.validateName(this.getName());
		Validation.validateName(this.getSurname());
		Validation.findGroup(conn, pstm, rs, this.getGroup());
		Validation.validateDate(this.getBirthDate());
		Validation.validateSubgroup(this.getSubgroup());
		Validation.validateBeginningYear(this.getBeginningYear());
		Validation.validateYear(this.getCurrentYear());
		Validation.validateCreditsNumber(this.getCreditsNumber());
		Validation.validateScholarshipType(this.getScholarshipType());
	}

}