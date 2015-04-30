package application;


import controller.Controller;
import view.MainFrame;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

//Assignment 2 version
public class Main {
	public static void main(String[] args) {
		GameEngine gameEngine;
		MainFrame mainFrame;
		@SuppressWarnings("unused")
		Controller controller;
		
		// MVC model. Controller has references to view and model.
		// View has reference to model, and model has a callback to update the view.
		gameEngine = new GameEngineImpl();
		mainFrame = new MainFrame(gameEngine);
		controller = new Controller(gameEngine, mainFrame);
	}
}
