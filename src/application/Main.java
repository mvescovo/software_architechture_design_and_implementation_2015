package application;


import controller.Controller;
import view.MainFrame;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

public class Main {
	public static void main(String[] args) {
		GameEngine gameEngine;
		MainFrame mainFrame;
		@SuppressWarnings("unused")
		Controller controller;
		
		gameEngine = new GameEngineImpl();
		mainFrame = new MainFrame(gameEngine);
		controller = new Controller(gameEngine, mainFrame);
	}
}
