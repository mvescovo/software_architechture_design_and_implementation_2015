package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import view.MainFrame;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class AddPlayerButtonListener implements ActionListener, KeyListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
	Player player;
	Controller controller;

	public AddPlayerButtonListener(GameEngine gameEngine, MainFrame mainFrame, Controller controller) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// get player name
		String name = JOptionPane.showInputDialog("Enter name");
		String points = JOptionPane.showInputDialog("Enter starting points");

		// change model
		player = new SimplePlayer("1", name, Integer.parseInt(points));
		gameEngine.addPlayer(player);
		
		// update master controller
		controller.setCurrPlayer(player);
		
		// change view
		mainFrame.getToolBar().focusActiveBetText();
		mainFrame.getPlayerPanel().setPlayerName(name);
		mainFrame.getPlayerPanel().showPoints();
		mainFrame.getPlayerPanel().setPoints(points);
		mainFrame.getPlayerPanel().disableAddPlayerButton();
		mainFrame.getMenu().disableAddPlayerMenu();
		mainFrame.getToolBar().enableBet();
		mainFrame.getMenu().enablePlaceBetMenu();
		mainFrame.getToolBar().enableQuit();
		mainFrame.getMenu().enableQuitMenu();
		System.out.printf("%s%d%s\n", "player has ", player.getPoints(), " points");
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getPlayerPanel().clickAddPlayer();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
