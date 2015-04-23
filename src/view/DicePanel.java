package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DicePanel extends JPanel {
	private static final long serialVersionUID = 5943364367155114789L;
	JLabel rollLabel1;
	JLabel rollLabel2;
	JLabel dice1Label;
	JLabel dice2Label;
	Font font;
	
	DicePanel() {
		font = new Font("SansSerif", Font.BOLD, 48);
		GridLayout gridLayout = new GridLayout(0, 2);
		setLayout(gridLayout);
		
		rollLabel1 = new JLabel("Player rolling:");
		//rollLabel.setFont(font);
		rollLabel1.setForeground(Color.WHITE);
		rollLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		//rollLabel.setVisible(false);
		add(rollLabel1);
		
		rollLabel2 = new JLabel("N/A");
		//rollLabel2.setFont(font);
		rollLabel2.setForeground(Color.WHITE);
		rollLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		//rollLabel2.setVisible(false);
		add(rollLabel2);
		
		dice1Label = new JLabel("1");
		dice1Label.setFont(font);
		dice1Label.setForeground(Color.WHITE);
		dice1Label.setHorizontalAlignment(SwingConstants.CENTER);
		//dice1Label.setVisible(false);
		add(dice1Label);
		
		dice2Label = new JLabel("1");
		dice2Label.setFont(font);
		dice2Label.setForeground(Color.WHITE);
		dice2Label.setHorizontalAlignment(SwingConstants.CENTER);
		//dice2Label.setVisible(false);
		add(dice2Label, BorderLayout.EAST);
		
		// panel settings
		setBackground(new Color(0, 153, 0));
	}
	
	public void setDice1(String value) {
		dice1Label.setText(value);
	}
	
	public void setDice2(String value) {
		dice2Label.setText(value);
	}
	
	public void setRollLabel2(String value) {
		rollLabel2.setText(value);
	}
}
