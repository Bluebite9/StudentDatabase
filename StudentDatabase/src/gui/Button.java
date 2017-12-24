package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button implements ActionListener {

	private JButton button;

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
