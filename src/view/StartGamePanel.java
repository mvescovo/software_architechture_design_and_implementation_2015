package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartGamePanel extends JPanel {
	private static final long serialVersionUID = -5341943341311031795L;
	private JLabel playerNameLabel;
	private JTextField playerNameTextField;
	private JLabel playerPointsLabel;
	private JTextField playerPointsTextField;
	private JButton startGameButton;
	private JButton quitGameButton;
	
	public StartGamePanel() {
		setBackground(new Color(151, 42, 39));
		setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		GridBagConstraints c3 = new GridBagConstraints();
		GridBagConstraints c4 = new GridBagConstraints();
		GridBagConstraints c5 = new GridBagConstraints();
		GridBagConstraints c6 = new GridBagConstraints();
				
		playerNameLabel = new JLabel("Player name*");
		playerNameLabel.setForeground(new Color(255, 215, 0));
		c1.gridx = 0; // column 0
		c1.gridy = 0; // row 0
		c1.gridwidth = 1;
		c1.gridheight = 1;
		c1.anchor = GridBagConstraints.SOUTHEAST;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		add(playerNameLabel, c1);
		
		playerNameTextField = new JTextField(8);
		c2.gridx = 1; // column 0
		c2.gridy = 0; // row 1
		c2.gridwidth = 1;
		c2.gridheight = 1;
		c2.insets = new Insets(0, 5, 5, 0);
		c2.anchor = GridBagConstraints.SOUTHWEST;
		c2.weightx = 1.0;
		c2.weighty = 1.0;
		add(playerNameTextField, c2);
				
		playerPointsLabel = new JLabel("Starting points*");
		playerPointsLabel.setForeground(new Color(255, 215, 0));
		c3.gridx = 0;
		c3.gridy = 1;
		c3.gridwidth = 1;
		c3.gridheight = 1;
		c3.insets = new Insets(5, 0, 0, 5);
		c3.anchor = GridBagConstraints.EAST;
		c3.weightx = 1.0;
		c3.weighty = 0;
		add(playerPointsLabel, c3);
				
		playerPointsTextField = new JTextField(8);
		c4.gridx = 1;
		c4.gridy = 1;
		c4.gridwidth = 1;
		c4.gridheight = 1;
		c4.insets = new Insets(5, 5, 0, 0);
		c4.anchor = GridBagConstraints.WEST;
		c4.weightx = 1.0;
		c4.weighty = 0;
		add(playerPointsTextField, c4);
				
		startGameButton = new JButton("Start Game");
		startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startGameButton.setBackground(new Color(202, 151, 74));
		startGameButton.setForeground(Color.WHITE);
		c5.gridx = 0;
		c5.gridy = 2;
		c5.gridwidth = 2;
		c5.gridheight = 1;
		c5.insets = new Insets(15, 0, 0, 0);
		c5.anchor = GridBagConstraints.NORTH;
		c5.weightx = 1.0;
		c5.weighty = 1.0;
		add(startGameButton, c5);
				
		quitGameButton = new JButton("Exit");
		quitGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		quitGameButton.setBackground(new Color(202, 151, 74));
		quitGameButton.setForeground(Color.WHITE);
		c6.gridx = 0;
		c6.gridy = 3;
		c6.gridwidth = 2;
		c6.gridheight = 1;
		c6.insets = new Insets(0, 0, 20, 20);
		c6.anchor = GridBagConstraints.SOUTHEAST;
		c6.weightx = 1.0;
		c6.weighty = 1.0;
		add(quitGameButton, c6);
	}
	
	public JButton getStartGameButton() {
		return startGameButton;
	}
	
	public JButton getQuitGameButton() {
		return quitGameButton;
	}
	
	public JTextField getPlayerPointsTextField() {
		return playerPointsTextField;
	}
	
	public JTextField getPlayerNameTextField() {
		return playerNameTextField;
	}
}
