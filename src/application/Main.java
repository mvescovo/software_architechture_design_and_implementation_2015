package application;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;
import view.CrapsMainFrame;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

public class Main {
	public static void main(String[] args) {
		GameEngine gameEngine;
		CrapsMainFrame crapsFrame;
		Controller controller;
		
		gameEngine = new GameEngineImpl();
		crapsFrame = new CrapsMainFrame();
		controller = new Controller(gameEngine, crapsFrame);
	}
}
