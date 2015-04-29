package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

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
		String playerString = null;
		String bet = null;
		int betInt = 0;
		boolean betOk = true;
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
		bet = mainFrame.getgameTablePanel().getToolBar().getBetTextField().getText();
		
		if (bet != null) {
			try {
				betInt = Integer.parseInt(bet);
			} catch (NumberFormatException e2){
				JOptionPane.showMessageDialog(mainFrame, "Bet needs to be a number.", "Invalid bet", 0);
				System.out.println("bet not a number");
				betOk = false;
			}
		}
		
		if ((betOk == true) && betInt < 0) {
			JOptionPane.showMessageDialog(mainFrame, "Bet needs to be 0 or greater.", "Invalid bet", 0);
			System.out.println("bet needs to be 0 or greater.");
			betOk = false;
		}
		
		if (betOk == true) {
			// change model
			if (gameEngine.placeBet(currPlayer, betInt) == true) {
				// change view
				System.out.printf("%s%d\n", "placed bet for: ", currPlayer.getBet());
				mainFrame.getPlayerPanel().deductPoints(Integer.parseInt(bet));
				mainFrame.getPlayerPanel().setBetPoints(Integer.parseInt(bet));
				mainFrame.getPlayerPanel().showBet();
				mainFrame.getgameTablePanel().getToolBar().disableBet();
				mainFrame.getMenu().disablePlaceBetMenu();
				mainFrame.getgameTablePanel().getToolBar().enableRoll();
				mainFrame.getMenu().enablePlayerRollsMenu();
				mainFrame.getgameTablePanel().getToolBar().focusPlayerRoll();
				mainFrame.getgameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Roll the dice to continue");
				mainFrame.getgameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText("Player result: N/A");
				mainFrame.getgameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: N/A");
				mainFrame.getgameTablePanel().getGameStatusPanel().getGameResultLabel().setText("Winner: N/A");
			} else {
				// change view
				System.out.printf("%s%d\n", "not enough points for bet of: ", currPlayer.getBet());
				mainFrame.getPlayerPanel().notEnoughPoints();
			}
		}
	}
}
