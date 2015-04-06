package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollingDicePanel extends JPanel {
	RollingDicePanel() {
		JLabel dice1Label = new JLabel("dice1");
		add(dice1Label);
		JLabel dice2Label = new JLabel("dice2");
		add(dice2Label);
		
		// panel settings
		setBackground(new Color(0, 153, 0));
	}
}
