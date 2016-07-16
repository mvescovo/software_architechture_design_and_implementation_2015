package model;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import view.MainFrame;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineCallbackImplGUI implements GameEngineCallback {
	private MainFrame mainFrame;
	
	public GameEngineCallbackImplGUI(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void intermediateResult(final Player player, DicePair dicePair,
			GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int total = num1 + num2;
		final String statusText = player.getPlayerName() + " is rolling...";
		
		// some console output for this player
//		System.out.print(player.getPlayerName() + " intermediate: ");
//		System.out.println(total);
		
		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText(statusText);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void result(final Player player, DicePair dicePair, final GameEngine gameEngine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int total = num1 + num2;
		
		// some console output for this player
//		System.out.print(player.getPlayerName() + " total: ");
//		System.out.println(total);
		
		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run()
				{
//					System.out.println("Am I running on the EDT? " + SwingUtilities.isEventDispatchThread());
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText(player.getPlayerName() + "'s result: " + total);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setVisible(true);
					
					// check if house has been pressed by another player
					if (((GameEngineClientStub)gameEngine).checkHouseRolling()) {
						// don't enable house roll
					} else {
						// enable roll house
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().setEnabled(true);
						mainFrame.getMenu().getRollHouseMenuItem().setEnabled(true);
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().requestFocusInWindow();
					}
					
					// enable quit and exit
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().setEnabled(true);
					mainFrame.getMenu().getQuitMenuItem().setEnabled(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().setEnabled(true);
					mainFrame.getMenu().getExitMenuItem().setEnabled(true);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();

		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					// disable things bearing in mind that new players might join during the roll

					// disable bet
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().disableBet();
					mainFrame.getMenu().getplaceBetMenuItem().setEnabled(false);
					// disable add points
					mainFrame.getPlayerPanel().getAddPointsButton().setEnabled(false);
					// disable quit and exit
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().setEnabled(false);
					mainFrame.getMenu().getQuitMenuItem().setEnabled(false);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().setEnabled(false);
					mainFrame.getMenu().getExitMenuItem().setEnabled(false);
					// label stuff
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setVisible(false);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("House is rolling...");
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void houseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int houseTotal = num1 + num2;

		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					// update house result
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: " + houseTotal);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setVisible(true);
					
					// enable quit and exit
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().setEnabled(true);
					mainFrame.getMenu().getQuitMenuItem().setEnabled(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().setEnabled(true);
					mainFrame.getMenu().getExitMenuItem().setEnabled(true);
					
					// enable add points
					mainFrame.getPlayerPanel().getAddPointsButton().setEnabled(true);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void updateResult(final Player player, GameEngine gameEngine) {
		if (((SimplePlayer)player).getIsParticipatingInRound()) {
			final int oldPoints = Integer.parseInt(mainFrame.getPlayerPanel().getPoints()) + Integer.parseInt(mainFrame.getPlayerPanel().getBetPoints().getText());
			final int newPoints = player.getPoints();
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if (newPoints > oldPoints) {
						// player won
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText("You won! :)");
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(true);
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setForeground(new Color(255, 215, 0));
					} else if (newPoints == oldPoints) {
						// draw
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText("Draw");
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(true);
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setForeground(Color.WHITE);
					} else if (newPoints < oldPoints) {
						// house won
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText("The house won :(");
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(true);
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setForeground(Color.DARK_GRAY);
					}
					
					// update GUI points
					mainFrame.getPlayerPanel().getBetPoints().setText("0");
					mainFrame.getPlayerPanel().setPoints(Integer.toString(newPoints));
					// update GUI item status
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place another bet to play again");
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableBet();
					mainFrame.getMenu().getplaceBetMenuItem().setEnabled(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusActiveBetText();
				}
			});
			
			((SimplePlayer)player).setParticipatingInRound(false);
			((SimplePlayer)(((GameEngineClientStub)gameEngine).getPlayer())).setParticipatingInRound(false);
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText(player.getPlayerName() + " sat this round out.");
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setVisible(true);

					if (0 == player.getBet()) {
						// update GUI item status
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place a bet to play");
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableBet();
						mainFrame.getMenu().getplaceBetMenuItem().setEnabled(true);
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusActiveBetText();
					} else if (player.getBet() > 0) {
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Roll the dice to continue");
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollPlayerButton().setEnabled(true);
						mainFrame.getMenu().getRollPlayerMenuItem().setEnabled(true);
						mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusPlayerRoll();
					}
				}
			});
		}
	}
	
	public void disableClientforHouseRoll() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run()
				{
					// disable betting
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().disableBet();
					mainFrame.getMenu().getplaceBetMenuItem().setEnabled(false);
					
					// disable roll player
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollPlayerButton().setEnabled(false);
					mainFrame.getMenu().getRollPlayerMenuItem().setEnabled(false);
					
					// disable roll house button
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().setEnabled(false);
					mainFrame.getMenu().getRollHouseMenuItem().setEnabled(false);
					
					// disable quit and exit
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().setEnabled(false);
					mainFrame.getMenu().getQuitMenuItem().setEnabled(false);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().setEnabled(false);
					mainFrame.getMenu().getExitMenuItem().setEnabled(false);
					
					// hide previous game result
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(false);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void showSittingPlayerMessage(final Player player) {
		if (!((SimplePlayer)player).getIsParticipatingInRound()) {
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@Override
					public void run()
					{
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText(player.getPlayerName() + " sat this round out.");
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setVisible(true);
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void notifyPlayersStillRolling() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run()
				{
					// let player that rolled house know that other players have in-progress rolls still happening
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Waiting for rolls to finish...");
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
