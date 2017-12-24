import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	Window(){
		JFrame frame = new JFrame("Adrian");
		JPanel panel = new JPanel();
		JButton button = new JButton("All students");
		JLabel label = new JLabel();
		
		label.setText("Yes, because why not?");
		
		frame.getContentPane().setPreferredSize(new Dimension(600, 600));
		frame.setMaximumSize(new Dimension(600, 600));
		frame.setMinimumSize(new Dimension(600, 600));
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		panel.add(button);
		panel.add(label);
		
		frame.getContentPane().add(panel);
	}
	
	public static void main(String[] args) {
		new Window();
	}
	
}
