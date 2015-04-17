package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import view.MainFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlaceBetButtonListener implements ActionListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
	
	public PlaceBetButtonListener(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Player currPlayer = null;
		String playerString;
		Collection<Player> players = new ArrayList<Player>();
		
		// get the player that pressed the button
		playerString = mainFrame.getPlayerPanel().getPlayerName();
		players = gameEngine.getAllPlayers();
		
		for (Player player : players) {
			if (player.getPlayerName() == playerString) {
				currPlayer = player;
				continue;
			}
		}
		
		// get bet from user
		String bet = mainFrame.getToolBar().getBetTextField().getText();
		
		// change model
		if (gameEngine.placeBet(currPlayer, Integer.parseInt(bet)) == true) {
			// change view
			System.out.printf("%s%d\n", "placed bet for: ", currPlayer.getBet());
			mainFrame.getPlayerPanel().deductPoints(Integer.parseInt(bet));
			mainFrame.getPlayerPanel().setBetPoints(Integer.parseInt(bet));
			mainFrame.getPlayerPanel().showBet();
			mainFrame.getToolBar().disableBet();
			mainFrame.getMenu().disablePlaceBetMenu();
			mainFrame.getToolBar().enableRoll();
			mainFrame.getMenu().enablePlayerRollsMenu();
			mainFrame.getToolBar().focusPlayerRoll();
		} else {
			// change view
			System.out.printf("%s%d\n", "not enough points for bet of: ", currPlayer.getBet());
			mainFrame.getPlayerPanel().notEnoughPoints();
		}
	}
}
