package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import repository.DepartmentDBHelper;
import repository.DomainDBHelper;
import repository.FacultyDBHelper;
import repository.GroupDBHelper;
import repository.MarkDBHelper;
import repository.ProfessorDBHelper;
import repository.SpecializationDBHelper;
import repository.StudentDBHelper;
import repository.SubjectDBHelper;

public class Button {

	private JButton button;
	private ButtonIds id;

	Button(ButtonIds id) {
		if (id == ButtonIds.Student) {
			allStudentsButton();
		}

		if (id == ButtonIds.Department) {
			allDepartmentsButton();
		}

		if (id == ButtonIds.Domain) {
			allDomainsButton();
		}

		if (id == ButtonIds.Faculty) {
			allFacultiesButton();
		}

		if (id == ButtonIds.Group) {
			allGroupsButton();
		}

		if (id == ButtonIds.Mark) {
			allMarksButton();
		}

		if (id == ButtonIds.Professor) {
			allProfessorsButton();
		}

		if (id == ButtonIds.Specialization) {
			allSpecializationsButton();
		}

		if (id == ButtonIds.Subject) {
			allSubjectsButton();
		}
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public ButtonIds getId() {
		return id;
	}

	public void setId(ButtonIds id) {
		this.id = id;
	}

	private void allStudentsButton() {
		button = new JButton("Get all students");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				StudentDBHelper studentDBHelper = new StudentDBHelper();
				try {
					window.remove(window.getTablePanel());
					window.getTablePanel().removeAll();
					window.getTablePanel().add(table.createStudentTableFromArrayList(studentDBHelper.getAllStudents()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allDepartmentsButton() {
		this.button = new JButton("Get all departments");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createDepartmentTableFromArrayList(departmentDBHelper.getAllDepartments()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allDomainsButton() {
		this.button = new JButton("Get all domains");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				DomainDBHelper domainDBHelper = new DomainDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createDomainTableFromArrayList(domainDBHelper.getAllDomains()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allFacultiesButton() {
		this.button = new JButton("Get all faculties");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createFacultyTableFromArrayList(facultyDBHelper.getAllFaculties()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allGroupsButton() {
		this.button = new JButton("Get all groups");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				GroupDBHelper groupDBHelper = new GroupDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createGroupTableFromArrayList(groupDBHelper.getAllGroups()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allMarksButton() {
		this.button = new JButton("Get all marks");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				MarkDBHelper markDBHelper = new MarkDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createMarkTableFromArrayList(markDBHelper.getAllMarks()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allProfessorsButton() {
		this.button = new JButton("Get all professors");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createProfessorTableFromArrayList(professorDBHelper.getAllProfessors()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allSpecializationsButton() {
		this.button = new JButton("Get all specializations");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createSpecializationTableFromArrayList(specializationDBHelper.getAllSpecializations()));
					window.add(window.getTablePanel());
					window.pack();
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void allSubjectsButton() {
		this.button = new JButton("Get all subject");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window window = Window.getGeneralWindow();
				Tables table = new Tables();
				SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
				try {
					window.removeUnnecessaryItems();
					window.getTablePanel().add(table.createSubjectTableFromArrayList(subjectDBHelper.getAllSubjects()));
					window.add(window.getTablePanel());
					window.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
