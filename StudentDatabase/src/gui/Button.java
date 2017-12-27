package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
import schemas.Department;
import schemas.Domain;
import schemas.Faculty;
import schemas.Group;
import schemas.Mark;
import schemas.Professor;
import schemas.Specialization;
import schemas.Student;
import schemas.Subject;

public class Button {

	private JButton button;
	private ButtonIds id;
	private DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
	private DomainDBHelper domainDBHelper = new DomainDBHelper();
	private FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
	private GroupDBHelper groupDBHelper = new GroupDBHelper();
	private MarkDBHelper markDBHelper = new MarkDBHelper();
	private ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
	private SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
	private StudentDBHelper studentDBHelper = new StudentDBHelper();
	private SubjectDBHelper subjectDBHelper = new SubjectDBHelper();
	private Window window = Window.getGeneralWindow();
	private Tables table = new Tables();
	private JPanel panel = window.getTablePanel();

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
				window.removeTablePanelAndItems();
				try {
					ArrayList<Student> students = studentDBHelper.getAllStudents();
					ArrayList<Group> groups = groupDBHelper.getAllGroups();
					JTable jtable = table.createStudentTableFromArrayList(students, groups);
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
				window.removeTablePanelAndItems();
				try {
					ArrayList<Department> departments = departmentDBHelper.getAllDepartments();
					ArrayList<Faculty> faculties = facultyDBHelper.getAllFaculties();
					JTable jtable = table.createDepartmentTableFromArrayList(departments, faculties);
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
				window.removeTablePanelAndItems();
				try {
					ArrayList<Domain> domains = domainDBHelper.getAllDomains();
					ArrayList<Faculty> faculties = facultyDBHelper.getAllFaculties();
					JTable jtable = table.createDomainTableFromArrayList(domains, faculties);
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
				window.removeTablePanelAndItems();
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
				window.removeTablePanelAndItems();
				try {
					ArrayList<Group> groups = groupDBHelper.getAllGroups();
					ArrayList<Specialization> specializations = specializationDBHelper.getAllSpecializations();
					JTable jtable = table.createGroupTableFromArrayList(groups, specializations);
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
				window.removeTablePanelAndItems();
				try {
					ArrayList<Mark> marks = markDBHelper.getAllMarks();
					ArrayList<Student> students = studentDBHelper.getAllStudents();
					ArrayList<Subject> subjects = subjectDBHelper.getAllSubjects();
					JTable jtable = table.createMarkTableFromArrayList(marks, students, subjects);
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
				window.removeTablePanelAndItems();
				try {
					ArrayList<Professor> professors = professorDBHelper.getAllProfessors();
					ArrayList<Department> departments = departmentDBHelper.getAllDepartments();
					JTable jtable = table.createProfessorTableFromArrayList(professors, departments);
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
				window.removeTablePanelAndItems();
				JPanel panel = window.getTablePanel();
				try {
					ArrayList<Specialization> specializations = specializationDBHelper.getAllSpecializations();
					ArrayList<Domain> domains = domainDBHelper.getAllDomains();
					JTable jtable = table.createSpecializationTableFromArrayList(specializations, domains);
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
				window.removeTablePanelAndItems();
				JPanel panel = window.getTablePanel();
				try {
					ArrayList<Subject> subjects = subjectDBHelper.getAllSubjects();
					ArrayList<Professor> professors = professorDBHelper.getAllProfessors();
					ArrayList<Specialization> specializations = specializationDBHelper.getAllSpecializations();
					JTable jtable = table.createSubjectTableFromArrayList(subjects, professors, specializations);
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

}
