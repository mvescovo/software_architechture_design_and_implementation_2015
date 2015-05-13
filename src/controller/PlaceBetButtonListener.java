package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.MainFrame;
import model.GameEngineClientStub;
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
		String bet = null;
		final int betInt;
		boolean betOk = true;

		// TODO fix this later to do validation using the validation class. maybe look at doing more error checking on sever
		// get bet from user
		bet = mainFrame.getTableAndToolbarContainerPanel().getToolBar().getBetTextField().getText();
		
		if (bet != null) {
			try {
				betInt = Integer.parseInt(bet);
				
				// place the bet to the gameEngine
				if (gameEngine.placeBet(null, betInt)) {
					System.out.println("GUI was told bet ok");
					
					// update GUI
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							mainFrame.getPlayerPanel().deductPoints(betInt);
							mainFrame.getPlayerPanel().setBetPoints(betInt);
							mainFrame.getPlayerPanel().showBet();
							mainFrame.getTableAndToolbarContainerPanel().getToolBar().disableBet();
							mainFrame.getMenu().getplaceBetMenuItem().setEnabled(false);
							mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollPlayerButton().setEnabled(true);
							mainFrame.getMenu().getRollPlayerMenuItem().setEnabled(true);
							mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusPlayerRoll();
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Roll the dice to continue");
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText("Player result: N/A");
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setVisible(false);
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: N/A");
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setVisible(false);
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText("Winner: N/A");
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(false);
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setVisible(false);
							mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setVisible(false);
						}
					});
				} else {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							mainFrame.getTableAndToolbarContainerPanel().getToolBar().getBetTextField().setText(null);
						}
					});
					
					System.out.println("gui was told bet not ok");
					JOptionPane.showMessageDialog(mainFrame, "Bet must not be less than zero or greater than points.", "Invalid bet", 0);
					System.out.println("Bet must not be less than zero or greater than points.");
					betOk = false;
				}
			} catch (NumberFormatException e2){
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().getBetTextField().setText(null);
					}
				});
				
				JOptionPane.showMessageDialog(mainFrame, "Bet needs to be a number.", "Invalid bet", 0);
				System.out.println("bet not a number");
				betOk = false;
			}
		}
	}
}
