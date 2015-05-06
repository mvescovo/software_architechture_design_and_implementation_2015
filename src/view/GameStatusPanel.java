package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStatusPanel extends JPanel {
	private static final long serialVersionUID = -5544648627797945979L;
	private JLabel gameStatusLabel;
	private JLabel playerResultLabel;
	private int tempPlayerResult = 0;
	private JLabel houseResultLabel;
	private JLabel gameResultLabel;
	private Font font;
	
	public GameStatusPanel() {
		font = new Font("SansSerif", Font.BOLD, 20);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(0, 153, 0));
		
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		gameStatusLabel = new JLabel("Place a bet to play");
		gameStatusLabel.setFont(font);
		gameStatusLabel.setForeground(Color.WHITE);
		gameStatusLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(gameStatusLabel);
		
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		playerResultLabel = new JLabel("Player result: N/A");
		playerResultLabel.setFont(font);
		playerResultLabel.setForeground(Color.WHITE);
		playerResultLabel.setAlignmentX(CENTER_ALIGNMENT);
		playerResultLabel.setVisible(false);
		add(playerResultLabel);
		
		add(Box.createRigidArea(new Dimension(0, 5)));
		
		houseResultLabel = new JLabel("House result: N/A");
		houseResultLabel.setFont(font);
		houseResultLabel.setForeground(Color.WHITE);
		houseResultLabel.setAlignmentX(CENTER_ALIGNMENT);
		houseResultLabel.setVisible(false);
		add(houseResultLabel);
		
		add(Box.createRigidArea(new Dimension(0, 5)));
		
		gameResultLabel = new JLabel("Winner: N/A");
		gameResultLabel.setFont(font);
		gameResultLabel.setForeground(Color.WHITE);
		gameResultLabel.setAlignmentX(CENTER_ALIGNMENT);
		gameResultLabel.setVisible(false);
		add(gameResultLabel);
	}

	public JLabel getGameStatusLabel() {
		return gameStatusLabel;
	}

	public JLabel getPlayerResultLabel() {
		return playerResultLabel;
	}
	
	public JLabel getHouseResultLabel() {
		return houseResultLabel;
	}
	
	public JLabel getGameResultLabel() {
		return gameResultLabel;
	}
	
	public void setGameStatusLabel(JLabel gameStatusLabel) {
		this.gameStatusLabel = gameStatusLabel;
	}
	
	public void setTempPlayerResult(int result) {
		tempPlayerResult = result;
	}
	
	public int getTempPlayerResult() {
		return tempPlayerResult;
	}
}
