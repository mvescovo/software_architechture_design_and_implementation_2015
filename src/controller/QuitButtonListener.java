package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.MainFrame;

public class QuitButtonListener implements ActionListener, KeyListener {
	GameEngine gameEngine;
	MainFrame mainFrame;
	
	public QuitButtonListener (GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame quit = new JFrame();
		int n = JOptionPane.showConfirmDialog(quit, "Seriously?", "Quit game confirmation", JOptionPane.YES_NO_OPTION);
		if (n == 0) {
			// change model
			gameEngine.removePlayer(null);
			
			// update master controller
//			controller.setCurrPlayer(null);
			
			// change view
			mainFrame.getPlayerPanel().setVisible(false);
			mainFrame.getTableAndToolbarContainerPanel().setVisible(false);
			mainFrame.getStartGamePanel().setVisible(true);
			// reset the centre panel of the mainFrame borderlayout as there can only be one
			mainFrame.add(mainFrame.getStartGamePanel(), BorderLayout.CENTER);
			
			mainFrame.getPlayerPanel().setPlayerName("No player");
//			mainFrame.getPlayerPanel().hidePoints();
//			mainFrame.getPlayerPanel().hideBet();
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().setBetTextField("");
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().disableBet();
			mainFrame.getMenu().getplaceBetMenuItem().setEnabled(false);
//			mainFrame.getTableAndToolbarContainerPanel().getToolBar().disableQuit();
			mainFrame.getMenu().getQuitMenuItem().setEnabled(false);
//			mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText("1");
//			mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText("1");
//			mainFrame.getPlayerPanel().enableAddPlayerButton();
			mainFrame.getMenu().getStartGameMenuItem().setEnabled(true);
//			mainFrame.getPlayerPanel().focusAddPlayerButton();
			mainFrame.getMenu().getRollPlayerMenuItem().setEnabled(false);
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollPlayerButton().setEnabled(false);
			mainFrame.getPlayerPanel().disableAddPoints();

			mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place a bet to play");
//			mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText("Player result: N/A");
//			mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: N/A");
//			mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText("Winner: N/A");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing to do here
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().clickQuit();;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing to do here
	}
}
