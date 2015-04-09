package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MainFrame;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class AddPlayerButtonListener implements ActionListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
	Player player;

	public AddPlayerButtonListener(GameEngine gameEngine, MainFrame mainFrame, Player player) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.player = player;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// get player name
		String name = JOptionPane.showInputDialog("Enter name");
		String points = JOptionPane.showInputDialog("Enter starting points");

		// change model
		player = new SimplePlayer("1", name, Integer.parseInt(points));
		gameEngine.addPlayer(player);
		
		// change view
		mainFrame.getToolBar().focusActiveBetText();
		mainFrame.getPlayerPanel().setPlayerName(name);
		mainFrame.getPlayerPanel().showPoints();
		mainFrame.getPlayerPanel().setPoints(points);
		mainFrame.getPlayerPanel().disableAddPlayerButton();
		mainFrame.getToolBar().enableBet();
		System.out.printf("%s%d%s\n", "player has ", player.getPoints(), " points");
	}

}
