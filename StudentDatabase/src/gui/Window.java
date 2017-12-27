package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame {

	private static final long serialVersionUID = 1677273811044150370L;
	public static Window generalWindow;
	private Tables tables = new Tables();
	private JPanel tablePanel;
	private JPanel mainPanel = new JPanel();
	private JPanel menuPanel, studentMenuPanel, departmentMenuPanel, domainMenuPanel, facultyMenuPanel, groupMenuPanel, markMenuPanel,
			professorMenuPanel, specializationMenuPanel, subjectMenuPanel;
	private JPanel buttonPanel;

	Window() {
		menuPanel = createMenuPanel();
		studentMenuPanel = createStudentMenu();
		departmentMenuPanel = createDepartmentMenu();
		domainMenuPanel = createDomainMenu();
		facultyMenuPanel = createFacultyMenu();
		groupMenuPanel = createGroupMenu();
		professorMenuPanel = createProfessorMenu();
		specializationMenuPanel = createSpecializationMenu();
		subjectMenuPanel = createSubjectMenu();
		markMenuPanel = createMarkMenu();
		// tablePanel = new JPanel(new BorderLayout());
		// Button studentButton = new Button(ButtonIds.Student);
		// Button departmentButton = new Button(ButtonIds.Department);
		// Button domainButton = new Button(ButtonIds.Domain);
		// Button facultyButton = new Button(ButtonIds.Faculty);
		// Button groupButton = new Button(ButtonIds.Group);
		// Button markButton = new Button(ButtonIds.Mark);
		// Button professorButton = new Button(ButtonIds.Professor);
		// Button specializationButton = new Button(ButtonIds.Specialization);
		// Button subjectButton = new Button(ButtonIds.Subject);
		//
		// buttonPanel.add(studentButton.getButton());
		// buttonPanel.add(departmentButton.getButton());
		// buttonPanel.add(domainButton.getButton());
		// buttonPanel.add(facultyButton.getButton());
		// buttonPanel.add(groupButton.getButton());
		// buttonPanel.add(markButton.getButton());
		// buttonPanel.add(professorButton.getButton());
		// buttonPanel.add(specializationButton.getButton());
		// buttonPanel.add(subjectButton.getButton());
		// mainPanel.add(button);

		add(menuPanel);

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

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
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

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void removeTablePanelAndItems() {
		this.remove(tablePanel);
		this.tablePanel.removeAll();
		this.repaint();
	}

	private void transition(JPanel panelToRemove, JPanel panelToAdd) {
		remove(panelToRemove);
		add(panelToAdd);
		repaint();
		pack();
		setVisible(true);
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
				transition(menuPanel, departmentMenuPanel);
			}
		});

		domains.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, domainMenuPanel);

			}
		});

		faculties.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, facultyMenuPanel);

			}
		});

		groups.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, groupMenuPanel);

			}
		});

		marks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, markMenuPanel);

			}
		});

		professors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, professorMenuPanel);

			}
		});

		specializations.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, specializationMenuPanel);

			}
		});

		students.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, studentMenuPanel);

			}
		});

		subjects.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(menuPanel, subjectMenuPanel);

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

	private JPanel createStudentMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta studenti");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga studenti");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica studenti");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(studentMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createDepartmentMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta departament");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga departament");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica departament");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(departmentMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createDomainMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta domeniu");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga domeniu");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica domeniu");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(domainMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createFacultyMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta faculate");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga faculate");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica faculate");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(facultyMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createMarkMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta nota");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga nota");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica nota");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(markMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createProfessorMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta profesor");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga profesor");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica profesor");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(professorMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createSpecializationMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta specializare");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga specializare");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica specializare");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(specializationMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createSubjectMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta materie");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga materie");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica materie");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(subjectMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

	private JPanel createGroupMenu() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel();
		panel.setLayout(null);

		title.setText("Meniu");
		title.setFont(new Font("Meniu", Font.BOLD, 32));
		title.setBounds(600, 35, 300, 140);

		JButton get = new JButton("Cauta grupa");
		get.setBounds(95, 210, 300, 100);
		JButton insert = new JButton("Adauga grupa");
		insert.setBounds(490, 210, 300, 100);
		JButton update = new JButton("Modifica grupa");
		update.setBounds(885, 210, 300, 100);
		JButton back = new JButton("Inapoi");
		back.setBounds(1000, 650, 80, 20);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transition(groupMenuPanel, menuPanel);
			}
		});

		panel.add(get);
		panel.add(insert);
		panel.add(update);
		panel.add(back);

		return panel;
	}

}
