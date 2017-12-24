package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Subject;
import util.DBUtil;
import util.DatabaseException;

public class SubjectDBHelper extends Driver {

	public ArrayList<Subject> getAllSubjects() throws SQLException {
		this.openConnection();

		this.setRs(DBUtil.getAllFromTable("subject", this.getConn(), this.getPstm()));

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsBySpecialization(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_specialization = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByYear(int year) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_year = ?"));
		this.getPstm().setInt(1, year);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByType(String type) throws SQLException {
		String newType = "%" + type + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_type like ?"));
		this.getPstm().setString(1, newType);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsBySemester(int semester) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_semester = ?"));
		this.getPstm().setInt(1, semester);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByLecturer(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_lecturer = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByLabProf(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_labProf = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsBySeminProf(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_seminProf = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByCredits(int credits) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_credits = ?"));
		this.getPstm().setInt(1, credits);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByExamType(String examType) throws SQLException {
		String newExamType = "%" + examType + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_examType like ?"));
		this.getPstm().setString(1, newExamType);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> getSubjectsByOptional(boolean optional) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_optional = ?"));
		this.getPstm().setBoolean(1, optional);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> students = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return students;
	}

	public ArrayList<Subject> createSubjectsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Subject> subjects = new ArrayList<Subject>();

		while (rs.next()) {
			subjects
					.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getInt("subject_specialization"),
							rs.getInt("subject_year"), rs.getString("subject_type"), rs.getInt("subject_semester"),
							rs.getInt("subject_lecturer"), rs.getInt("subject_labProf"), rs.getInt("subject_seminProf"),
							rs.getInt("subject_credits"), rs.getString("subject_examType"), rs.getBoolean("subject_optional")));
		}

		return subjects;
	}

	public int insert(Subject subject) throws SQLException, DatabaseException {
		this.openConnection();
		subject.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement(
				"insert into students.subject (subject_name, subject_specialization, subject_year, subject_type, subject_semester, subject_lecturer, subject_labProf, subject_seminProf, subject_examType, subject_optional, subject_credits) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"));
		this.getPstm().setString(1, subject.getName());
		this.getPstm().setInt(2, subject.getSpecialization());
		this.getPstm().setInt(3, subject.getYear());
		this.getPstm().setString(4, subject.getType());
		this.getPstm().setInt(5, subject.getSemester());
		this.getPstm().setInt(6, subject.getLecturer());
		this.getPstm().setInt(7, subject.getLabProf());
		this.getPstm().setInt(8, subject.getSeminProf());
		this.getPstm().setString(9, subject.getExamType());
		this.getPstm().setBoolean(10, subject.isOptional());
		this.getPstm().setInt(11, subject.getCredits());
		int inserted = this.getPstm().executeUpdate();

		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();
		
		this.setPstm(this.getConn().prepareStatement("delete from students.subject where subject_id = ?"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

}
