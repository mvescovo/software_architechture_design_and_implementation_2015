package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DicePanel extends JPanel {
	private static final long serialVersionUID = 5943364367155114789L;
	private JLabel dice1Label;
	private JLabel dice2Label;
	private Font font;
	
	DicePanel() {
		font = new Font("SansSerif", Font.BOLD, 48);
		setLayout(new GridLayout(2, 2));
		setBackground(new Color(0, 153, 0));
		
		dice1Label = new JLabel("1");
		dice1Label.setFont(font);
		dice1Label.setForeground(Color.WHITE);
		dice1Label.setHorizontalAlignment(SwingConstants.CENTER);
		dice1Label.setVisible(false);
		add(dice1Label);
		
		dice2Label = new JLabel("1");
		dice2Label.setFont(font);
		dice2Label.setForeground(Color.WHITE);
		dice2Label.setHorizontalAlignment(SwingConstants.CENTER);
		dice2Label.setVisible(false);
		add(dice2Label);
		
		add(Box.createRigidArea(new Dimension(0, 0)));
	}
	
	public JLabel getDice1() {
		return dice1Label;
	}
	
	public JLabel getDice2() {
		return dice2Label;
	}
}
