package application;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;
import view.MainFrame;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

public class Main {
	public static void main(String[] args) {
		GameEngine gameEngine;
		MainFrame crapsFrame;
		Controller controller;
		
		gameEngine = new GameEngineImpl();
		crapsFrame = new MainFrame();
		controller = new Controller(gameEngine, crapsFrame);
	}
}
