/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class RefreshButtonListener implements ActionListener {
	private GameEngine gameEngine = null;
	private MainFrame mainFrame = null;
	
	public RefreshButtonListener(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("clicked refresh button. doesn't seem to be needed with this implementation but it looks nice.");
	}
}
