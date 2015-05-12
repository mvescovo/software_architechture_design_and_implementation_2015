/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.MainFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class RollHouseButtonListener implements ActionListener, KeyListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
//	Controller controller;
	Player player;
	
	RollHouseButtonListener(GameEngine gameEngine, MainFrame mainFrame, Controller controller) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
//		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// change model for player roll
//		player = controller.getCurrPlayer();
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// roll house and calculate results
				gameEngine.calculateResult();
				
				// clear bet
//				controller.getCurrPlayer().placeBet(0);
				

			}
		});
		
		thread.start();
		
		// change view immediately
		mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().setEnabled(false);
		mainFrame.getMenu().getRollHouseMenuItem().setEnabled(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing to do here
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing to do here
		
	}

}
