package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schemas.Professor;
import util.DBUtil;
import util.DatabaseException;

public class ProfessorDBHelper extends Driver {

	public ArrayList<Professor> getAllProfessors() throws SQLException {
		this.openConnection();

		this.setRs(DBUtil.getAllFromTable("professor", this.getConn(), this.getPstm()));

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> getProfessorById(int id) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from professor where professor_id = ?"));
		this.getPstm().setInt(1, id);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> getProfessorByName(String name) throws SQLException {
		String newName = "%" + name + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from professor where professor_name like ?"));
		this.getPstm().setString(1, newName);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> getProfessorBySurname(String surname) throws SQLException {
		String newSurname = "%" + surname + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from professor where professor_surname like ?"));
		this.getPstm().setString(1, newSurname);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> getProfessorByNameAndSurname(String name, String surname) throws SQLException {
		String newName = "%" + name + "%";
		String newSurname = "%" + surname + "%";
		this.openConnection();
		this.setPstm(this.getConn()
				.prepareStatement("select * from professor where professor_name like ? and professor_surname like ?"));
		this.getPstm().setString(1, newName);
		this.getPstm().setString(2, newSurname);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> getProfessorByBirthDate(Date birthDate) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from professor where professor_birthdate like ?"));
		this.getPstm().setDate(1, birthDate);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> getProfessorByDepartment(int department) throws SQLException {
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from professor where professor_department = ?"));
		this.getPstm().setInt(1, department);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> getProfessorByDegree(String degree) throws SQLException {
		String newDegree = "%" + degree + "%";
		this.openConnection();
		this.setPstm(this.getConn().prepareStatement("select * from professor where professor_degree like ?"));
		this.getPstm().setString(1, newDegree);
		this.setRs(this.getPstm().executeQuery());

		ArrayList<Professor> professors = this.createProfessorsFromResultSet(this.getRs());
		this.closeAll();

		return professors;
	}

	public ArrayList<Professor> createProfessorsFromResultSet(ResultSet rs) throws SQLException {
		ArrayList<Professor> professors = new ArrayList<Professor>();
		while (rs.next()) {
			professors.add(
					new Professor(rs.getInt("professor_id"), rs.getString("professor_name"), rs.getString("professor_surname"),
							rs.getDate("professor_birthdate"), rs.getInt("professor_department"), rs.getString("professor_degree")));
		}

		return professors;
	}

	public int insert(Professor professor) throws SQLException, DatabaseException {
		this.openConnection();
		professor.validate(this.getConn(), this.getPstm(), this.getRs());

		this.setPstm(this.getConn().prepareStatement(
				"insert into students.professor(professor_name, professor_surname, professor_birthdate, professor_department, professor_degree) values (?, ?, ?, ?, ?);"));
		this.getPstm().setString(1, professor.getName());
		this.getPstm().setString(2, professor.getSurname());
		this.getPstm().setDate(3, professor.getBirthDate());
		this.getPstm().setInt(4, professor.getDepartment());
		this.getPstm().setString(5, professor.getDegree());
		int inserted = this.getPstm().executeUpdate();
		this.closeAll();

		return inserted;
	}

	public int permanentDelete(int id) throws DatabaseException, SQLException {
		this.openConnection();

		this.setPstm(this.getConn().prepareStatement("delete from students.professor where professor_id = ?;"));
		this.getPstm().setInt(1, id);
		int deleted = this.getPstm().executeUpdate();

		this.closeAll();

		return deleted;
	}

}
