package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.Random;

import javax.swing.Timer;

import controller.TimeListener;

public class GameEngineImpl implements GameEngine {
	Collection<Player> players = new ArrayList<Player>();
	GameEngineCallback gameEngineCallback;
	private int houseTotal;
	private Timer timer;
	private Timer houseTimer;
	private TimeListener timeListener;
	private TimeListener houseTimeListener;
	public Player currPlayer;
	
	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay,
			int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		timer = new Timer(initialDelay, null);
		timer.setRepeats(false);
		timeListener = new TimeListener(timer, this, player, initialDelay, finalDelay, delayIncrement);
		timer.addActionListener(timeListener);
		
		// roll the dice and update views
		if (initialDelay < finalDelay) {
			num1 = random.nextInt(NUM_FACES) + minNum;
			num2 = random.nextInt(NUM_FACES) + minNum;
			dicePair = new DicePairImpl(num1, num2, NUM_FACES);
			this.gameEngineCallback.intermediateResult(player, dicePair, this);
			timer.start();
		} else {
			num1 = random.nextInt(NUM_FACES) + minNum;
			num2 = random.nextInt(NUM_FACES) + minNum;
			dicePair = new DicePairImpl(num1, num2, NUM_FACES);
			player.setRollResult(dicePair);
			this.gameEngineCallback.result(player, dicePair, this);
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		houseTimer = new Timer(initialDelay, null);
		houseTimer.setRepeats(false);
		houseTimeListener = new TimeListener(houseTimer, this, initialDelay, finalDelay, delayIncrement);
		houseTimer.addActionListener(houseTimeListener);

		
		// roll the dice and update views
		if (initialDelay < finalDelay) {
			num1 = random.nextInt(NUM_FACES) + minNum;
			num2 = random.nextInt(NUM_FACES) + minNum;
			dicePair = new DicePairImpl(num1, num2, NUM_FACES);
			this.gameEngineCallback.intermediateHouseResult(dicePair, this);
			houseTimer.start();
		} else {
			num1 = random.nextInt(NUM_FACES) + minNum;
			num2 = random.nextInt(NUM_FACES) + minNum;
			dicePair = new DicePairImpl(num1, num2, NUM_FACES);
			houseTotal = num1 + num2;
			this.gameEngineCallback.houseResult(dicePair, this);
		}
	}

	@Override
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	@Override
	public boolean removePlayer(Player player) {
		if (this.players.contains(player)) {
			this.players.remove(player);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void calculateResult() {
		//this.rollHouse(1, 200, 20);
		
		for (Player player: players) {
			int num1 = player.getRollResult().getDice1();
			int num2 = player.getRollResult().getDice2();
			int total = num1 + num2;
			
			if (total > houseTotal) {
				// player won
				player.setPoints(player.getPoints() + player.getBet() * 2);
			} else if (total == houseTotal){
				// draw - return points to player
				player.setPoints(player.getPoints() + player.getBet());
			} else {
				// player lost.
			}
			
			System.out.printf("%s%s%s%s%s%d\n", "Player: id=", player.getPlayerId(),
					  ", name=", player.getPlayerName(), ", points=",
					  player.getPoints());
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return this.players;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		try {
			player.placeBet(bet);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage() + player.getPlayerName());
			return false;
		}
		
		return true;
	}
	
	public void stopTimer() {
		timer.stop();
	}
}
