package application;


import javax.swing.SwingUtilities;

import controller.Controller;
import view.MainFrame;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

//Assignment 2 version
public class Main {
	public static void main(String[] args) {
		final GameEngine gameEngine;
		
		
		@SuppressWarnings("unused")
		Controller controller;
		
		// MVC model. Controller has references to view and model.
		// View has reference to model, and model has a callback to update the view.
		gameEngine = new GameEngineImpl();
		// create GUI from event dispatch thread
		SwingUtilities.invokeLater(new Runnable() {
			final MainFrame mainFrame;
			public void run() {

				mainFrame = new MainFrame(gameEngine);
			}
		});
		
		controller = new Controller(gameEngine, mainFrame);
	}
}
