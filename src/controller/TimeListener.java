package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class TimeListener implements ActionListener {
	GameEngine gameEngine;
	Timer timer;
	Timer houseTimer;
	Player player = null;
	int initialDelay;
	int finalDelay;
	int delayIncrement;
	
	public TimeListener(Timer timer, GameEngine gameEngine, Player player, int initialDelay, int finalDelay, int delayIncrement) {
		this.timer = timer;
		this.gameEngine = gameEngine;
		this.player = player;
		this.initialDelay = initialDelay;
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
	}
	
	public TimeListener(Timer houseTimer, GameEngine gameEngine, int initialDelay, int finalDelay, int delayIncrement) {
//		System.out.println("created house timerlistener");
		this.houseTimer = houseTimer;
		this.gameEngine = gameEngine;
		this.initialDelay = initialDelay;
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		initialDelay += delayIncrement;
		if (e.getSource() == timer) {
//			System.out.println("player rolled Test");
			gameEngine.rollPlayer(player, initialDelay, finalDelay, delayIncrement);
		} else if (e.getSource() == houseTimer) {
//			System.out.println("house rolled Test");
			gameEngine.rollHouse(initialDelay, finalDelay, delayIncrement);
		} else {
			System.out.println("no idea who called me");
		}
	}
}