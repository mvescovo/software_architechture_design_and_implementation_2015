package view;

import java.awt.*;
import javax.swing.*;

import model.interfaces.GameEngine;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7523238222452192023L;
	GameEngine gameEngine;
	Menu menu;
	HeadingPanel heading;
	ToolBarPanel toolBar;
	PlayerPanel playerPanel;
	DicePanel dicePanel;

	
	public MainFrame(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		menu = new Menu();
		heading = new HeadingPanel();
		toolBar = new ToolBarPanel();
		playerPanel = new PlayerPanel();
		dicePanel = new DicePanel();

		setJMenuBar(menu);
		add(heading, BorderLayout.NORTH);
		add(toolBar, BorderLayout.SOUTH);
		add(playerPanel, BorderLayout.WEST);
		add(dicePanel, BorderLayout.CENTER);
		
		// set details of dice frame
		setTitle("Dice Game");
		setSize(800, 600);
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
	
	public DicePanel getDicePanel() {
		return dicePanel;
	}
	
	public Menu getMenu() {
		return menu;
	}
}
