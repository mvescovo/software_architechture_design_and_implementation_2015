package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class CrapsToolBarPanel extends JPanel {
	JToolBar toolBar;
	JButton addPlayerButton;
	JButton placeBetButton;
	JButton playerRollsButton;
	JButton houseRollsButton;
	JButton displayResultsButton;
	
	public CrapsToolBarPanel() {
		toolBar = new JToolBar("Craps toolbar");
		
		// buttons
		addPlayerButton = new JButton("Add player");
		toolBar.add(addPlayerButton);
		placeBetButton = new JButton("Place bet");
		toolBar.add(placeBetButton);
		playerRollsButton = new JButton("Player rolls dice");
		toolBar.add(playerRollsButton);
		houseRollsButton = new JButton("House rolls dice");
		toolBar.add(houseRollsButton);
		displayResultsButton = new JButton("Display results");
		toolBar.add(displayResultsButton);
		
		add(toolBar);
	}
	
	public JButton getAddPlayerButton() {
		return addPlayerButton;
	}
	
	public JButton getPlaceBetButton() {
		return placeBetButton;
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
}
