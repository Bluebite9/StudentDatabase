package gui;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import repository.StudentDBHelper;

public class WindowTest {

	private JPanel tablePanel;
	private JFrame frame;
	private JPanel buttonPanel;

	WindowTest() {
		frame = new JFrame("Window test");
		tablePanel = new JPanel();
		Tables tables = new Tables();
		JTable table = new JTable();
		buttonPanel = new JPanel();
		StudentDBHelper studentDBHelper = new StudentDBHelper();

		JButton button = new JButton("Studenti");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablePanel.add(tables.createStudentTableFromArrayList(studentDBHelper.getAllStudents()));
					tablePanel.setVisible(true);
					frame.add(tablePanel);
					frame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		buttonPanel.add(button);
		frame.add(buttonPanel);
		frame.pack();
		frame.setVisible(true);

		frame.setPreferredSize(new Dimension(1600, 900));
		frame.setMaximumSize(new Dimension(1600, 900));
		frame.setMinimumSize(new Dimension(1600, 900));

		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public static void main(String[] args) {
		new WindowTest();
	}

}
