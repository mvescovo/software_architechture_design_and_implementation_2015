package view;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ToolBarPanel extends JPanel {
	private static final long serialVersionUID = -1874776971025449948L;
	JToolBar toolBar;
	JButton placeBetButton;
	JTextField betTextField;
	JButton playerRollsButton;
	JButton houseRollsButton;
	JButton quitButton;
	JButton exitButton;
	
	public ToolBarPanel() {
		setBackground(new Color(151, 42, 39));
		
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
		
		playerRollsButton = new JButton("Roll Player");
		playerRollsButton.setEnabled(false);
		playerRollsButton.setBackground(new Color(202,151,74));
		playerRollsButton.setForeground(new Color(255, 215, 0));
		playerRollsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(playerRollsButton);
		
		houseRollsButton = new JButton("Roll House");
		houseRollsButton.setEnabled(false);
		houseRollsButton.setBackground(new Color(202,151,74));
		houseRollsButton.setForeground(new Color(255, 215, 0));
		houseRollsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toolBar.add(houseRollsButton);
		
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
	
		setVisible(false);
	}
	
	public JButton getPlaceBetButton() {
		return placeBetButton;
	}
	
	public JTextField getBetTextField() {
		return betTextField;
	}
	
	public JButton getRollPlayerButton() {
		return playerRollsButton;
	}
	
	public JButton getRollHouseButton() {
		return houseRollsButton;
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
	
//	public void enableRoll() {
//		playerRollsButton.setEnabled(true);
//	}
	
//	public void disableRoll() {
//		playerRollsButton.setEnabled(false);
//	}
	
	public void focusActiveBetText() {
		betTextField.requestFocusInWindow();
	}
	
	public void clickPlaceBet() {
		placeBetButton.doClick();
	}
	
	public void focusPlayerRoll() {
		playerRollsButton.requestFocusInWindow();
	}
	
	public void clickRollPlayer() {
		playerRollsButton.doClick();
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
