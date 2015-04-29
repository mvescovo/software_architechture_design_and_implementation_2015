package model;

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
		
		// update GUI view
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.print("intermediate: ");
				System.out.println(total);
				mainFrame.getgameTablePanel().getGameStatusPanel().getGameStatusLabel().setText(statusText);
				mainFrame.getgameTablePanel().getDicePanel().setDice1(Integer.toString(num1));
				mainFrame.getgameTablePanel().getDicePanel().setDice2(Integer.toString(num2));
			}
		});
	}

	@Override
	public void result(final Player player, DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int total = num1 + num2;
		
		// update GUI view
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.print("total: ");
				System.out.println(total);
				mainFrame.getgameTablePanel().getDicePanel().setDice1(Integer.toString(num1));
				mainFrame.getgameTablePanel().getDicePanel().setDice2(Integer.toString(num2));
				mainFrame.getgameTablePanel().getGameStatusPanel().setTempPlayerResult(total);
				mainFrame.getgameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText(player.getPlayerName() + "'s result: " + total);
			}
		});
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int total = num1 + num2;
		
		// update GUI view
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.print("intermediate: ");
				System.out.println(total);
				mainFrame.getgameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("House rolling");
				mainFrame.getgameTablePanel().getDicePanel().setDice1(Integer.toString(num1));
				mainFrame.getgameTablePanel().getDicePanel().setDice2(Integer.toString(num2));
			}
		});
	}

	@Override
	public void houseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int houseTotal = num1 + num2;
		final int playerTotal = mainFrame.getgameTablePanel().getGameStatusPanel().getTempPlayerResult();
		
		// update GUI view
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.print("total: ");
				System.out.println(houseTotal);
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
	}
}
