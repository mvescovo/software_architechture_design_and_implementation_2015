package model;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import javax.swing.SwingUtilities;

import view.MainFrame;
import model.Commands.Command;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineCallbackImplGUI implements GameEngineCallback {
	MainFrame mainFrame;
	
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
		
		// some console output because it looks cool
		System.out.print(player.getPlayerName() + " intermediate: ");
		System.out.println(total);
		
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
	public void result(final Player player, DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int total = num1 + num2;
		
		// some console output because it looks cool
		System.out.print(player.getPlayerName() + " total: ");
		System.out.println(total);
		
		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					System.out.println("Am I running on the EDT? " + SwingUtilities.isEventDispatchThread());
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText(player.getPlayerName() + "'s result: " + total);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setVisible(true);
					// change view after roll has finished
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().setEnabled(true);
					mainFrame.getMenu().getRollHouseMenuItem().setEnabled(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().requestFocusInWindow();
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
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("House is rolling...");
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
					System.out.println("trying to update dice for house result on GUI");
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: " + houseTotal);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setVisible(true);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void updateResult(Player player) {
		int oldPoints = Integer.parseInt(mainFrame.getPlayerPanel().getPoints()) + Integer.parseInt(mainFrame.getPlayerPanel().getBetPoints().getText());
		int newPoints = player.getPoints();
		
		System.out.println("oldPoints: " + oldPoints);
		System.out.println("newPoints: " + newPoints);
		
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
		
		mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusActiveBetText();
		mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place another bet to play again");
		mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableBet();
		mainFrame.getMenu().getplaceBetMenuItem().setEnabled(true);
		mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusActiveBetText();
	}
}
