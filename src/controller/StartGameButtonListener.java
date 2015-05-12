package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class StartGameButtonListener implements ActionListener, KeyListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
	Player player;
	Controller controller;
	
	public StartGameButtonListener(GameEngine gameEngine, MainFrame mainFrame, Controller controller) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = null;
		String points = null;
		int pointsInt = 0;
		boolean pointsOk = true;
		
		// check name and points are entered correctly before making changes
		name = mainFrame.getStartGamePanel().getPlayerNameTextField().getText();			
		
		if (!name.isEmpty()) {
			points = mainFrame.getStartGamePanel().getPlayerPointsTextField().getText();
			
			if (!points.isEmpty()) {
				try {
					pointsInt = Integer.parseInt(points);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(mainFrame, "Points needs to be a number.", "Invalid points", 0);
					System.out.println("not a number");
					mainFrame.getStartGamePanel().getPlayerPointsTextField().requestFocusInWindow();
					mainFrame.getStartGamePanel().getPlayerPointsTextField().setText(null);
					pointsOk = false;
				}
				
				if ((pointsOk == true) && (pointsInt < 1)) {
					JOptionPane.showMessageDialog(mainFrame, "Points must be greater than 0.", "Invalid points", 0);
					System.out.println("points must be greater than 0");
					mainFrame.getStartGamePanel().getPlayerPointsTextField().requestFocusInWindow();
					mainFrame.getStartGamePanel().getPlayerPointsTextField().setText(null);
					pointsOk = false;
				}
			} else {
				pointsOk = false;
				JOptionPane.showMessageDialog(mainFrame, "Points empty.", "Invalid points", 0);
				System.out.println("Points empty");
				mainFrame.getStartGamePanel().getPlayerPointsTextField().requestFocusInWindow();
			}
			
			if ((!name.isEmpty()) && (pointsOk == true)) {
				// change model
				player = new SimplePlayer("1", name, pointsInt);
				gameEngine.addPlayer(player);
				
				// update master controller
//				controller.setCurrPlayer(player);
				
				// change view
//				System.out.println("clicked start game");
				mainFrame.getTableAndToolbarContainerPanel().setVisible(true);
				mainFrame.getStartGamePanel().setVisible(false);
				mainFrame.getPlayerPanel().setVisible(true);
				mainFrame.getTableAndToolbarContainerPanel().getToolBar().setVisible(true);
				// reset the centre panel of the mainFrame borderlayout as there can only be one
				mainFrame.add(mainFrame.getTableAndToolbarContainerPanel(), BorderLayout.CENTER);
				mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusActiveBetText();
				mainFrame.getPlayerPanel().setPlayerName(name);
				mainFrame.getPlayerPanel().showPoints();
				mainFrame.getPlayerPanel().showBet();
				mainFrame.getPlayerPanel().setPoints(points);
				mainFrame.getPlayerPanel().disableAddPlayerButton();
				mainFrame.getMenu().getStartGameMenuItem().setEnabled(false);
				mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableBet();
				mainFrame.getMenu().getplaceBetMenuItem().setEnabled(true);
				mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableQuit();
				mainFrame.getMenu().getQuitMenuItem().setEnabled(true);
				mainFrame.getPlayerPanel().enableAddPoints();
//				System.out.printf("%s%d%s\n", "player has ", player.getPoints(), " points");
			}
		} else {
			JOptionPane.showMessageDialog(mainFrame, "Name empty", "Invalid name", 0);
			System.out.println("Name empty");
			mainFrame.getStartGamePanel().getPlayerNameTextField().requestFocusInWindow();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing to do here
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getStartGamePanel().getStartGameButton().doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing to do here
	}
}
