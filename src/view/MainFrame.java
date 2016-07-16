package view;

import java.awt.*;

import javax.swing.*;

import controller.Controller;
import model.GameEngineCallbackImplGUI;
import model.interfaces.GameEngine;
import model.GameEngineClientStub;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -7523238222452192023L;
	private GameEngine gameEngine;
	private StartGamePanel startGamePanel;
	private Menu menu;
	private HeadingPanel heading;
	private PlayerPanel playerPanel;
	private TableAndToolbarContainerPanel tableAndToolbarContainerPanel;
	
	public MainFrame(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		this.gameEngine.addGameEngineCallback(new GameEngineCallbackImplGUI(this));
		((GameEngineClientStub)(this.gameEngine)).addGameEngineCallbackToGameEngineCallbackServer();
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
		setSize(600, 420);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		setResizable(false);
		new Controller(this.gameEngine, this);
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
