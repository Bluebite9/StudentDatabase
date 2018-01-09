package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Subject;
import util.Util;
import util.DatabaseException;

public class SubjectDBHelper extends Driver {

	public ArrayList<Subject> getAllSubjects() throws SQLException {
		this.openConnection();

		this.setRs(Util.getAllFromTable("subject", this.getConn(), this.getPstm()));

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

	public ArrayList<Subject> getSubjectsBySemester(String semester) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_semester = ?"));
		this.getPstm().setString(1, semester);
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

	public ArrayList<Subject> getSubjectsByCredits(String credits) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_credits = ?"));
		this.getPstm().setString(1, credits);
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

	public ArrayList<Subject> getSubjectsByOptional(String optional) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from subject where subject_optional = ?"));
		this.getPstm().setString(1, optional);
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

	public ArrayList<Subject> find(String name, String specialization, String year, String type, String semester,
			String lecturer, String labProf, String seminProf, String credits, String examType, String optional,
			String active) throws SQLException {
		ArrayList<Subject> subjectName = new ArrayList<>();
		ArrayList<Subject> subjectSpecialization = new ArrayList<>();
		ArrayList<Subject> subjectYear = new ArrayList<>();
		ArrayList<Subject> subjectType = new ArrayList<>();
		ArrayList<Subject> subjectSemester = new ArrayList<>();
		ArrayList<Subject> subjectLecturer = new ArrayList<>();
		ArrayList<Subject> subjectLabProf = new ArrayList<>();
		ArrayList<Subject> subjectSeminProf = new ArrayList<>();
		ArrayList<Subject> subjectCredits = new ArrayList<>();
		ArrayList<Subject> subjectExamType = new ArrayList<>();
		ArrayList<Subject> subjectOptional = new ArrayList<>();
		ArrayList<Subject> subjects = getAllSubjects();

		boolean bActive = true;

		if (active.equals("Nu")) {
			bActive = false;
		}

		if (!name.isEmpty()) {
			subjectName = getSubjectsByName(name);
			subjects = intersection(subjects, subjectName);
		}
		if (!specialization.isEmpty()) {
			subjectSpecialization = getSubjectsBySpecializationName(specialization);
			subjects = intersection(subjects, subjectSpecialization);
		}
		if (!type.isEmpty()) {
			subjectType = getSubjectsByType(type);
			subjects = intersection(subjects, subjectType);
		}
		if (!year.isEmpty()) {
			subjectYear = getSubjectsByName(year);
			subjects = intersection(subjects, subjectYear);
		}
		if (!semester.isEmpty()) {
			subjectSemester = getSubjectsBySemester(semester);
			subjects = intersection(subjects, subjectSemester);
		}
		if (!lecturer.isEmpty()) {
			subjectLecturer = getSubjectsByLecturerName(lecturer);
			subjects = intersection(subjects, subjectLecturer);
		}
		if (!labProf.isEmpty()) {
			subjectLabProf = getSubjectsByLabProfName(labProf);
			subjects = intersection(subjects, subjectLabProf);
		}
		if (!seminProf.isEmpty()) {
			subjectSeminProf = getSubjectsBySeminProfName(name);
			subjects = intersection(subjects, subjectSeminProf);
		}
		if (!credits.isEmpty()) {
			subjectCredits = getSubjectsByCredits(credits);
			subjects = intersection(subjects, subjectCredits);
		}
		if (!examType.isEmpty()) {
			subjectExamType = getSubjectsByExamType(examType);
			subjects = intersection(subjects, subjectExamType);
		}
		if (!optional.isEmpty()) {
			subjectOptional = getSubjectsByOptional(optional);
			subjects = intersection(subjects, subjectOptional);
		}

		for (Subject subject : subjects) {
			if (subject.isActive() != bActive) {
				subjects.remove(subject);
			}
		}

		return subjects;
	}

	private ArrayList<Subject> getSubjectsBySpecializationName(String specialization) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.subject join students.specialization on specialization_id = subject_specialization where specialization_name = ?"));
		this.getPstm().setString(1, specialization);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> subjects = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return subjects;
	}

	public ArrayList<Subject> getSubjectsByLecturerName(String prof) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.subject join students.professor on professor_id = subject_lecturer where professor_name = ?"));
		this.getPstm().setString(1, prof);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> subjects = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return subjects;
	}

	public ArrayList<Subject> getSubjectsByLabProfName(String prof) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.subject join students.professor on professor_id = subject_labProf where professor_name = ?"));
		this.getPstm().setString(1, prof);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> subjects = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return subjects;
	}

	public ArrayList<Subject> getSubjectsBySeminProfName(String prof) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement(
				"select * from students.subject join students.professor on professor_id = subject_seminProf where professor_name = ?"));
		this.getPstm().setString(1, prof);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> subjects = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return subjects;
	}

	public ArrayList<Subject> intersection(ArrayList<Subject> list1, ArrayList<Subject> list2) {
		ArrayList<Subject> list = new ArrayList<Subject>();

		for (Subject subject1 : list1) {
			for (Subject subject2 : list2) {
				if (subject1.getId() == subject2.getId()) {
					list.add(subject1);
				}
			}
		}

		return list;
	}

	public Subject getLastSubjectRow() throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from students.subject order by subject_id desc limit 1"));
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Subject> subjects = this.createSubjectsFromResultSet(this.getRs());
		this.closeAll();

		return subjects.get(0);
	}

	public int update(Subject subject) throws SQLException, DatabaseException {
		this.openConnection();
		subject.validate(getConn(), getPstm(), getRs());

		this.setPstm(this.getConn().prepareStatement(
				"update students.student set subject_name = ?, subject_specialization = ?, subject_year = ?, subject_type = ?, subject_semester = ?, subject_lecturer = ?, subject_labProf = ?, subject_seminProf = ?, subject_credits = ?, subject_examType = ?, subject_optional = ?, subject_active = ? where subject_id = ?"));
		this.getPstm().setString(1, subject.getName());
		this.getPstm().setInt(2, subject.getSpecialization());
		this.getPstm().setInt(3, subject.getYear());
		this.getPstm().setString(4, subject.getType());
		this.getPstm().setInt(5, subject.getSemester());
		this.getPstm().setInt(6, subject.getLecturer());
		this.getPstm().setInt(7, subject.getLabProf());
		this.getPstm().setInt(8, subject.getSeminProf());
		this.getPstm().setInt(9, subject.getCredits());
		this.getPstm().setString(10, subject.getExamType());
		this.getPstm().setBoolean(11, subject.isOptional());
		this.getPstm().setBoolean(12, subject.isActive());
		this.getPstm().setInt(13, subject.getId());

		int update = this.getPstm().executeUpdate();

		return update;
	}

}
