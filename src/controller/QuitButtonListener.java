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
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	
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
			if (gameEngine.removePlayer(null)) {
//				System.out.println("GUI was told player removed from server");
				// change view
				mainFrame.getPlayerPanel().setVisible(false);
				mainFrame.getTableAndToolbarContainerPanel().setVisible(false);
				mainFrame.getStartGamePanel().setVisible(true);
				mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setVisible(false);
				mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setVisible(false);
				mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(false);
				mainFrame.getMenu().getplaceBetMenuItem().setEnabled(false);
				mainFrame.getMenu().getRollPlayerMenuItem().setEnabled(false);
				mainFrame.getMenu().getQuitMenuItem().setEnabled(false);
				mainFrame.getTableAndToolbarContainerPanel().getToolBar().disableBet();
				mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollPlayerButton().setEnabled(false);
				mainFrame.getPlayerPanel().disableAddPoints();
				mainFrame.getMenu().getStartGameMenuItem().setEnabled(true);
				// reset the centre panel of the mainFrame borderlayout as there can only be one
				mainFrame.add(mainFrame.getStartGamePanel(), BorderLayout.CENTER);
				// reset values
				mainFrame.getTableAndToolbarContainerPanel().getToolBar().setBetTextField(null);
				mainFrame.getPlayerPanel().getAddPointsTextField().setText(null);
				mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText("1");
				mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText("1");
				mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place a bet to play");
				mainFrame.getStartGamePanel().getPlayerNameTextField().setText(null);
				mainFrame.getStartGamePanel().getPlayerPointsTextField().setText(null);
			} else {
//				System.out.println("GUI was told player not removed from server");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing to do here
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getTableAndToolbarContainerPanel().getToolBar().clickQuit();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing to do here
	}
}
