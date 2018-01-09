package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Mark;
import util.Util;
import util.DatabaseException;

public class MarkDBHelper extends Driver {

	public ArrayList<Mark> getAllMarks() throws SQLException {
		this.openConnection();

		this.setRs(Util.getAllFromTable("mark", this.getConn(), this.getPstm()));

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarkById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarksByStudent(int student) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_student = ?"));
		this.getPstm().setInt(1, student);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarksByMark(String mark) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_mark = ?"));
		this.getPstm().setString(1, mark);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarksBySession(String session) throws SQLException {
		String newSession = "%" + session + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_session like ?"));
		this.getPstm().setString(1, newSession);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarksByDate(String date) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_date = ?"));
		this.getPstm().setString(1, date);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarksBySubject(int subject) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_subject = ?"));
		this.getPstm().setInt(1, subject);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarksByStudentName(String student) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from mark join students.student on student_id = mark_student where student_name = ?"));
		this.getPstm().setString(1, student);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> getMarksBySubjectName(String subject) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from mark join students.subject on subject_id = mark_subject where subject_name = ?"));
		this.getPstm().setString(1, subject);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks;
	}

	public ArrayList<Mark> createMarksFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Mark> marks = new ArrayList<Mark>();
		while (rs.next()) {
			marks.add(new Mark(rs.getInt("mark_id"), rs.getInt("mark_student"), rs.getInt("mark_mark"),
					rs.getInt("mark_subject"), rs.getString("mark_session"), rs.getString("mark_date")));
		}

		return marks;
	}

	public int insert(Mark mark) throws SQLException, DatabaseException {
		this.openConnection();
		mark.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement(
				"insert into mark (mark_student, mark_mark, mark_subject, mark_session, mark_date) values (?, ?, ?, ?, ?);"));
		this.getPstm().setInt(1, mark.getStudent());
		this.getPstm().setInt(2, mark.getMark());
		this.getPstm().setInt(3, mark.getSubject());
		this.getPstm().setString(4, mark.getSession());
		this.getPstm().setString(5, mark.getDate());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.mark where mark_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

	public ArrayList<Mark> find(String student, String mark, String subject, String session, String date, String active)
			throws SQLException {
		ArrayList<Mark> markStudent = new ArrayList<>();
		ArrayList<Mark> markMark = new ArrayList<>();
		ArrayList<Mark> markSubject = new ArrayList<>();
		ArrayList<Mark> markDate = new ArrayList<>();
		ArrayList<Mark> markSession = new ArrayList<>();
		ArrayList<Mark> marks = getAllMarks();

		boolean bActive = true;

		if (active.equals("Nu")) {
			bActive = false;
		}

		if (!student.isEmpty()) {
			markStudent = getMarksByStudentName(student);
			marks = intersection(marks, markStudent);
		}
		if (!mark.isEmpty()) {
			markMark = getMarksByMark(mark);
			marks = intersection(marks, markMark);
		}
		if (!subject.isEmpty()) {
			markSubject = getMarksBySubjectName(subject);
			marks = intersection(marks, markSubject);
		}
		if (!session.isEmpty()) {
			markSession = getMarksBySession(session);
			marks = intersection(marks, markSession);
		}
		if (!date.isEmpty()) {
			markDate = getMarksByDate(date);
			marks = intersection(marks, markDate);
		}
		
		for (Mark mark2 : marks) {
			if (mark2.isActive() != bActive) {
				marks.remove(mark2);
			}
		}

		return marks;
	}

	public ArrayList<Mark> intersection(ArrayList<Mark> list1, ArrayList<Mark> list2) {
		ArrayList<Mark> list = new ArrayList<Mark>();

		for (Mark mark1 : list1) {
			for (Mark mark2 : list2) {
				if (mark1.getId() == mark2.getId()) {
					list.add(mark1);
				}
			}
		}

		return list;
	}

	public int update(Mark mark) throws SQLException, DatabaseException {
		this.openConnection();
		mark.validate(getConn(), getPstm(), getRs());

		this.setPstm(this.getConn().prepareStatement(
				"update students.mark set mark_student = ?, mark_mark = ?, mark_subject = ?, mark_session = ?, mark_date = ?, mark_active = ? where mark_id = ?"));
		this.getPstm().setInt(1, mark.getStudent());
		this.getPstm().setInt(2, mark.getMark());
		this.getPstm().setInt(3, mark.getSubject());
		this.getPstm().setString(4, mark.getSession());
		this.getPstm().setString(5, mark.getDate());
		this.getPstm().setBoolean(6, mark.isActive());
		this.getPstm().setInt(7, mark.getId());
		int update = this.getPstm().executeUpdate();

		return update;
	}

	public Mark getLastMarkRow() throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from students.mark order by mark_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Mark> marks = this.createMarksFromResultSet(this.getRs());
		this.closeAll();

		return marks.get(0);
	}

}
