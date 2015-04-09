package application;


import controller.Controller;
import view.MainFrame;
import model.GameEngineImpl;

public class Main {
	public static void main(String[] args) {
		GameEngineImpl gameEngine;
		MainFrame mainFrame;
		@SuppressWarnings("unused")
		Controller controller;
		
		gameEngine = new GameEngineImpl();
		mainFrame = new MainFrame(gameEngine);
		controller = new Controller(gameEngine, mainFrame);
	}
}
