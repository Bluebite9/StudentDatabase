package gui;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import schemas.Department;
import schemas.Domain;
import schemas.Faculty;
import schemas.Group;
import schemas.Mark;
import schemas.Professor;
import schemas.Specialization;
import schemas.Student;
import schemas.Subject;

public class Tables {

	public JTable createStudentTableFromArrayList(ArrayList<Student> students) {
		String[] col = { "Id", "Nume", "Prenume", "Ziua de nastere", "Grupa", "Subgrupa", "Anul de inceput", "Anul curent",
				"Restantier", "Credite" };
		
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		JTable table = new JTable();

		tableModel.addRow(col);

		for (int i = 0; i < students.size(); i++) {
			Student student = students.get(i);
			Object[] stud = { student.getId(), student.getName(), student.getSurname(), student.getBirthDate(),
					student.getGroup(), student.getSubgroup(), student.getBeginningYear(), student.getCurrentYear(),
					student.isDebtor(), student.getCreditsNumber() };
			
			tableModel.addRow(stud);
		}

		table.setModel(tableModel);

		return table;
	}

	public JTable createDepartmentTableFromArrayList(ArrayList<Department> departments) {
		String col[] = { "Id", "Nume", "Facultate" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < departments.size(); i++) {
			Department department = departments.get(i);
			Object[] dept = { department.getId(), department.getName(), department.getFaculty() };

			tableModel.addRow(dept);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createDomainTableFromArrayList(ArrayList<Domain> domains) {
		String col[] = { "Id", "Nume", "Facultate" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < domains.size(); i++) {
			Domain domain = domains.get(i);
			Object[] domn = { domain.getId(), domain.getName(), domain.getFaculty() };

			tableModel.addRow(domn);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createFacultyTableFromArrayList(ArrayList<Faculty> faculties) {
		String col[] = { "Id", "Nume", "Adresa" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < faculties.size(); i++) {
			Faculty faculty = faculties.get(i);
			Object[] fac = { faculty.getId(), faculty.getName(), faculty.getAddress() };

			tableModel.addRow(fac);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createGroupTableFromArrayList(ArrayList<Group> groups) {
		String col[] = { "Id", "Nume", "Specializare", "An", "Numar de studenti" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);
			Object[] grp = { group.getId(), group.getName(), group.getSpecialization(), group.getYear(),
					group.getNumberOfStudents() };

			tableModel.addRow(grp);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createMarkTableFromArrayList(ArrayList<Mark> marks) {
		String col[] = { "Id", "Student", "Nota", "Materie", "Sesiune", "Data" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < marks.size(); i++) {
			Mark mark = marks.get(i);
			Object[] mrk = { mark.getId(), mark.getStudent(), mark.getMark(), mark.getSubject(), mark.getSession(),
					mark.getDate() };

			tableModel.addRow(mrk);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createProfessorTableFromArrayList(ArrayList<Professor> professors) {
		String col[] = { "Id", "Prenume", "Nume", "Ziua de nastere", "Departament", "Gradul" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < professors.size(); i++) {
			Professor professor = professors.get(i);
			Object[] prof = { professor.getId(), professor.getName(), professor.getSurname(), professor.getBirthDate(),
					professor.getDepartment(), professor.getDegree() };

			tableModel.addRow(prof);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createSpecializationTableFromArrayList(ArrayList<Specialization> specializations) {
		String col[] = { "Id", "Nume", "Domeniu" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < specializations.size(); i++) {
			Specialization specialization = specializations.get(i);
			Object[] spec = { specialization.getId(), specialization.getName(), specialization.getDomain() };

			tableModel.addRow(spec);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createSubjectTableFromArrayList(ArrayList<Subject> subjects) {
		String col[] = { "Id", "Nume", "Specializare", "Year", "Tipul", "Semestrul", "Profesorul de curs",
				"Profesorul de laborator", "Profesorul de seminar", "Credite", "Tipul de examinare", "Optional" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		tableModel.addRow(col);

		for (int i = 0; i < subjects.size(); i++) {
			Subject subject = subjects.get(i);
			Object[] subj = { subject.getId(), subject.getName(), subject.getSpecialization(), subject.getYear(),
					subject.getType(), subject.getSemester(), subject.getLecturer(), subject.getLabProf(), subject.getSeminProf(),
					subject.getCredits(), subject.getExamType(), subject.isOptional() };

			tableModel.addRow(subj);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

}
