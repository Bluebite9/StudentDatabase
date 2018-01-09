package schemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseException;
import util.Validation;

public class Mark {

	private int id, student, mark, subject;
	private String session;
	private String date;
	private boolean active;

	public Mark(int id, int student, int mark, int subject, String session, String date) {
		this.setId(id);
		this.setStudent(student);
		this.setMark(mark);
		this.setSubject(subject);
		this.setSession(session);
		this.setDate(date);
		this.setActive(true);
	}

	public Mark(String id, String student, String mark, String subject, String session, String date)
			throws DatabaseException {
		this.setId(Integer.parseInt(id));
		try {
			this.setStudent(Integer.parseInt(student));
		} catch (Exception e) {
			throw new DatabaseException("Invalid student");
		}
		this.setMark(Validation.validateStringMark(mark));
		try {
			this.setSubject(Integer.parseInt(subject));
		} catch (Exception e) {
			throw new DatabaseException("Invalid subject id");
		}
		this.setSession(Validation.validateSession(session));
		this.setDate(Validation.validateStringDate(date));
		this.setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent() {
		return student;
	}

	public void setStudent(int student) {
		this.student = student;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		String mark = "" + this.getMark();
		return mark;
	}

	public void validate(Connection conn, PreparedStatement pstm, ResultSet rs) throws DatabaseException, SQLException {
		Validation.validateMark(this.getMark());
		Validation.validateSession(this.getSession());
		Validation.validateStringDate(this.getDate());
		Validation.findStudent(conn, pstm, rs, getStudent());
		Validation.findSubject(conn, pstm, rs, getSubject());
	}

}
