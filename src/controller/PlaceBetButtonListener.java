package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.MainFrame;
import model.interfaces.GameEngine;

public class PlaceBetButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	
	public PlaceBetButtonListener(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String bet = null;
		final int betInt;

		// get bet from user
		bet = mainFrame.getTableAndToolbarContainerPanel().getToolBar().getBetTextField().getText();
		
		if (bet != null) {
			try {
				betInt = Integer.parseInt(bet);
				
				// pass the bet to the gameEngine
				if (gameEngine.placeBet(null, betInt)) {
					// bet has gone all the way through the chain and back again
//					System.out.println("GUI was told bet ok");
					
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
					
					// bet has gone all the way through the chain and back again
//					System.out.println("gui was told bet not ok");
					JOptionPane.showMessageDialog(mainFrame, "Bet must not be less than zero or greater than points.", "Invalid bet", 0);
//					System.out.println("Bet must not be less than zero or greater than points.");
				}
			} catch (NumberFormatException e2){
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().getBetTextField().setText(null);
					}
				});
				
				JOptionPane.showMessageDialog(mainFrame, "Bet needs to be a number.", "Invalid bet", 0);
//				System.out.println("bet not a number");
			}
		}
	}
}
