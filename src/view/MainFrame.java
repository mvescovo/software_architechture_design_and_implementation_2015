package view;

import java.awt.*;

import javax.swing.*;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class MainFrame extends JFrame implements GameEngineCallback {
	private static final long serialVersionUID = -7523238222452192023L;
	GameEngine gameEngine;
	Menu menu;
	HeadingPanel heading;
	ToolBarPanel toolBar;
	PlayerPanel playerPanel;
	DicePanel dicePanel;
//	GameEngineCallback gameEngineCallback;

	
	public MainFrame(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
//		gameEngineCallback = this;
//		this.gameEngine.addGameEngineCallback(this);
		menu = new Menu();
		heading = new HeadingPanel();
		toolBar = new ToolBarPanel();
		playerPanel = new PlayerPanel();
		dicePanel = new DicePanel();

		setJMenuBar(menu);
		add(heading, BorderLayout.NORTH);
		add(toolBar, BorderLayout.SOUTH);
		add(playerPanel, BorderLayout.WEST);
		add(dicePanel, BorderLayout.CENTER);
		
		// set details of dice frame
		setTitle("Dice Game");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public ToolBarPanel getToolBar() {
		return toolBar;
	}
	
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	
	public DicePanel getDicePanel() {
		return dicePanel;
	}
	
	public Menu getMenu() {
		return menu;
	}

	@Override
	public void intermediateResult(final Player player, DicePair dicePair,
			GameEngine engine) {
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
				getDicePanel().setRollLabel2(player.getPlayerName());
				getDicePanel().setDice1(Integer.toString(num1));
				getDicePanel().setDice2(Integer.toString(num2));
			}
		});
	}

	@Override
	public void result(Player player, DicePair dicePair, GameEngine engine) {
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
				getDicePanel().setDice1(Integer.toString(num1));
				getDicePanel().setDice2(Integer.toString(num2));
				getToolBar().enableDisplayResults();
				getMenu().enableDisplayResultsMenu();
				getToolBar().focusDisplayResults();
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
				getDicePanel().setRollLabel2("House");
				getDicePanel().setDice1(Integer.toString(num1));
				getDicePanel().setDice2(Integer.toString(num2));
			}
		});
	}

	@Override
	public void houseResult(DicePair dicePair, GameEngine engine) {
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
				getDicePanel().setDice1(Integer.toString(num1));
				getDicePanel().setDice2(Integer.toString(num2));
//				getToolBar().enableDisplayResults();
//				getMenu().enableDisplayResultsMenu();
				getToolBar().focusActiveBetText();
			}
		});
	}
}
