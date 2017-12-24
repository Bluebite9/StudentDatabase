package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Mark;
import util.DBUtil;
import util.DatabaseException;

public class MarkDBHelper extends Driver {

	public ArrayList<Mark> getAllMarks() throws SQLException {
		this.openConnection();

		this.setRs(DBUtil.getAllFromTable("mark", this.getConn(), this.getPstm()));

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

	public ArrayList<Mark> getMarksByMark(int mark) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_mark = ?"));
		this.getPstm().setInt(1, mark);
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

	public ArrayList<Mark> getMarksByDate(Date date) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from mark where mark_date = ?"));
		this.getPstm().setDate(1, date);
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

	public ArrayList<Mark> createMarksFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Mark> marks = new ArrayList<Mark>();
		while (rs.next()) {
			marks.add(new Mark(rs.getInt("mark_id"), rs.getInt("mark_student"), rs.getInt("mark_mark"),
					rs.getInt("mark_subject"), rs.getString("mark_session"), rs.getDate("mark_date")));
		}

		return marks;
	}

	public int insert(Mark mark) throws SQLException, DatabaseException {
		this.openConnection();
		mark.validate(this.getConn(), this.getPstm(), this.getRs(), mark.getStudent(), mark.getSubject());

		this.setPstm(this.getConn().prepareStatement(
				"insert into mark (mark_student, mark_mark, mark_subject, mark_session, mark_date) values (?, ?, ?, ?, ?);"));
		this.getPstm().setInt(1, mark.getStudent());
		this.getPstm().setInt(2, mark.getMark());
		this.getPstm().setInt(3, mark.getSubject());
		this.getPstm().setString(4, mark.getSession());
		this.getPstm().setDate(5, mark.getDate());
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

}
