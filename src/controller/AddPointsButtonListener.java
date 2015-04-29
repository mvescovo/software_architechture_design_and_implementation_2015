package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import view.MainFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class AddPointsButtonListener implements ActionListener, KeyListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
	
	public AddPointsButtonListener(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Player currPlayer = null;
		String playerString = null;
		String addPoints = null;
		int addPointsInt = 0;
		boolean addPointsOk = true;
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
		
		// get add points amount from user
		addPoints = mainFrame.getPlayerPanel().getAddPointsTextField().getText();
		
		if (!addPoints.isEmpty()) {
			try {
				addPointsInt = Integer.parseInt(addPoints);
			} catch (NumberFormatException e2){
				JOptionPane.showMessageDialog(mainFrame, "Add points needs to be a number.", "Invalid add points", 0);
				System.out.println("bet not a number");
				mainFrame.getPlayerPanel().getAddPointsTextField().setText(null);
				mainFrame.getPlayerPanel().getAddPointsTextField().requestFocusInWindow();
				addPointsOk = false;
			}
		} else {
			JOptionPane.showMessageDialog(mainFrame, "Add points empty", "Invalid add points", 0);
			System.out.println("Add points empty");
			mainFrame.getPlayerPanel().getAddPointsTextField().setText(null);
			mainFrame.getPlayerPanel().getAddPointsTextField().requestFocusInWindow();
			addPointsOk = false;
		}
		
		if ((addPointsOk == true) && addPointsInt <= 0) {
			JOptionPane.showMessageDialog(mainFrame, "Add points needs to be greater than 0.", "Invalid add points", 0);
			System.out.println("Add points needs to be greater than 0.");
			mainFrame.getPlayerPanel().getAddPointsTextField().setText(null);
			mainFrame.getPlayerPanel().getAddPointsTextField().requestFocusInWindow();
			addPointsOk = false;
		}
		
		if (addPointsOk == true) {
			// change model
			currPlayer.setPoints(currPlayer.getPoints() + addPointsInt);
			
			// change view
			System.out.printf("%s%d%s%s\n", "added ", addPointsInt, " points to ", playerString);
			mainFrame.getPlayerPanel().addPoints(addPointsInt);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getPlayerPanel().clickAddPoints();
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
