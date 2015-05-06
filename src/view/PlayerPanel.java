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
import javax.swing.JTextField;

public class PlayerPanel extends JPanel {
	private static final long serialVersionUID = 5849689848560582871L;
	JLabel playerTitle;
	JLabel playerName;
	JButton addPlayerButton;
	JLabel pointsTitle;
	JLabel points;
	JLabel betTitle;
	JLabel betPoints;
	JButton addPointsButton;
	JTextField addPointsTextField;
	int betPointsInt = 0;
	
	public PlayerPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(151, 42, 39));
		
		add(Box.createRigidArea(new Dimension(170, 20)));
		
		playerTitle = new JLabel("Player:");
		playerTitle.setForeground(new Color(255, 215, 0));
		playerTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(playerTitle);
		
		add(Box.createRigidArea(new Dimension(170, 5)));
		
		playerName = new JLabel("No player yet");
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
		add(betTitle);
		
		add(Box.createRigidArea(new Dimension(170, 5)));
		
		betPoints = new JLabel(Integer.toString(betPointsInt));
		betPoints.setForeground(Color.WHITE);
		betPoints.setAlignmentX(CENTER_ALIGNMENT);
		add(betPoints);
		
		add(Box.createRigidArea(new Dimension(170, 20)));
		
		addPointsButton = new JButton("Add Points");
		addPointsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addPointsButton.setBackground(new Color(202, 151, 74));
		addPointsButton.setForeground(Color.WHITE);
		addPointsButton.setAlignmentX(CENTER_ALIGNMENT);
		addPointsButton.setEnabled(false);
		addPointsButton.setVisible(false);
		add(addPointsButton);
		
		add(Box.createRigidArea(new Dimension(170, 10)));
		
		addPointsTextField = new JTextField(4);
		addPointsTextField.setMaximumSize(new Dimension(90, 20));
		addPointsTextField.setEnabled(false);
		addPointsTextField.setVisible(false);
		add(addPointsTextField);
		
		setVisible(false);
	}
	
	public JButton getAddPlayerButton() {
		return addPlayerButton;
	}
	
	public void setPlayerName(String name) {
		playerName.setText(name);
	}
	
	public String getPlayerName() {
		return playerName.getText();
	}
	
	public void disableAddPlayerButton() {
		addPlayerButton.setEnabled(false);
		addPlayerButton.setVisible(false);
	}
	
	public void enableAddPlayerButton() {
		addPlayerButton.setEnabled(true);
		addPlayerButton.setVisible(true);
	}
	
	public void showPoints() {
		pointsTitle.setVisible(true);
		points.setVisible(true);
	}
	
	public void hidePoints() {
		pointsTitle.setVisible(false);
		points.setVisible(false);
	}
	
	public void setPoints(String points) {
		this.points.setText(points);
	}
	
	public void deductPoints(int points) {
		int currentPoints = Integer.parseInt(this.points.getText());
		int newPoints = currentPoints - points;
		this.points.setText(Integer.toString(newPoints));
	}
	
	public void addPoints(int points) {
		int currentPoints = Integer.parseInt(this.points.getText());
		int newPoints = currentPoints + points;
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
	
	public void hideBet() {
		betTitle.setVisible(false);
		betPoints.setVisible(false);
	}
	
	public void clickAddPlayer() {
		addPlayerButton.doClick();
	}
	
	public void clickAddPoints() {
		addPointsButton.doClick();
	}
	
	public void focusAddPlayerButton() {
		addPlayerButton.requestFocusInWindow();
	}
	
	public JTextField getAddPointsTextField() {
		return addPointsTextField;
	}
	
	public JButton getAddPointsButton() {
		return addPointsButton;
	}
	
	public void enableAddPoints() {
		addPointsButton.setEnabled(true);
		addPointsButton.setVisible(true);
		addPointsTextField.setEnabled(true);
		addPointsTextField.setVisible(true);
	}
	
	public void disableAddPoints() {
		addPointsButton.setEnabled(false);
		addPointsButton.setVisible(false);
		addPointsTextField.setEnabled(false);
		addPointsTextField.setVisible(false);
	}
}
