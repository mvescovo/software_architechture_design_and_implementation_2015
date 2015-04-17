package view;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ToolBarPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1874776971025449948L;
	JToolBar toolBar;
	JButton placeBetButton;
	JTextField betTextField;
	JButton playerRollsButton;
	JButton houseRollsButton;
	JButton displayResultsButton;
	JButton quitButton;
	JButton exitButton;
	
	public ToolBarPanel() {
		toolBar = new JToolBar("Craps toolbar");
		toolBar.setFloatable(false);
		
		// buttons
		placeBetButton = new JButton("Place bet");
		placeBetButton.setEnabled(false);
		placeBetButton.setBackground(new Color(202,151,74));
		placeBetButton.setForeground(Color.WHITE);
		placeBetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(placeBetButton);
		betTextField = new JTextField(8);
		betTextField.setEnabled(false);
		toolBar.add(betTextField);
		playerRollsButton = new JButton("Player rolls dice");
		playerRollsButton.setEnabled(false);
		playerRollsButton.setBackground(new Color(202,151,74));
		playerRollsButton.setForeground(new Color(255, 215, 0));
		playerRollsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(playerRollsButton);
		houseRollsButton = new JButton("House rolls dice");
		houseRollsButton.setEnabled(false);
		houseRollsButton.setBackground(new Color(202,151,74));
		houseRollsButton.setForeground(Color.WHITE);
		houseRollsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(houseRollsButton);
		displayResultsButton = new JButton("Display results");
		displayResultsButton.setEnabled(false);
		displayResultsButton.setBackground(new Color(202,151,74));
		displayResultsButton.setForeground(Color.WHITE);
		displayResultsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(displayResultsButton);
		quitButton = new JButton("Quit");
		quitButton.setEnabled(false);
		quitButton.setBackground(new Color(202,151,74));
		quitButton.setForeground(Color.WHITE);
		quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(quitButton);
		exitButton = new JButton("Exit");
		exitButton.setBackground(new Color(202,151,74));
		exitButton.setForeground(Color.WHITE);
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(exitButton);
		
		add(toolBar);
		
		// panel settings
		setBackground(new Color(151, 42, 39));
	}
	
	public JButton getPlaceBetButton() {
		return placeBetButton;
	}
	
	public JTextField getBetTextField() {
		return betTextField;
	}
	
	public JButton getPlayerRollsButton() {
		return playerRollsButton;
	}
	
	public JButton getHouseRollsButton() {
		return houseRollsButton;
	}
	
	public JButton getDisplayResultsButton() {
		return displayResultsButton;
	}
	
	public JButton getQuitButton() {
		return quitButton;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}
	
	public void enableBet() {
		placeBetButton.setEnabled(true);
		betTextField.setEnabled(true);
	}
	
	public void disableBet() {
		placeBetButton.setEnabled(false);
		betTextField.setEnabled(false);
	}
	
	public void enableRoll() {
		playerRollsButton.setEnabled(true);
	}
	
	public void disableRoll() {
		playerRollsButton.setEnabled(false);
	}
	
	public void enableHouseRoll() {
		houseRollsButton.setEnabled(true);
	}
	
	public void disableHouseRoll() {
		houseRollsButton.setEnabled(false);
	}
	
	public void enableDisplayResults() {
		displayResultsButton.setEnabled(true);
	}
	
	public void disableDisplayResults() {
		displayResultsButton.setEnabled(false);
	}
	
	public void focusActiveBetText() {
		betTextField.requestFocusInWindow();
	}
	
	public void clickPlaceBet() {
		placeBetButton.doClick();
	}
	
	public void focusPlayerRoll() {
		playerRollsButton.requestFocusInWindow();
	}
	
	public void clickPlayerRolls() {
		playerRollsButton.doClick();
	}
	
	public void clickHouseRolls() {
		houseRollsButton.doClick();
	}
	
	public void focusHouseRolls() {
		houseRollsButton.requestFocusInWindow();
	}
	
	public void focusDisplayResults() {
		displayResultsButton.requestFocusInWindow();
	}
	
	public void clickDisplayResults() {
		displayResultsButton.doClick();
	}
	
	public void enableQuit() {
		quitButton.setEnabled(true);
	}
	
	public void disableQuit() {
		quitButton.setEnabled(false);
	}
	
	public void clickQuit() {
		quitButton.doClick();
	}
	
	public void clickExit() {
		exitButton.doClick();
	}
	
	public void setBetTextField(String value) {
		betTextField.setText(value);
	}
}
