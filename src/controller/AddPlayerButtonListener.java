package controller;

import java.awt.BorderLayout;
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
		String name = null;
		String points = null;
		int pointsInt = 0;
		boolean pointsOk = true;
		
		name = JOptionPane.showInputDialog("Enter name");
		
		if (name != null) {
			points = JOptionPane.showInputDialog("Enter starting points");
			
			if (points != null) {
				try {
					pointsInt = Integer.parseInt(points);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(mainFrame, "Points needs to be a number.", "Invalid points", 0);
					System.out.println("not a number");
					pointsOk = false;
				} finally {
					if ((pointsOk == true) && (pointsInt < 1)) {
						JOptionPane.showMessageDialog(mainFrame, "Points must be greater than 0.", "Invalid points", 0);
						System.out.println("points must be greater than 0");
						pointsOk = false;
					}
				}
			} else {
				pointsOk = false;
			}
		}

		if ((name != null) && (pointsOk == true)) {
			// change model
			player = new SimplePlayer("1", name, pointsInt);
			gameEngine.addPlayer(player);
			
			// update master controller
			controller.setCurrPlayer(player);
			
			// change view
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().setVisible(true);
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
			mainFrame.getMenu().disableAddPlayerMenu();
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableBet();
			mainFrame.getMenu().enablePlaceBetMenu();
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableQuit();
			mainFrame.getMenu().enableQuitMenu();
			mainFrame.getPlayerPanel().enableAddPoints();
			System.out.printf("%s%d%s\n", "player has ", player.getPoints(), " points");
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// nothing to do here
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getPlayerPanel().clickAddPlayer();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// nothing to do here
	}

}
