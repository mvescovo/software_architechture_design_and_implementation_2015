package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	
	public StartGamePanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(new Color(151, 42, 39));
		
		add(Box.createRigidArea(new Dimension(0, 80)));
		
		playerNameLabel = new JLabel("Player name*");
		playerNameLabel.setForeground(new Color(255, 215, 0));
		playerNameLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(playerNameLabel);
		
		add(Box.createRigidArea(new Dimension(0, 2)));

		playerNameTextField = new JTextField(8);
		playerNameTextField.setMaximumSize(new Dimension(100, 20));
		playerNameTextField.setAlignmentX(CENTER_ALIGNMENT);
		add(playerNameTextField);
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		playerPointsLabel = new JLabel("Starting points*");
		playerPointsLabel.setForeground(new Color(255, 215, 0));
		playerPointsLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(playerPointsLabel);
		
		add(Box.createRigidArea(new Dimension(0, 2)));
		
		playerPointsTextField = new JTextField(8);
		playerPointsTextField.setMaximumSize(new Dimension(100, 20));
		playerPointsTextField.setAlignmentX(CENTER_ALIGNMENT);
		add(playerPointsTextField);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		
		startGameButton = new JButton("Start Game");
		startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startGameButton.setBackground(new Color(202, 151, 74));
		startGameButton.setForeground(Color.WHITE);
		startGameButton.setAlignmentX(CENTER_ALIGNMENT);
		add(startGameButton);
	}
	
	public JButton getStartGameButton() {
		return startGameButton;
	}
	
	public JTextField getPlayerPointsTextField() {
		return playerPointsTextField;
	}
	
	public JTextField getPlayerNameTextField() {
		return playerNameTextField;
	}
}
