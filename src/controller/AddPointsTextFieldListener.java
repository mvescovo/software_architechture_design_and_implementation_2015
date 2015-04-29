package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainFrame;

public class AddPointsTextFieldListener implements ActionListener {
	MainFrame mainFrame;
	
	public AddPointsTextFieldListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// press add points button
		mainFrame.getPlayerPanel().clickAddPoints();
	}
}
