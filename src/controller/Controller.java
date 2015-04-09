package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.MainFrame;
import model.GameEngineCallbackImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Controller {
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private Listener listener;
	private ListenerKey listenerKey;
	private DicePanelListener dicePanelListener;
	private AddPlayerButtonListener addPlayerButtonListener;
	Player player = null;
	
	public Controller(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl(mainFrame));
		listener = new Listener();
		listenerKey = new ListenerKey();
		dicePanelListener = new DicePanelListener();
		addPlayerButtonListener = new AddPlayerButtonListener(gameEngine, mainFrame, player);

		// register listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addActionListener(addPlayerButtonListener);
		
		
		// register action listeners
		//this.mainFrame.getPlayerPanel().getAddPlayerButton().addActionListener(listener);
		this.mainFrame.getToolBar().getPlaceBetButton().addActionListener(listener);
		this.mainFrame.getToolBar().getBetTextField().addActionListener(listener);
		this.mainFrame.getToolBar().getPlayerRollsButton().addActionListener(listener);
		this.mainFrame.getToolBar().getHouseRollsButton().addActionListener(listener);
		this.mainFrame.getToolBar().getDisplayResultsButton().addActionListener(listener);
		this.mainFrame.getToolBar().getQuitButton().addActionListener(listener);
		
		// register key listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addKeyListener(listenerKey);
		this.mainFrame.getToolBar().getPlayerRollsButton().addKeyListener(listenerKey);
		this.mainFrame.getToolBar().getHouseRollsButton().addKeyListener(listenerKey);
		this.mainFrame.getToolBar().getDisplayResultsButton().addKeyListener(listenerKey);
		this.mainFrame.getToolBar().getQuitButton().addKeyListener(listenerKey);
	}
	
	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mainFrame.getPlayerPanel().getAddPlayerButton()) {
//				// get player name
//				String name = JOptionPane.showInputDialog("Enter name");
//				String points = JOptionPane.showInputDialog("Enter starting points");
//
//				// change model
//				player = new SimplePlayer("1", name, Integer.parseInt(points));
//				gameEngine.addPlayer(player);
//				
//				// change view
//				mainFrame.getToolBar().focusActiveBetText();
//				mainFrame.getPlayerPanel().setPlayerName(name);
//				mainFrame.getPlayerPanel().showPoints();
//				mainFrame.getPlayerPanel().setPoints(points);
//				mainFrame.getPlayerPanel().disableAddPlayerButton();
//				mainFrame.getToolBar().enableBet();
//				System.out.printf("%s%d%s\n", "player has ", player.getPoints(), " points");
			} else if (e.getSource() == mainFrame.getToolBar().getPlaceBetButton()) {
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
					mainFrame.getToolBar().enableRoll();
					mainFrame.getToolBar().focusPlayerRoll();
				} else {
					// change view
					System.out.printf("%s%d\n", "not enough points for bet of: ", currPlayer.getBet());
					mainFrame.getPlayerPanel().notEnoughPoints();
				}
			} else if (e.getSource() == mainFrame.getToolBar().getBetTextField()) {
				// press place bet button
				mainFrame.getToolBar().clickPlaceBet();
				
			} else if (e.getSource() == mainFrame.getToolBar().getPlayerRollsButton()) {
				// change model
				gameEngine.rollPlayer(player, 1, 200, 20);
				
				// change view
				mainFrame.getToolBar().disableRoll();
			} else if (e.getSource() == mainFrame.getToolBar().getHouseRollsButton()) {
				// change model
				gameEngine.rollHouse(1, 200, 20);
				
				// change view
				mainFrame.getToolBar().disableHouseRoll();
			} else if (e.getSource() == mainFrame.getToolBar().getDisplayResultsButton()) {
				// change model
				gameEngine.calculateResult();
				
				// clear bet
				player.placeBet(0);
				
				// change view
				mainFrame.getPlayerPanel().setPoints(Integer.toString(player.getPoints()));
				mainFrame.getPlayerPanel().setBetPoints(player.getBet());
				mainFrame.getToolBar().enableBet();
				mainFrame.getToolBar().disableDisplayResults();
				mainFrame.getToolBar().focusActiveBetText();
			} else if (e.getSource() == mainFrame.getToolBar().getQuitButton()) {
				JFrame quit = new JFrame();
				int n = JOptionPane.showConfirmDialog(quit, "Seriously?", "Quit game confirmation", JOptionPane.YES_NO_OPTION);
				if (n == 0)
					System.exit(0);
			}
		}
	}
	
	public class ListenerKey implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == mainFrame.getPlayerPanel().getAddPlayerButton()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mainFrame.getPlayerPanel().clickAddPlayer();
				}
			} else if (e.getSource() == mainFrame.getToolBar().getPlayerRollsButton()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mainFrame.getToolBar().clickPlayerRolls();
				}
			} else if (e.getSource() == mainFrame.getToolBar().getHouseRollsButton()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mainFrame.getToolBar().clickHouseRolls();
				}
			} else if (e.getSource() == mainFrame.getToolBar().getDisplayResultsButton()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mainFrame.getToolBar().clickDisplayResults();
				}
			} else if (e.getSource() == mainFrame.getToolBar().getQuitButton()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mainFrame.getToolBar().clickQuit();;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}
}
