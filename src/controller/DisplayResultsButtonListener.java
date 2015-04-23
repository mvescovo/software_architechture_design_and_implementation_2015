package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.interfaces.GameEngine;
import view.MainFrame;

public class DisplayResultsButtonListener implements ActionListener, KeyListener {
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private Controller controller;
	
	public DisplayResultsButtonListener (GameEngine gameEngine, MainFrame mainFrame, Controller controller) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// change model
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				gameEngine.calculateResult();
				
				// clear bet
				controller.getCurrPlayer().placeBet(0);
				
    			// change view after house has rolled, results calculated and be cleared
				mainFrame.getPlayerPanel().setPoints(Integer.toString(controller.getCurrPlayer().getPoints()));
				mainFrame.getPlayerPanel().setBetPoints(controller.getCurrPlayer().getBet());
				mainFrame.getToolBar().enableBet();
				mainFrame.getMenu().enablePlaceBetMenu();
				mainFrame.getToolBar().focusActiveBetText();
			}
		});
		
		thread.start();
		
		// change view immediately
		mainFrame.getToolBar().disableDisplayResults();
		mainFrame.getMenu().disableDisplayResultsMenu();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getToolBar().clickDisplayResults();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
