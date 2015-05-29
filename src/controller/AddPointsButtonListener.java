package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import utility.InputValidation;
import view.MainFrame;
import model.GameEngineClientStub;
import model.interfaces.GameEngine;

public class AddPointsButtonListener implements ActionListener, KeyListener {
	private GameEngineClientStub gameEngine;
	private MainFrame mainFrame;
	private InputValidation validate;
	
	public AddPointsButtonListener(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = (GameEngineClientStub)gameEngine;
		this.mainFrame = mainFrame;
		validate = new InputValidation();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean pointsValid = false;
		int pointsToAdd = 0;
		int oldPoints;
		
		// disable textfield until operation is complete
		mainFrame.getPlayerPanel().getAddPointsTextField().setEnabled(false);
		
		pointsValid = validate.checkJTextFieldPositiveInt(mainFrame.getPlayerPanel().getAddPointsTextField(), mainFrame);
		oldPoints = Integer.parseInt(mainFrame.getPlayerPanel().getPoints());
		
		if (pointsValid) {
			// change model
			pointsToAdd = Integer.parseInt(mainFrame.getPlayerPanel().getAddPointsTextField().getText());
			gameEngine.addPoints(pointsToAdd);
			mainFrame.getPlayerPanel().setPoints(Integer.toString(oldPoints + pointsToAdd));
		}
		
		// enable text field again
		mainFrame.getPlayerPanel().getAddPointsTextField().setEnabled(true);
		mainFrame.getPlayerPanel().getAddPointsTextField().requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing to do here
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getPlayerPanel().clickAddPoints();
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing to do here
	}
}
