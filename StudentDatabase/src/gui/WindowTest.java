package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowTest extends JFrame {

	private static final long serialVersionUID = 5316723917180966373L;
	private JPanel firstPanel = new JPanel(), secondPanel = new JPanel();
	private JButton firstButton = new JButton("FirstButton");
	private JButton secondButton = new JButton("SecondButton");

	WindowTest() {
		// firstPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// firstPanel = new JPanel() {
		// private static final long serialVersionUID = -2821218877968742143L;
		//
		// @Override
		// public Dimension getPreferredSize() {
		// return new Dimension(300, 300);
		// };
		// };
		//
		// firstButton.setBounds(30, 30, 80, 40);
		// firstButton.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// remove(firstPanel);
		// repaint();
		// add(secondPanel);
		// setVisible(true);
		// }
		// });
		//
		// secondButton.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// remove(secondPanel);
		// repaint();
		// add(firstPanel);
		// setVisible(true);
		// }
		// });
		//
		// firstPanel.add(firstButton);
		// secondPanel.add(secondButton);
		// getContentPane().add(firstPanel);
		//
		// setPreferredSize(new Dimension(800, 640));
		// pack();
		// setVisible(true);

		JPanel panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(300, 300);
			};
		};
		panel.setLayout(null);
		JButton button = new JButton("Position Test");
		button.setBounds(30, 30, 80, 40);
		panel.add(button);
		add(panel);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new WindowTest();
	}

}
