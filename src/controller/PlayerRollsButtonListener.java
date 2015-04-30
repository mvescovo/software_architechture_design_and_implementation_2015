package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.MainFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlayerRollsButtonListener implements ActionListener, KeyListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
	Player player;
	Controller controller;
	
	public PlayerRollsButtonListener(GameEngine gameEngine, MainFrame mainFrame, Controller controller) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// change model for player roll
		player = controller.getCurrPlayer();
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// player roll
				gameEngine.rollPlayer(player, 1, 200, 20);
				
				 // sleep for a bit so player can see their result
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// house roll and calculate results
				gameEngine.calculateResult();
				
				// clear bet
				controller.getCurrPlayer().placeBet(0);
				
				// change view after house has rolled, results calculated, and bet cleared
				mainFrame.getPlayerPanel().setPoints(Integer.toString(controller.getCurrPlayer().getPoints()));
				mainFrame.getPlayerPanel().setBetPoints(controller.getCurrPlayer().getBet());
				mainFrame.getgameTablePanel().getToolBar().enableBet();
				mainFrame.getMenu().enablePlaceBetMenu();
				mainFrame.getgameTablePanel().getToolBar().focusActiveBetText();
			}
		});
		
		thread.start();
		
		// change view
		mainFrame.getgameTablePanel().getToolBar().disableRoll();
		mainFrame.getMenu().disablePlayerRollsMenu();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getgameTablePanel().getToolBar().clickPlayerRolls();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
