package view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.sun.glass.events.KeyEvent;

public class MainFrame extends JFrame {
	Menu crapsMenu;
	HeadingPanel heading;
	ToolBarPanel toolBar;
	PlayerPanel playerPanel;
	RollingDicePanel dicePanel;

	
	public MainFrame() {
		crapsMenu = new Menu();
		heading = new HeadingPanel();
		toolBar = new ToolBarPanel();
		playerPanel = new PlayerPanel();
		dicePanel = new RollingDicePanel();

		setJMenuBar(crapsMenu);
		add(heading, BorderLayout.NORTH);
		add(toolBar, BorderLayout.SOUTH);
		add(playerPanel, BorderLayout.WEST);
		add(dicePanel, BorderLayout.CENTER);
		
		// set details of dice frame
		setTitle("Dice Game");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public ToolBarPanel getToolBar() {
		return toolBar;
	}
	
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
}
