package schemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseException;
import util.Validation;

public class Subject {

	private int id, year, specialization, semester, lecturer, labProf, seminProf, credits;
	private String name, type, examType;
	private boolean optional, active;

	public Subject(int id, String name, int specialization, int year, String type, int semester, int lecturer,
			int labProf, int seminProf, int credits, String examType, boolean optional) {
		this.setId(id);
		this.setName(name);
		this.setSpecialization(specialization);
		this.setYear(year);
		this.setType(type);
		this.setSemester(semester);
		this.setLecturer(lecturer);
		this.setLabProf(labProf);
		this.setSeminProf(seminProf);
		this.setCredits(credits);
		this.setExamType(examType);
		this.setOptional(optional);
		this.setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSpecialization() {
		return specialization;
	}

	public void setSpecialization(int specialization) {
		this.specialization = specialization;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getLecturer() {
		return lecturer;
	}

	public void setLecturer(int lecturer) {
		this.lecturer = lecturer;
	}

	public int getLabProf() {
		return labProf;
	}

	public void setLabProf(int labProf) {
		this.labProf = labProf;
	}

	public int getSeminProf() {
		return seminProf;
	}

	public void setSeminProf(int seminProf) {
		this.seminProf = seminProf;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
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
	
	public void validate(Connection conn, PreparedStatement pstm, ResultSet rs) throws SQLException, DatabaseException {
		Validation.validateName(this.getName());
		Validation.validateYear(this.getYear());
		Validation.findSpecialization(conn, pstm, rs, this.getSpecialization());
		Validation.findProfessor(conn, pstm, rs, this.getLecturer());
		Validation.findProfessor(conn, pstm, rs, this.getSeminProf());
		Validation.findProfessor(conn, pstm, rs, this.getLabProf());
		Validation.validateSemester(this.getSemester());
		Validation.validateCredits(this.getCredits());
		Validation.validateType(this.getType());
		Validation.validateExamType(this.getExamType());
	}

}
