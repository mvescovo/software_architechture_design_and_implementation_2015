package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeadingPanel extends JPanel {
	JLabel heading;
	Font font;
	
	public HeadingPanel() {
		font = new Font("SansSerif", Font.BOLD, 16);
		heading = new JLabel("Dice Game");
		heading.setFont(font);
		heading.setForeground(new Color(255,215,0));
		
		add(heading);
		
		// panel settings
		setBackground(Color.BLACK);

	}
}
