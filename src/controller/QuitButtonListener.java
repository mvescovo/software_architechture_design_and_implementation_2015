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
	Controller controller;
	
	public QuitButtonListener (GameEngine gameEngine, MainFrame mainFrame, Controller controller) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame quit = new JFrame();
		int n = JOptionPane.showConfirmDialog(quit, "Seriously?", "Quit game confirmation", JOptionPane.YES_NO_OPTION);
		if (n == 0) {
			// change model
			gameEngine.removePlayer(controller.getCurrPlayer());
			
			// update master controller
			controller.setCurrPlayer(null);
			
			// change view
			mainFrame.getPlayerPanel().setPlayerName("No player");
			mainFrame.getPlayerPanel().hidePoints();
			mainFrame.getPlayerPanel().hideBet();
			mainFrame.getgameTablePanel().getToolBar().setBetTextField("");
			mainFrame.getgameTablePanel().getToolBar().disableBet();
			mainFrame.getMenu().disablePlaceBetMenu();
			mainFrame.getgameTablePanel().getToolBar().disableQuit();
			mainFrame.getMenu().disableQuitMenu();
			mainFrame.getgameTablePanel().getDicePanel().setDice1("1");
			mainFrame.getgameTablePanel().getDicePanel().setDice2("1");
			mainFrame.getPlayerPanel().enableAddPlayerButton();
			mainFrame.getMenu().enableAddPlayerMenu();
			mainFrame.getPlayerPanel().focusAddPlayerButton();
			mainFrame.getMenu().disablePlayerRollsMenu();
			mainFrame.getgameTablePanel().getToolBar().disableRoll();
			mainFrame.getMenu().disablePlayerRollsMenu();
			mainFrame.getgameTablePanel().getToolBar().disableRoll();
			mainFrame.getPlayerPanel().disableAddPoints();
			mainFrame.getgameTablePanel().setVisible(false);
			mainFrame.getStartGamePanel().setVisible(true);
			mainFrame.getPlayerPanel().setVisible(false);
			mainFrame.getgameTablePanel().getToolBar().setVisible(false);
			mainFrame.add(mainFrame.getStartGamePanel(), BorderLayout.CENTER);
			mainFrame.getgameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place a bet to play");
			mainFrame.getgameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText("Player result: N/A");
			mainFrame.getgameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: N/A");
			mainFrame.getgameTablePanel().getGameStatusPanel().getGameResultLabel().setText("Winner: N/A");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getgameTablePanel().getToolBar().clickQuit();;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
