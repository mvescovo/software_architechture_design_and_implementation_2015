package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5849689848560582871L;
	JLabel playerTitle;
	JLabel playerName;
	JButton addPlayerButton;
	JLabel pointsTitle;
	JLabel points;
	JLabel betTitle;
	JLabel betPoints;
	int betPointsInt = 0;
	
	public PlayerPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(170, 20)));
		playerTitle = new JLabel("Players:");
		playerTitle.setForeground(new Color(255, 215, 0));
		playerTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(playerTitle);
		add(Box.createRigidArea(new Dimension(170, 5)));
		playerName = new JLabel("No players yet");
		playerName.setForeground(Color.WHITE);
		playerName.setAlignmentX(CENTER_ALIGNMENT);
		add(playerName);
		add(Box.createRigidArea(new Dimension(170, 10)));
		addPlayerButton = new JButton("Add Player");
		addPlayerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addPlayerButton.setBackground(new Color(202, 151, 74));
		addPlayerButton.setForeground(Color.WHITE);
		addPlayerButton.setAlignmentX(CENTER_ALIGNMENT);
		add(addPlayerButton);
		pointsTitle = new JLabel("Points:");
		pointsTitle.setForeground(new Color(255, 215, 0));
		pointsTitle.setAlignmentX(CENTER_ALIGNMENT);
		pointsTitle.setVisible(false);
		add(pointsTitle);
		add(Box.createRigidArea(new Dimension(170, 5)));
		points = new JLabel("");
		points.setForeground(Color.WHITE);
		points.setVisible(false);
		points.setAlignmentX(CENTER_ALIGNMENT);
		add(points);
		add(Box.createRigidArea(new Dimension(170, 5)));
		betTitle = new JLabel("Current bet:");
		betTitle.setForeground(new Color(255, 215, 0));
		betTitle.setAlignmentX(CENTER_ALIGNMENT);
		betTitle.setVisible(false);
		add(betTitle);
		add(Box.createRigidArea(new Dimension(170, 5)));
		betPoints = new JLabel(Integer.toString(betPointsInt));
		betPoints.setForeground(Color.WHITE);
		betPoints.setVisible(false);
		betPoints.setAlignmentX(CENTER_ALIGNMENT);
		add(betPoints);
		add(Box.createRigidArea(new Dimension(170, 5)));
		
		// panel settings
		setBackground(new Color(151, 42, 39));
	}
	
	public JButton getAddPlayerButton() {
		return addPlayerButton;
	}
	
	public void setPlayerName(String name) {
		playerName.setText(name);
	}
	
	public void disableAddPlayerButton() {
		addPlayerButton.setEnabled(false);
		addPlayerButton.setVisible(false);
	}
	
	public void showPoints() {
		pointsTitle.setVisible(true);
		points.setVisible(true);
	}
	
	public void setPoints(String points) {
		this.points.setText(points);
	}
	
	public void deductPoints(int points) {
		int currentPoints = Integer.parseInt(this.points.getText());
		int newPoints = currentPoints - points;
		this.points.setText(Integer.toString(newPoints));
	}
	
	public void notEnoughPoints() {
		JOptionPane.showMessageDialog(this, "Not enough points.");
	}
	
	public void setBetPoints(int points) {
		betPointsInt = points;
		betPoints.setText(Integer.toString(betPointsInt));
	}
	
	public void showBet() {
		betTitle.setVisible(true);
		betPoints.setVisible(true);
	}
	
	public void clickAddPlayer() {
		addPlayerButton.doClick();
	}
}
