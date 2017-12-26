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

	public JTable createStudentTableFromArrayList(ArrayList<Student> students, ArrayList<Group> groups) {
		String[] col = { "Id", "Nume", "Prenume", "Ziua de nastere", "Grupa", "Subgrupa", "Anul de inceput", "Anul curent",
				"Restantier", "Credite" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Student student : students) {
			Group studentGroup = null;
			String debtor = "Nu";
			if (student.isDebtor()) {
				debtor = "Da";
			}
			for (Group group : groups) {
				if (student.getGroup() == group.getId()) {
					studentGroup = group;
					break;
				}
			}
			Object[] stud = { student.getId(), student.getName(), student.getSurname(), student.getBirthDate(),
					studentGroup.getName(), student.getSubgroup(), student.getBeginningYear(), student.getCurrentYear(), debtor,
					student.getCreditsNumber() };

			tableModel.addRow(stud);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createDepartmentTableFromArrayList(ArrayList<Department> departments, ArrayList<Faculty> faculties) {
		String col[] = { "Id", "Nume", "Facultate" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Department department : departments) {
			Faculty depFaculty = null;
			for (Faculty faculty : faculties) {
				if (department.getFaculty() == faculty.getId()) {
					depFaculty = faculty;
					break;
				}
			}
			Object[] dept = { department.getId(), department.getName(), depFaculty.getName() };

			tableModel.addRow(dept);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createDomainTableFromArrayList(ArrayList<Domain> domains, ArrayList<Faculty> faculties) {
		String col[] = { "Id", "Nume", "Facultate" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Domain domain : domains) {
			Faculty domFaculty = null;
			for (Faculty faculty : faculties) {
				if (domain.getFaculty() == faculty.getId()) {
					domFaculty = faculty;
					break;
				}
			}
			Object[] domn = { domain.getId(), domain.getName(), domFaculty.getName() };

			tableModel.addRow(domn);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createFacultyTableFromArrayList(ArrayList<Faculty> faculties) {
		String col[] = { "Id", "Nume", "Adresa" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Faculty faculty : faculties) {
			Object[] fac = { faculty.getId(), faculty.getName(), faculty.getAddress() };

			tableModel.addRow(fac);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createGroupTableFromArrayList(ArrayList<Group> groups, ArrayList<Specialization> specializations) {
		String col[] = { "Id", "Nume", "Specializare", "An", "Numar de studenti" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Group group : groups) {
			Specialization groupSpec = null;
			for (Specialization specialization : specializations) {
				if (group.getSpecialization() == specialization.getId()) {
					groupSpec = specialization;
					break;
				}
			}
			Object[] grp = { group.getId(), group.getName(), groupSpec.getName(), group.getYear(),
					group.getNumberOfStudents() };

			tableModel.addRow(grp);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createMarkTableFromArrayList(ArrayList<Mark> marks, ArrayList<Student> students,
			ArrayList<Subject> subjects) {
		String col[] = { "Id", "Student", "Nota", "Materie", "Sesiune", "Data" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Mark mark : marks) {
			Student markStudent = null;
			Subject markSubject = null;

			for (Student student : students) {
				if (mark.getStudent() == student.getId()) {
					markStudent = student;
					break;
				}
			}

			for (Subject subject : subjects) {
				if (mark.getSubject() == subject.getId()) {
					markSubject = subject;
					break;
				}
			}

			Object[] mrk = { mark.getId(), markStudent.getSurname() + " " + markStudent.getName(), mark.getMark(),
					markSubject.getName(), mark.getSession(), mark.getDate() };

			tableModel.addRow(mrk);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createProfessorTableFromArrayList(ArrayList<Professor> professors, ArrayList<Department> departments) {
		String col[] = { "Id", "Prenume", "Nume", "Ziua de nastere", "Departament", "Gradul" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Professor professor : professors) {
			Department profDep = null;

			for (Department department : departments) {
				if (professor.getDepartment() == department.getId()) {
					profDep = department;
					break;
				}
			}

			Object[] prof = { professor.getId(), professor.getName(), professor.getSurname(), professor.getBirthDate(),
					profDep.getName(), professor.getDegree() };

			tableModel.addRow(prof);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createSpecializationTableFromArrayList(ArrayList<Specialization> specializations,
			ArrayList<Domain> domains) {
		String col[] = { "Id", "Nume", "Domeniu" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Specialization specialization : specializations) {
			Domain specDom = null;

			for (Domain domain : domains) {
				if (specialization.getDomain() == domain.getId()) {
					specDom = domain;
					break;
				}
			}

			Object[] spec = { specialization.getId(), specialization.getName(), specDom.getName() };

			tableModel.addRow(spec);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

	public JTable createSubjectTableFromArrayList(ArrayList<Subject> subjects, ArrayList<Professor> professors,
			ArrayList<Specialization> specializations) {
		String col[] = { "Id", "Nume", "Specializare", "Year", "Tipul", "Semestrul", "Profesorul de curs",
				"Profesorul de laborator", "Profesorul de seminar", "Credite", "Tipul de examinare", "Optional" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (Subject subject : subjects) {
			Professor subLecturer = null;
			Professor subSeminProf = null;
			Professor subLabProf = null;
			Specialization subSpec = null;
			String optional = "Nu";

			for (Specialization specialization : specializations) {
				if (subject.getSpecialization() == specialization.getId()) {
					subSpec = specialization;
					break;
				}
			}

			for (Professor professor : professors) {
				if (subject.getLecturer() == professor.getId()) {
					subLecturer = professor;
				}

				if (subject.getSeminProf() == professor.getId()) {
					subSeminProf = professor;
				}

				if (subject.getLabProf() == professor.getId()) {
					subLabProf = professor;
				}
			}
			
			if (subject.isOptional()) {
				optional = "Da";
			}

			Object[] subj = { subject.getId(), subject.getName(), subSpec.getName(), subject.getYear(), subject.getType(),
					subject.getSemester(), subLecturer.getSurname() + " " + subLecturer.getName(),
					subLabProf.getSurname() + " " + subLabProf.getName(),
					subSeminProf.getSurname() + " " + subSeminProf.getName(), subject.getCredits(), subject.getExamType(),
					optional };

			tableModel.addRow(subj);
		}

		JTable table = new JTable(tableModel);

		return table;
	}

}
