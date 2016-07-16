package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class PlayerNameTextFieldListener implements ActionListener {
	private MainFrame mainFrame;
	
	public PlayerNameTextFieldListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.getStartGamePanel().getPlayerPointsTextField().requestFocusInWindow();
	}
}
