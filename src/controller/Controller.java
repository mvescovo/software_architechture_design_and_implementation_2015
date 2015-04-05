package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CrapsMainFrame;
import model.GameEngineCallbackImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Controller {
	private GameEngine gameEngine;
	private CrapsMainFrame mainFrame;
	private Listener listener;
	Player player;
	
	public Controller(GameEngine gameEngine, CrapsMainFrame mainFrame) {
		listener = new Listener();
		this.gameEngine = gameEngine;
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		this.mainFrame = mainFrame;
		
		this.mainFrame.getCrapsToolBar().getAddPlayerButton().addActionListener(listener);
		this.mainFrame.getCrapsToolBar().getPlaceBetButton().addActionListener(listener);
		this.mainFrame.getCrapsToolBar().getPlayerRollsButton().addActionListener(listener);
		this.mainFrame.getCrapsToolBar().getHouseRollsButton().addActionListener(listener);
		this.mainFrame.getCrapsToolBar().getDisplayResultsButton().addActionListener(listener);
	}
	
	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mainFrame.getCrapsToolBar().getAddPlayerButton()) {
				// change model
				player = new SimplePlayer("25", "New player", 3000);
				gameEngine.addPlayer(player);
				
				// change view
				System.out.println("add player button clicked");
				System.out.printf("%s%d%s\n", "player has ", player.getPoints(), " points");
				System.out.println(gameEngine.getAllPlayers());
			} else if (e.getSource() == mainFrame.getCrapsToolBar().getPlaceBetButton()) {
				// change model
				gameEngine.placeBet(player, 200);
				
				// change view
				System.out.printf("%s%d\n", "placed bet for: ", player.getBet());
			} else if (e.getSource() == mainFrame.getCrapsToolBar().getPlayerRollsButton()) {
				// change model
				gameEngine.rollPlayer(player, 1, 100, 20);
				
				// change view
				// done by callback thingamajig
			} else if (e.getSource() == mainFrame.getCrapsToolBar().getHouseRollsButton()) {
				// change model
				gameEngine.rollHouse(1, 100, 20);
				
				// change view
				// done by callback thingamajig
			} else if (e.getSource() == mainFrame.getCrapsToolBar().getDisplayResultsButton()) {
				// change model
				gameEngine.calculateResult();
				
				// change view
				// done by callback thingamajig
			}
		}
	}
}
