package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class BetTextFieldListener implements ActionListener {
	MainFrame mainFrame;
	
	public BetTextFieldListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// press place bet button
		mainFrame.getTableAndToolbarContainerPanel().getToolBar().clickPlaceBet();
	}
}
