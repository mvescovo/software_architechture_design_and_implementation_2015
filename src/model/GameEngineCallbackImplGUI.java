package model;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import view.MainFrame;
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
		final String statusText = player.getPlayerName() + " rolling";
		
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
					mainFrame.getgameTablePanel().getGameStatusPanel().getGameStatusLabel().setText(statusText);
					mainFrame.getgameTablePanel().getDicePanel().setDice1(Integer.toString(num1));
					mainFrame.getgameTablePanel().getDicePanel().setDice2(Integer.toString(num2));
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
					mainFrame.getgameTablePanel().getDicePanel().setDice1(Integer.toString(num1));
					mainFrame.getgameTablePanel().getDicePanel().setDice2(Integer.toString(num2));
					// TODO maybe change this part so it's not writing to a GUI object
					mainFrame.getgameTablePanel().getGameStatusPanel().setTempPlayerResult(total);
					mainFrame.getgameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText(player.getPlayerName() + "'s result: " + total);
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int total = num1 + num2;
		
		// some console output because it looks cool
		System.out.print("house intermediate: ");
		System.out.println(total);
		
		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					mainFrame.getgameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("House rolling");
					mainFrame.getgameTablePanel().getDicePanel().setDice1(Integer.toString(num1));
					mainFrame.getgameTablePanel().getDicePanel().setDice2(Integer.toString(num2));
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void houseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int houseTotal = num1 + num2;
		final int playerTotal = mainFrame.getgameTablePanel().getGameStatusPanel().getTempPlayerResult();
		
		// some console output because it looks cool
		System.out.print("house total: ");
		System.out.println(houseTotal);
		
		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					mainFrame.getgameTablePanel().getDicePanel().setDice1(Integer.toString(num1));
					mainFrame.getgameTablePanel().getDicePanel().setDice2(Integer.toString(num2));
					mainFrame.getgameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: " + houseTotal);
					if (playerTotal > houseTotal) {
						mainFrame.getgameTablePanel().getGameStatusPanel().getGameResultLabel().setText("You won!");
					} else if (playerTotal == houseTotal) {
						mainFrame.getgameTablePanel().getGameStatusPanel().getGameResultLabel().setText("Draw!");
					} else {
						mainFrame.getgameTablePanel().getGameStatusPanel().getGameResultLabel().setText("The house won");
					}
					
					mainFrame.getgameTablePanel().getToolBar().focusActiveBetText();
					mainFrame.getgameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place another bet to play again");
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
