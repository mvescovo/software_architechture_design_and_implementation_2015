package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class PlayerPointsTextFieldListener implements ActionListener {
	private MainFrame mainFrame;
	
	public PlayerPointsTextFieldListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.getStartGamePanel().getStartGameButton().doClick();
	}
}
