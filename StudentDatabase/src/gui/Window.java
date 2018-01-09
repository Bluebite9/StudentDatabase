package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
import util.DatabaseException;

public class Window extends JFrame {

	private static final long serialVersionUID = 1677273811044150370L;

	public static Window generalWindow;

	private Tables tables = new Tables();

	private JPanel menuPanel, departmentPanel, domainPanel, facultyPanel, groupPanel, markPanel, professorPanel,
			specializationPanel, subjectPanel, studentPanel, loginPanel;

	private JScrollPane jScrollPane = new JScrollPane();
	private JTable table = new JTable();

	private StudentDBHelper studentDBHelper = new StudentDBHelper();
	private DepartmentDBHelper departmentDBHelper = new DepartmentDBHelper();
	private DomainDBHelper domainDBHelper = new DomainDBHelper();
	private FacultyDBHelper facultyDBHelper = new FacultyDBHelper();
	private GroupDBHelper groupDBHelper = new GroupDBHelper();
	private MarkDBHelper markDBHelper = new MarkDBHelper();
	private ProfessorDBHelper professorDBHelper = new ProfessorDBHelper();
	private SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
	private SubjectDBHelper subjectDBHelper = new SubjectDBHelper();

	Window() {
		loginPanel = createLoginPanel();
		menuPanel = createMenuPanel();
		departmentPanel = departmentPanel();
		domainPanel = domainPanel();
		facultyPanel = facultyPanel();
		groupPanel = groupPanel();
		professorPanel = professorPanel();
		specializationPanel = specializationPanel();
		subjectPanel = subjectPanel();
		markPanel = markPanel();
		studentPanel = studentPanel();

		add(loginPanel);

		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(new Dimension(1280, 720));
		setMinimumSize(new Dimension(1280, 720));

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		pack();
		setVisible(true);
	}

	public static Window getGeneralWindow() {
		return generalWindow;
	}

	public static void setGeneralWindow(Window generalWindow) {
		Window.generalWindow = generalWindow;
	}

	public Tables getTables() {
		return tables;
	}

	public void setTables(Tables table) {
		this.tables = table;
	}

	public static void main(String[] args) {
		generalWindow = new Window();
	}

	private void transition(JPanel panelToRemove, JPanel panelToAdd) {
		remove(panelToRemove);
		add(panelToAdd);
		repaint();
		pack();
		setVisible(true);
	}

	private JPanel createLoginPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(500, 360, 250, 20);

		JLabel label = new JLabel("Username:");
		label.setBounds(200, 250, 100, 20);
		panel.add(label);

		label = new JLabel("Password:");
		label.setBounds(200, 300, 100, 20);
		panel.add(label);

		JTextField user = new JTextField();
		user.setBounds(310, 250, 100, 20);
		JPasswordField pass = new JPasswordField();
		pass.setBounds(310, 300, 100, 20);
		pass.setEchoChar('*');

		JCheckBox showPass = new JCheckBox("Show password");
		showPass.setBounds(200, 330, 150, 20);

		showPass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPass.isSelected()) {
					pass.setEchoChar((char) 0);
				} else {
					pass.setEchoChar('*');
				}
			}
		});

		JButton button = new JButton("Login");
		button.setBounds(500, 250, 200, 100);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DatabaseException ex = new DatabaseException("Invalid username or password");
				try {
					if (!user.getText().equals("admin")) {
						throw ex;
					}

					if (pass.getPassword() != null) {
						String sPass = "";
						for (char c : pass.getPassword()) {
							sPass += c;
						}

						if (!sPass.equals("admin")) {
							throw ex;
						}
						transition(loginPanel, menuPanel);
					} else {
						throw ex;
					}
				} catch (Exception exc) {
					statusLabel.setText(ex.getMessage());
				}
			}
		});

		panel.add(user);
		panel.add(pass);
		panel.add(showPass);
		panel.add(button);
		panel.add(statusLabel);

		return panel;
	}

	private JPanel createMenuPanel() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();

		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton departments = new JButton("Departamente");
		departments.setBounds(95, 210, 300, 100);
		JButton domains = new JButton("Domenii");
		domains.setBounds(490, 210, 300, 100);
		JButton faculties = new JButton("Facultati");
		faculties.setBounds(885, 210, 300, 100);
		JButton groups = new JButton("Grupe");
		groups.setBounds(95, 380, 300, 100);
		JButton marks = new JButton("Note");
		marks.setBounds(490, 380, 300, 100);
		JButton professors = new JButton("Profesori");
		professors.setBounds(885, 380, 300, 100);
		JButton specializations = new JButton("Specialitati");
		specializations.setBounds(95, 550, 300, 100);
		JButton students = new JButton("Studenti");
		students.setBounds(490, 550, 300, 100);
		JButton subjects = new JButton("Materii");
		subjects.setBounds(885, 550, 300, 100);

		departments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, departmentPanel);
			}
		});

		domains.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, domainPanel);

			}
		});

		faculties.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, facultyPanel);

			}
		});

		groups.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, groupPanel);

			}
		});

		marks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, markPanel);

			}
		});

		professors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, professorPanel);

			}
		});

		specializations.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, specializationPanel);

			}
		});

		students.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, studentPanel);

			}
		});

		subjects.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, subjectPanel);

			}
		});

		panel.add(title);
		panel.add(subjects);
		panel.add(students);
		panel.add(specializations);
		panel.add(professors);
		panel.add(marks);
		panel.add(groups);
		panel.add(faculties);
		panel.add(domains);
		panel.add(departments);

		return panel;
	}

	private JPanel studentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Prenume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField surname = new TextField();
		surname.setBounds(120, 50, 100, 25);
		label = new JLabel("Nume");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField birthdate = new TextField();
		birthdate.setBounds(120, 80, 100, 25);
		label = new JLabel("Data nasterii");
		label.setBounds(20, 80, 100, 25);
		panel.add(label);

		TextField credits = new TextField();
		credits.setBounds(120, 110, 100, 25);
		label = new JLabel("Credite");
		label.setBounds(20, 110, 100, 25);
		panel.add(label);

		TextField debtor = new TextField();
		debtor.setBounds(120, 140, 100, 25);
		label = new JLabel("Restantier");
		label.setBounds(20, 140, 100, 25);
		panel.add(label);

		TextField scholarshipType = new TextField();
		scholarshipType.setBounds(120, 170, 100, 25);
		label = new JLabel("Bursa");
		label.setBounds(20, 170, 100, 25);
		panel.add(label);

		TextField beginningYear = new TextField();
		beginningYear.setBounds(120, 200, 100, 25);
		label = new JLabel("An de inceput");
		label.setBounds(20, 200, 100, 25);
		panel.add(label);

		TextField currentYear = new TextField();
		currentYear.setBounds(120, 230, 100, 25);
		label = new JLabel("Anul curent");
		label.setBounds(20, 230, 100, 25);
		panel.add(label);

		TextField specialization = new TextField();
		specialization.setBounds(120, 260, 100, 25);
		label = new JLabel("Specializarea");
		label.setBounds(20, 260, 100, 25);
		panel.add(label);

		TextField domain = new TextField();
		domain.setBounds(120, 290, 100, 25);
		label = new JLabel("Domeniul");
		label.setBounds(20, 290, 100, 25);
		panel.add(label);

		TextField faculty = new TextField();
		faculty.setBounds(120, 320, 100, 25);
		label = new JLabel("Facultatea");
		label.setBounds(20, 320, 100, 25);
		panel.add(label);

		TextField group = new TextField();
		group.setBounds(120, 350, 100, 25);
		label = new JLabel("Grupa");
		label.setBounds(20, 350, 100, 25);
		panel.add(label);

		TextField subgroup = new TextField();
		subgroup.setBounds(120, 380, 100, 25);
		label = new JLabel("Subgrupa");
		label.setBounds(20, 380, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 410, 100, 25);
		label = new JLabel("Activ");
		label.setBounds(20, 410, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				surname.setText("");
				birthdate.setText("");
				group.setText("");
				subgroup.setText("");
				beginningYear.setText("");
				currentYear.setText("");
				scholarshipType.setText("");
				debtor.setText("");
				credits.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(studentPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				studentPanel.remove(jScrollPane);
				try {
					ArrayList<Student> students = studentDBHelper.find(name.getText(), surname.getText(), birthdate.getText(),
							credits.getText(), debtor.getText(), scholarshipType.getText(), beginningYear.getText(),
							currentYear.getText(), specialization.getText(), domain.getText(), faculty.getText(), group.getText(),
							active.getText());
					ArrayList<Group> groups = groupDBHelper.getAllGroups();
					table = tables.createStudentTableFromArrayList(students, groups);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					studentPanel.add(jScrollPane);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					surname.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					birthdate.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					group.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					subgroup.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
					beginningYear.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					currentYear.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
					debtor.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
					credits.setText(table.getValueAt(table.getSelectedRow(), 9).toString());
					if (table.getValueAt(table.getSelectedRow(), 10) != null) {
						scholarshipType.setText(table.getValueAt(table.getSelectedRow(), 10).toString());
					}
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				studentPanel.remove(jScrollPane);
				Student student = null;
				try {
					ArrayList<Group> groups = groupDBHelper.getGroupsByName(group.getText());
					String groupId = "" + groups.get(0).getId();
					student = new Student("0", name.getText(), surname.getText(), birthdate.getText(), groupId,
							subgroup.getText(), beginningYear.getText(), currentYear.getText(), scholarshipType.getText(),
							debtor.getText(), credits.getText());
				} catch (ParseException | DatabaseException | SQLException e2) {
					statusLabel.setText(e2.getMessage());
				}

				if (student != null) {
					try {
						studentDBHelper.insert(student);
						statusLabel.setText("Student inserted");
						student = studentDBHelper.getLastStudentRow();
						String debtor = "Nu";
						if (student.isDebtor()) {
							debtor = "Da";
						}
						Object[] studentObj = { student.getId(), student.getName(), student.getSurname(), student.getBirthDate(),
								group.getText(), student.getSubgroup(), student.getBeginningYear(), student.getCurrentYear(), debtor,
								student.getCreditsNumber(), student.getScholarshipType() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(studentObj);
						table = new JTable(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						studentPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Student student = null;
				try {
					ArrayList<Group> groups = groupDBHelper.getGroupsByName(group.getText());
					String groupId = "";
					if (!groups.isEmpty()) {
						groupId = "" + groups.get(0).getId();
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Student not found");
					}
					student = new Student(id.getText(), name.getText(), surname.getText(), birthdate.getText(), groupId,
							subgroup.getText(), beginningYear.getText(), currentYear.getText(), scholarshipType.getText(),
							debtor.getText(), credits.getText());
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						student.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						student.setActive(bActive);
					}

					studentDBHelper.update(student);
				} catch (ParseException | DatabaseException | SQLException e2) {
					statusLabel.setText(e2.getMessage());
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(surname);
		panel.add(birthdate);
		panel.add(credits);
		panel.add(debtor);
		panel.add(scholarshipType);
		panel.add(beginningYear);
		panel.add(currentYear);
		panel.add(specialization);
		panel.add(domain);
		panel.add(faculty);
		panel.add(group);
		panel.add(subgroup);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel departmentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Nume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField faculty = new TextField();
		faculty.setBounds(120, 50, 100, 25);
		label = new JLabel("Facultate");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 80, 100, 25);
		label = new JLabel("Activ");
		label.setBounds(20, 80, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				faculty.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(departmentPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				departmentPanel.remove(jScrollPane);
				try {
					ArrayList<Department> departments = departmentDBHelper.find(name.getText(), faculty.getText(),
							active.getText());
					ArrayList<Faculty> faculties = facultyDBHelper.getAllFaculties();
					table = tables.createDepartmentTableFromArrayList(departments, faculties);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					departmentPanel.add(jScrollPane);
					add(departmentPanel);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					faculty.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Department department = null;
				departmentPanel.remove(jScrollPane);
				try {
					ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByName(faculty.getText());
					String facultyId = "" + faculties.get(0).getId();
					department = new Department("0", name.getText(), facultyId);
				} catch (SQLException | DatabaseException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (department != null) {
					try {
						departmentDBHelper.insert(department);
						statusLabel.setText("Department inserted");
						department = departmentDBHelper.getLastDomainRow();
						Object[] departmentObj = { department.getId(), department.getName(), department.getFaculty() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(departmentObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						departmentPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Department department = null;
				try {
					ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByName(faculty.getText());
					String facultyId = "";
					if (!faculties.isEmpty()) {
						facultyId = "" + faculties.get(0).getId();
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Department not found");
					}
					department = new Department(id.getText(), name.getText(), facultyId);
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						department.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						department.setActive(bActive);
					}
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (department != null) {
					try {
						departmentDBHelper.update(department);
						statusLabel.setText("Department updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(faculty);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel domainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Nume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField faculty = new TextField();
		faculty.setBounds(120, 50, 100, 25);
		label = new JLabel("Facultate");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 50, 100, 25);
		label = new JLabel("Activ");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				faculty.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(domainPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				departmentPanel.remove(jScrollPane);
				try {
					ArrayList<Domain> domains = domainDBHelper.find(name.getText(), faculty.getText(), active.getText());
					ArrayList<Faculty> faculties = facultyDBHelper.getAllFaculties();
					table = tables.createDomainTableFromArrayList(domains, faculties);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					domainPanel.add(jScrollPane);
					add(domainPanel);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					faculty.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Domain domain = null;
				domainPanel.remove(jScrollPane);
				try {
					ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByName(faculty.getText());
					String facultyId = "" + faculties.get(0).getId();
					domain = new Domain("0", name.getText(), facultyId);
				} catch (SQLException | DatabaseException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (domain != null) {
					try {
						domainDBHelper.insert(domain);
						statusLabel.setText("Domain inserted");
						domain = domainDBHelper.getLastDomainRow();
						Object[] domainObj = { domain.getId(), domain.getName(), domain.getFaculty() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(domainObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						domainPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Domain domain = null;
				try {
					ArrayList<Faculty> faculties = facultyDBHelper.getFacultiesByName(faculty.getText());
					String facultyId = "";
					if (!faculties.isEmpty()) {
						facultyId = "" + faculties.get(0).getId();
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Domain not found");
					}
					domain = new Domain(id.getText(), name.getText(), facultyId);
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						domain.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						domain.setActive(bActive);
					}
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (domain != null) {
					try {
						domainDBHelper.update(domain);
						statusLabel.setText("Domain updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(faculty);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel facultyPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Nume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField address = new TextField();
		address.setBounds(120, 50, 100, 25);
		label = new JLabel("Adresa");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 80, 100, 25);
		label = new JLabel("Activ");
		label.setBounds(20, 80, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				address.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(facultyPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				facultyPanel.remove(jScrollPane);
				try {
					ArrayList<Faculty> faculties = facultyDBHelper.find(name.getText(), address.getText(), active.getText());
					table = tables.createFacultyTableFromArrayList(faculties);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					facultyPanel.add(jScrollPane);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				add(facultyPanel);
				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					address.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Faculty faculty = null;
				facultyPanel.remove(jScrollPane);
				try {
					faculty = new Faculty("0", name.getText(), address.getText());
				} catch (DatabaseException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (faculty != null) {
					try {
						facultyDBHelper.insert(faculty);
						statusLabel.setText("Faculty inserted");
						faculty = facultyDBHelper.getLastFacultyRow();
						Object[] facultyObj = { faculty.getId(), faculty.getName(), faculty.getAddress() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(facultyObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						facultyPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Faculty faculty = null;
				try {
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Faculty not found");
					}

					faculty = new Faculty(id.getText(), name.getText(), address.getText());
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						faculty.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						faculty.setActive(bActive);
					}
				} catch (DatabaseException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (faculty != null) {
					try {
						facultyDBHelper.update(faculty);
						statusLabel.setText("Faculty updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(address);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel groupPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Nume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField specialization = new TextField();
		specialization.setBounds(120, 50, 100, 25);
		label = new JLabel("Specializarea");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField year = new TextField();
		year.setBounds(120, 80, 100, 25);
		label = new JLabel("An");
		label.setBounds(20, 80, 100, 25);
		panel.add(label);

		TextField numberOfStudents = new TextField();
		numberOfStudents.setBounds(120, 110, 100, 25);
		label = new JLabel("Numarul de studenti");
		label.setBounds(20, 110, 100, 25);
		panel.add(label);

		TextField faculty = new TextField();
		faculty.setBounds(120, 140, 100, 25);
		label = new JLabel("Facultatea");
		label.setBounds(20, 140, 100, 25);
		panel.add(label);

		TextField domain = new TextField();
		domain.setBounds(120, 170, 100, 25);
		label = new JLabel("Domeniul");
		label.setBounds(20, 170, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 200, 100, 25);
		label = new JLabel("Activ");
		label.setBounds(20, 200, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				specialization.setText("");
				year.setText("");
				numberOfStudents.setText("");
				faculty.setText("");
				domain.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(groupPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				groupPanel.remove(jScrollPane);
				try {
					ArrayList<Group> groups = groupDBHelper.find(name.getText(), specialization.getText(), year.getText(),
							numberOfStudents.getText(), faculty.getText(), domain.getText(), active.getText());
					ArrayList<Specialization> specializations = specializationDBHelper.getAllSpecializations();
					table = tables.createGroupTableFromArrayList(groups, specializations);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					groupPanel.add(jScrollPane);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					specialization.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					year.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					numberOfStudents.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Group group = null;
				facultyPanel.remove(jScrollPane);
				try {
					ArrayList<Specialization> specializations = specializationDBHelper
							.getSpecializationsByName(specialization.getText());
					String specializationId = "" + specializations.get(0).getId();
					group = new Group("0", name.getText(), specializationId, year.getText(), numberOfStudents.getText());
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (group != null) {
					try {
						groupDBHelper.insert(group);
						statusLabel.setText("Group inserted");
						group = groupDBHelper.getLastGroupRow();
						Object[] groupObj = { group.getId(), group.getName(), group.getSpecialization(), group.getYear(),
								group.getNumberOfStudents() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(groupObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						groupPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Group group = null;
				try {
					ArrayList<Specialization> specializations = specializationDBHelper
							.getSpecializationsByName(specialization.getText());
					String specializationId = "";
					if (!specializations.isEmpty()) {
						specializationId = "" + specializations.get(0).getId();
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Group not found");
					}
					group = new Group(id.getText(), name.getText(), specializationId, year.getText(), numberOfStudents.getText());

					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						group.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						group.setActive(bActive);
					}
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (group != null) {
					try {
						groupDBHelper.update(group);
						statusLabel.setText("Group updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(year);
		panel.add(specialization);
		panel.add(numberOfStudents);
		panel.add(faculty);
		panel.add(domain);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel markPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField student = new TextField();
		student.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Student");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField mark = new TextField();
		mark.setBounds(120, 50, 100, 25);
		label = new JLabel("Nota");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField subject = new TextField();
		subject.setBounds(120, 80, 100, 25);
		label = new JLabel("Subject");
		label.setBounds(20, 80, 100, 25);
		panel.add(label);

		TextField session = new TextField();
		session.setBounds(120, 110, 100, 25);
		label = new JLabel("Sesiunea");
		label.setBounds(20, 110, 100, 25);
		panel.add(label);

		TextField date = new TextField();
		date.setBounds(120, 140, 100, 25);
		label = new JLabel("Data");
		label.setBounds(20, 140, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 170, 100, 25);
		label = new JLabel("Activ");
		label.setBounds(20, 170, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				student.setText("");
				mark.setText("");
				subject.setText("");
				session.setText("");
				date.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(markPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				markPanel.remove(jScrollPane);
				try {
					ArrayList<Mark> marks = markDBHelper.find(student.getText(), mark.getText(), subject.getText(),
							session.getText(), date.getText(), active.getText());
					ArrayList<Student> students = studentDBHelper.getAllStudents();
					ArrayList<Subject> subjects = subjectDBHelper.getAllSubjects();
					table = tables.createMarkTableFromArrayList(marks, students, subjects);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					markPanel.add(jScrollPane);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					student.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					mark.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					subject.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					session.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					date.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Mark mark2 = null;
				markPanel.remove(jScrollPane);
				try {
					ArrayList<Student> students = studentDBHelper.getStudentByName(student.getText());
					String studentId = "" + students.get(0);
					ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByName(subject.getText());
					String subjectId = "" + subjects.get(0);
					mark2 = new Mark("0", studentId, mark.getText(), subjectId, session.getText(), date.getText());
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (mark2 != null) {
					try {
						markDBHelper.insert(mark2);
						statusLabel.setText("Group inserted");
						mark2 = markDBHelper.getLastMarkRow();
						Object[] markObj = { mark2.getId(), mark2.getStudent(), mark2.getMark(), mark2.getSubject(),
								mark2.getSession(), mark2.getDate() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(markObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						markPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Mark mark2 = null;
				try {
					ArrayList<Student> students = studentDBHelper.getStudentByName(student.getText());
					String studentId = "";
					if (!students.isEmpty()) {
						studentId = "" + students.get(0);
					}
					ArrayList<Subject> subjects = subjectDBHelper.getSubjectsByName(subject.getText());
					String subjectId = "";
					if (!subjects.isEmpty()) {
						subjectId = "" + subjects.get(0);
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Mark not found");
					}

					mark2 = new Mark(id.getText(), studentId, mark.getText(), subjectId, session.getText(), date.getText());
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						mark2.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						mark2.setActive(bActive);
					}
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (mark2 != null) {
					try {
						markDBHelper.update(mark2);
						statusLabel.setText("Mark updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(student);
		panel.add(mark);
		panel.add(subject);
		panel.add(session);
		panel.add(date);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel professorPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Prenume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField surname = new TextField();
		surname.setBounds(120, 50, 100, 25);
		label = new JLabel("Nume");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField birthdate = new TextField();
		birthdate.setBounds(120, 80, 100, 25);
		label = new JLabel("Zi de nastere");
		label.setBounds(20, 80, 100, 25);
		panel.add(label);

		TextField department = new TextField();
		department.setBounds(120, 110, 100, 25);
		label = new JLabel("Departament");
		label.setBounds(20, 110, 100, 25);
		panel.add(label);

		TextField degree = new TextField();
		degree.setBounds(120, 140, 100, 25);
		label = new JLabel("Grad");
		label.setBounds(20, 140, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 170, 100, 25);
		label = new JLabel("Active");
		label.setBounds(20, 170, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				surname.setText("");
				birthdate.setText("");
				department.setText("");
				degree.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(professorPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				professorPanel.remove(jScrollPane);
				try {
					ArrayList<Professor> professors = professorDBHelper.find(name.getText(), surname.getText(),
							birthdate.getText(), department.getText(), degree.getText(), active.getText());
					ArrayList<Department> departments = departmentDBHelper.getAllDepartments();
					table = tables.createProfessorTableFromArrayList(professors, departments);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					professorPanel.add(jScrollPane);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					surname.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					birthdate.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					department.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					degree.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Professor professor = null;
				professorPanel.remove(jScrollPane);
				try {
					ArrayList<Department> departments = departmentDBHelper.getDepartmentsByName(department.getText());
					String departmentId = "" + departments.get(0);
					professor = new Professor("0", name.getText(), surname.getText(), birthdate.getText(), departmentId,
							degree.getText());
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (professor != null) {
					try {
						professorDBHelper.insert(professor);
						statusLabel.setText("Professor inserted");
						professor = professorDBHelper.getLastProfessorRow();
						Object[] professorObj = { professor.getId(), professor.getName(), professor.getSurname(),
								professor.getBirthDate(), professor.getDepartment(), professor.getDegree() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(professorObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						professorPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Professor professor = null;
				try {
					ArrayList<Department> departments = departmentDBHelper.getDepartmentsByName(department.getText());
					String departmentId = "";
					if (!departments.isEmpty()) {
						departmentId = "" + departments.get(0);
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Professor not found");
					}
					professor = new Professor(id.getText(), name.getText(), surname.getText(), birthdate.getText(), departmentId,
							degree.getText());
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						professor.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						professor.setActive(bActive);
					}
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (professor != null) {
					try {
						professorDBHelper.update(professor);
						statusLabel.setText("Professor updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(surname);
		panel.add(birthdate);
		panel.add(department);
		panel.add(degree);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel specializationPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Prenume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField domain = new TextField();
		domain.setBounds(120, 50, 100, 25);
		label = new JLabel("Domeniu");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 50, 100, 25);
		label = new JLabel("Activ");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				domain.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(specializationPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				specializationPanel.remove(jScrollPane);
				try {
					ArrayList<Specialization> specializations = specializationDBHelper.find(name.getText(), domain.getText(),
							active.getText());
					ArrayList<Domain> domains = domainDBHelper.getAllDomains();
					table = tables.createSpecializationTableFromArrayList(specializations, domains);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					specializationPanel.add(jScrollPane);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					domain.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Specialization specialization = null;
				specializationPanel.remove(jScrollPane);
				try {
					ArrayList<Domain> domains = domainDBHelper.getDomainsByName(domain.getText());
					String domainId = "" + domains.get(0);
					specialization = new Specialization("0", name.getText(), domainId);
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (specialization != null) {
					try {
						specializationDBHelper.insert(specialization);
						statusLabel.setText("Professor inserted");
						specialization = specializationDBHelper.getLastSpecializationRow();
						Object[] specializationObj = { specialization.getId(), specialization.getName(),
								specialization.getDomain() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(specializationObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						specializationPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Specialization specialization = null;
				try {
					ArrayList<Domain> domains = domainDBHelper.getDomainsByName(domain.getText());
					String domainId = "";
					if (!domains.isEmpty()) {
						domainId = "" + domains.get(0);
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Specialization not found");
					}
					specialization = new Specialization(id.getText(), name.getText(), domainId);
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						specialization.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						specialization.setActive(bActive);
					}
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (specialization != null) {
					try {
						specializationDBHelper.update(specialization);
						statusLabel.setText("Specialization updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(domain);
		panel.add(back);
		panel.add(search);
		panel.add(active);

		return panel;
	}

	private JPanel subjectPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JButton back = new JButton("Inapoi");
		back.setBounds(1190, 650, 80, 20);
		JButton search = new JButton("Search");
		search.setBounds(20, 650, 80, 20);
		JButton insert = new JButton("Insert");
		insert.setBounds(420, 650, 80, 20);
		JButton get = new JButton("Get");
		get.setBounds(220, 650, 80, 20);
		JButton update = new JButton("Update");
		update.setBounds(620, 650, 80, 20);
		JButton clear = new JButton("Clear");
		clear.setBounds(120, 650, 80, 20);

		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(720, 650, 500, 20);

		TextField id = new TextField();

		TextField name = new TextField();
		name.setBounds(120, 20, 100, 25);
		JLabel label = new JLabel("Nume");
		label.setBounds(20, 20, 100, 25);
		panel.add(label);

		TextField specialization = new TextField();
		specialization.setBounds(120, 50, 100, 25);
		label = new JLabel("Specializare");
		label.setBounds(20, 50, 100, 25);
		panel.add(label);

		TextField year = new TextField();
		year.setBounds(120, 80, 100, 25);
		label = new JLabel("An");
		label.setBounds(20, 80, 100, 25);
		panel.add(label);

		TextField type = new TextField();
		type.setBounds(120, 110, 100, 25);
		label = new JLabel("Tipul");
		label.setBounds(20, 110, 100, 25);
		panel.add(label);

		TextField semester = new TextField();
		semester.setBounds(120, 140, 100, 25);
		label = new JLabel("Semestru");
		label.setBounds(20, 140, 100, 25);
		panel.add(label);

		TextField lecturer = new TextField();
		lecturer.setBounds(120, 170, 100, 25);
		label = new JLabel("Profesor de curs");
		label.setBounds(20, 170, 100, 25);
		panel.add(label);

		TextField labProf = new TextField();
		labProf.setBounds(120, 200, 100, 25);
		label = new JLabel("Profesor de laborator");
		label.setBounds(20, 200, 100, 25);
		panel.add(label);

		TextField seminProf = new TextField();
		seminProf.setBounds(120, 230, 100, 25);
		label = new JLabel("Profesor de seminar");
		label.setBounds(20, 230, 100, 25);
		panel.add(label);

		TextField credits = new TextField();
		credits.setBounds(120, 260, 100, 25);
		label = new JLabel("Credite");
		label.setBounds(20, 260, 100, 25);
		panel.add(label);

		TextField examType = new TextField();
		examType.setBounds(120, 290, 100, 25);
		label = new JLabel("Tip examinare");
		label.setBounds(20, 290, 100, 25);
		panel.add(label);

		TextField optional = new TextField();
		optional.setBounds(120, 320, 100, 25);
		label = new JLabel("Optional");
		label.setBounds(20, 320, 100, 25);
		panel.add(label);

		TextField active = new TextField();
		active.setBounds(120, 320, 100, 25);
		label = new JLabel("Active");
		label.setBounds(20, 320, 100, 25);
		panel.add(label);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				specialization.setText("");
				year.setText("");
				type.setText("");
				semester.setText("");
				lecturer.setText("");
				labProf.setText("");
				seminProf.setText("");
				credits.setText("");
				examType.setText("");
				optional.setText("");
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(subjectPanel, menuPanel);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				subjectPanel.remove(jScrollPane);
				try {
					ArrayList<Subject> subjects = subjectDBHelper.find(name.getText(), specialization.getText(), year.getText(),
							type.getText(), semester.getText(), lecturer.getText(), labProf.getText(), seminProf.getText(),
							credits.getText(), examType.getText(), optional.getText(), active.getText());
					ArrayList<Professor> professors = professorDBHelper.getAllProfessors();
					ArrayList<Specialization> specializations = specializationDBHelper.getAllSpecializations();
					table = tables.createSubjectTableFromArrayList(subjects, professors, specializations);
					jScrollPane = new JScrollPane(table);
					jScrollPane.setBounds(250, 10, 1000, 600);
					subjectPanel.add(jScrollPane);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				repaint();
				pack();
				setVisible(true);
			}
		});

		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					id.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					name.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					specialization.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					year.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					type.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					semester.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
					lecturer.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					labProf.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
					seminProf.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
					credits.setText(table.getValueAt(table.getSelectedRow(), 9).toString());
					examType.setText(table.getValueAt(table.getSelectedRow(), 10).toString());
					optional.setText(table.getValueAt(table.getSelectedRow(), 11).toString());
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Subject subject = null;
				subjectPanel.remove(jScrollPane);
				try {
					ArrayList<Specialization> specializations = specializationDBHelper
							.getSpecializationsByName(specialization.getText());
					String specializationId = "" + specializations.get(0);
					ArrayList<Professor> lecturers = professorDBHelper.getProfessorByName(lecturer.getText());
					String lecturerId = "" + lecturers.get(0);
					ArrayList<Professor> labProfs = professorDBHelper.getProfessorByName(labProf.getText());
					String labProfId = "" + labProfs.get(0);
					ArrayList<Professor> seminProfs = professorDBHelper.getProfessorByName(seminProf.getText());
					String seminProfId = "" + seminProfs.get(0);
					subject = new Subject("0", name.getText(), specializationId, year.getText(), type.getText(),
							semester.getText(), lecturerId, labProfId, seminProfId, credits.getText(), examType.getText(),
							optional.getText());
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (subject != null) {
					try {
						subjectDBHelper.insert(subject);
						statusLabel.setText("Subject inserted");
						subject = subjectDBHelper.getLastSubjectRow();
						Object[] specializationObj = { subject.getId(), subject.getName(), subject.getYear(), subject.getType(),
								subject.getSemester(), subject.getLecturer(), subject.getLabProf(), subject.getSeminProf(),
								subject.getCredits(), subject.getExamType(), subject.isOptional() };
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.addRow(specializationObj);
						table.setModel(tableModel);
						jScrollPane = new JScrollPane(table);
						jScrollPane.setBounds(250, 10, 1000, 600);
						subjectPanel.add(jScrollPane);
					} catch (SQLException | DatabaseException e1) {
						statusLabel.setText(e1.getMessage());
					}
				}
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Subject subject = null;
				try {
					ArrayList<Specialization> specializations = specializationDBHelper
							.getSpecializationsByName(specialization.getText());
					String specializationId = "";
					if (!specializations.isEmpty()) {
						specializationId = "" + specializations.get(0).getId();
					}
					ArrayList<Professor> lecturers = professorDBHelper.getProfessorByName(lecturer.getText());
					String lecturerId = "";
					if (!lecturers.isEmpty()) {
						lecturerId = "" + lecturers.get(0).getId();
					}
					ArrayList<Professor> labProfs = professorDBHelper.getProfessorByName(labProf.getText());
					String labProfId = "";
					if (!labProfs.isEmpty()) {
						labProfId = "" + labProfs.get(0).getId();
					}
					ArrayList<Professor> seminProfs = professorDBHelper.getProfessorByName(seminProf.getText());
					String seminProfId = "";
					if (seminProfs.isEmpty()) {
						seminProfId = "" + seminProfs.get(0).getId();
					}
					if (id.getText().isEmpty()) {
						throw new DatabaseException("Subject not found");
					}
					subject = new Subject(id.getText(), name.getText(), specializationId, year.getText(), type.getText(),
							semester.getText(), lecturerId, labProfId, seminProfId, credits.getText(), examType.getText(),
							optional.getText());
					boolean bActive;
					if (active.getText().equals("Da")) {
						bActive = true;
						subject.setActive(bActive);
					} else if (active.getText().equals("Nu")) {
						bActive = false;
						subject.setActive(bActive);
					}
				} catch (DatabaseException | SQLException e1) {
					statusLabel.setText(e1.getMessage());
				}

				if (subject != null) {
					try {
						subjectDBHelper.update(subject);
						statusLabel.setText("Subject updated");
					} catch (SQLException | DatabaseException e2) {
						statusLabel.setText(e2.getMessage());
					}
				}
			}
		});

		panel.add(clear);
		panel.add(statusLabel);
		panel.add(update);
		panel.add(get);
		panel.add(insert);
		panel.add(name);
		panel.add(specialization);
		panel.add(year);
		panel.add(type);
		panel.add(semester);
		panel.add(lecturer);
		panel.add(labProf);
		panel.add(seminProf);
		panel.add(credits);
		panel.add(examType);
		panel.add(optional);
		panel.add(back);
		panel.add(search);

		return panel;
	}

}
