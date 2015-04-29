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
	GameTablePanel gameTablePanel;
	
	public MainFrame(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		startGamePanel = new StartGamePanel();
		menu = new Menu();
		heading = new HeadingPanel();
		playerPanel = new PlayerPanel();
		gameTablePanel = new GameTablePanel();

		setJMenuBar(menu);
		add(heading, BorderLayout.NORTH);
		add(playerPanel, BorderLayout.WEST);
		add(gameTablePanel, BorderLayout.CENTER);
		add(startGamePanel, BorderLayout.CENTER);
		
		// set details of main frame
		setTitle("Dice Game");
		setSize(600, 400);
//		setLocation(1200, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public StartGamePanel getStartGamePanel() {
		return startGamePanel;
	}
	
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	
	public GameTablePanel getgameTablePanel() {
		return gameTablePanel;
	}
	
	public Menu getMenu() {
		return menu;
	}
}
