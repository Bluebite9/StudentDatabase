package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Window extends JFrame {

	private static final long serialVersionUID = 1677273811044150370L;
	public static Window generalWindow;
	private JPanel buttonPanel;
	private Tables tables;
	private JTable jtable;
	private JPanel tablePanel;

	Window() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		tables = new Tables();
		tablePanel = new JPanel();

		Button studentButton = new Button(ButtonIds.Student);
		Button departmentButton = new Button(ButtonIds.Department);
		Button domainButton = new Button(ButtonIds.Domain);
		Button facultyButton = new Button(ButtonIds.Faculty);
		Button groupButton = new Button(ButtonIds.Group);
		Button markButton = new Button(ButtonIds.Mark);
		Button professorButton = new Button(ButtonIds.Professor);
		Button specializationButton = new Button(ButtonIds.Specialization);
		Button subjectButton = new Button(ButtonIds.Subject);

		buttonPanel.add(studentButton.getButton());
		buttonPanel.add(departmentButton.getButton());
		buttonPanel.add(domainButton.getButton());
		buttonPanel.add(facultyButton.getButton());
		buttonPanel.add(groupButton.getButton());
		buttonPanel.add(markButton.getButton());
		buttonPanel.add(professorButton.getButton());
		buttonPanel.add(specializationButton.getButton());
		buttonPanel.add(subjectButton.getButton());

		add(BorderLayout.WEST, buttonPanel);

		pack();
		setVisible(true);

		setPreferredSize(new Dimension(1600, 900));
		setMaximumSize(new Dimension(1600, 900));
		setMinimumSize(new Dimension(1600, 900));

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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

	public JTable getJtable() {
		return jtable;
	}

	public void setJtable(JTable jtable) {
		this.jtable = jtable;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public void removeUnnecessaryItems() {
		this.remove(tablePanel);
		this.tablePanel.removeAll();
		this.repaint();
	}

}
