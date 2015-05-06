package view;

import java.awt.*;

import javax.swing.*;

import model.interfaces.GameEngine;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -7523238222452192023L;
	GameEngine gameEngine;
	StartGamePanel startGamePanel;
	Menu menu;
	HeadingPanel heading;
	PlayerPanel playerPanel;
	TableAndToolbarContainerPanel tableAndToolbarContainerPanel;
	
	public MainFrame(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		startGamePanel = new StartGamePanel();
		menu = new Menu();
		heading = new HeadingPanel();
		playerPanel = new PlayerPanel();
		tableAndToolbarContainerPanel = new TableAndToolbarContainerPanel();

		setJMenuBar(menu);
		add(heading, BorderLayout.NORTH);
		add(playerPanel, BorderLayout.WEST);
		add(tableAndToolbarContainerPanel, BorderLayout.CENTER);
		add(startGamePanel, BorderLayout.CENTER);
		
		// set details of main frame
		setTitle("Dice Game");
		setSize(600, 400);
//		setLocation(1200, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
		setVisible(true);
	}
	
	public StartGamePanel getStartGamePanel() {
		return startGamePanel;
	}
	
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	
	public TableAndToolbarContainerPanel getTableAndToolbarContainerPanel() {
		return tableAndToolbarContainerPanel;
	}
	
	public Menu getMenu() {
		return menu;
	}
}
