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
		// change model
		player = controller.getCurrPlayer();
		gameEngine.rollPlayer(player, 1, 200, 20);
		
		// change view
		mainFrame.getToolBar().disableRoll();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getToolBar().clickPlayerRolls();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
