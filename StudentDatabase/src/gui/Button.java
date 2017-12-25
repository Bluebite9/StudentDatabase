package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
				window.removeUnnecessaryItems();
				JPanel panel = window.getTablePanel();
				try {
					JTable jtable = table.createStudentTableFromArrayList(studentDBHelper.getAllStudents());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				window.removeUnnecessaryItems();
				JPanel panel = window.getTablePanel();
				try {
					JTable jtable = table.createDepartmentTableFromArrayList(departmentDBHelper.getAllDepartments());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				window.removeUnnecessaryItems();
				JPanel panel = window.getTablePanel();
				try {
					JTable jtable = table.createDomainTableFromArrayList(domainDBHelper.getAllDomains());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				JPanel panel = window.getTablePanel();
				window.removeUnnecessaryItems();
				try {
					JTable jtable = table.createFacultyTableFromArrayList(facultyDBHelper.getAllFaculties());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				JPanel panel = window.getTablePanel();
				window.removeUnnecessaryItems();
				try {
					JTable jtable = table.createGroupTableFromArrayList(groupDBHelper.getAllGroups());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				window.removeUnnecessaryItems();
				JPanel panel = window.getTablePanel();
				try {
					JTable jtable = table.createMarkTableFromArrayList(markDBHelper.getAllMarks());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				window.removeUnnecessaryItems();
				JPanel panel = window.getTablePanel();
				try {
					JTable jtable = table.createProfessorTableFromArrayList(professorDBHelper.getAllProfessors());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				window.removeUnnecessaryItems();
				JPanel panel = window.getTablePanel();
				try {
					JTable jtable = table.createSpecializationTableFromArrayList(specializationDBHelper.getAllSpecializations());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.pack();
				window.setVisible(true);
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
				window.removeUnnecessaryItems();
				JPanel panel = window.getTablePanel();
				try {
					JTable jtable = table.createSubjectTableFromArrayList(subjectDBHelper.getAllSubjects());
					panel.add(new JScrollPane(jtable));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				window.add(panel);
				window.setVisible(true);
			}
		});
	}

}
