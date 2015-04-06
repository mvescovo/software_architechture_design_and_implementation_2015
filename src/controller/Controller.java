package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.MainFrame;
import model.GameEngineCallbackImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Controller {
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private Listener listener;
	Player player;
	
	public Controller(GameEngine gameEngine, MainFrame mainFrame) {
		listener = new Listener();
		this.gameEngine = gameEngine;
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		this.mainFrame = mainFrame;
		
		// register listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addActionListener(listener);
		this.mainFrame.getToolBar().getPlaceBetButton().addActionListener(listener);
		this.mainFrame.getToolBar().getPlayerRollsButton().addActionListener(listener);
		this.mainFrame.getToolBar().getHouseRollsButton().addActionListener(listener);
		this.mainFrame.getToolBar().getDisplayResultsButton().addActionListener(listener);
		this.mainFrame.getToolBar().getQuitButton().addActionListener(listener);
		
		
	}
	
	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mainFrame.getPlayerPanel().getAddPlayerButton()) {
				// get player name
				String name = JOptionPane.showInputDialog("Enter name");
				String points = JOptionPane.showInputDialog("Enter starting points");

				// change model
				player = new SimplePlayer("1", name, Integer.parseInt(points));
				gameEngine.addPlayer(player);
				
				// change view
				mainFrame.getPlayerPanel().setPlayerName(name);
				mainFrame.getPlayerPanel().showPoints();
				mainFrame.getPlayerPanel().setPoints(points);
				mainFrame.getPlayerPanel().disableAddPlayerButton();
				mainFrame.getToolBar().enableBet();
				System.out.println("add player button clicked");
				System.out.printf("%s%d%s\n", "player has ", player.getPoints(), " points");
				System.out.println(gameEngine.getAllPlayers());
			} else if (e.getSource() == mainFrame.getToolBar().getPlaceBetButton()) {
				// get bet from user
				String bet = mainFrame.getToolBar().getBetTextField().getText();
				
				// change model
				if (gameEngine.placeBet(player, Integer.parseInt(bet)) == true) {
					// change view
					System.out.printf("%s%d\n", "placed bet for: ", player.getBet());
					mainFrame.getPlayerPanel().deductPoints(Integer.parseInt(bet));
					mainFrame.getPlayerPanel().setBetPoints(Integer.parseInt(bet));
					mainFrame.getPlayerPanel().displayBet();
					mainFrame.getToolBar().disableBet();
					mainFrame.getToolBar().enableRoll();
				} else {
					// change view
					System.out.printf("%s%d\n", "not enough points for bet of: ", player.getBet());
					mainFrame.getPlayerPanel().notEnoughPoints();
				}
			} else if (e.getSource() == mainFrame.getToolBar().getPlayerRollsButton()) {
				// change model
				gameEngine.rollPlayer(player, 1, 100, 20);
				
				// change view
				mainFrame.getToolBar().disableRoll();
				mainFrame.getToolBar().enableDisplayResults();
			} else if (e.getSource() == mainFrame.getToolBar().getHouseRollsButton()) {
				// change model
				gameEngine.rollHouse(1, 100, 20);
				
				// change view
				// done by callback thingamajig
			} else if (e.getSource() == mainFrame.getToolBar().getDisplayResultsButton()) {
				// change model
				gameEngine.calculateResult();
				player.placeBet(0);
				
				// change view
				mainFrame.getPlayerPanel().setPoints(Integer.toString(player.getPoints()));
				mainFrame.getPlayerPanel().setBetPoints(player.getBet());
				mainFrame.getToolBar().enableBet();
				mainFrame.getToolBar().disableDisplayResults();
			} else if (e.getSource() == mainFrame.getToolBar().getQuitButton()) {
				JFrame quit = new JFrame();
				int n = JOptionPane.showConfirmDialog(quit, "Seriously?", "Quit game confirmation", JOptionPane.YES_NO_OPTION);
				if (n == 0)
					System.exit(0);
			}
		}
	}
}
