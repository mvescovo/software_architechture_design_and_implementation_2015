package model;

import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.Random;

public class GameEngineImpl implements GameEngine {
	Collection<Player> players = new ArrayList<Player>();
	GameEngineCallback gameEngineCallback;
	
	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay,
			int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		
		while (initialDelay < finalDelay) {
			num1 = random.nextInt(NUM_FACES) + minNum;
			num2 = random.nextInt(NUM_FACES) + minNum;
			dicePair = new DicePairImpl(num1, num2, NUM_FACES);
			this.gameEngineCallback.intermediateResult(player, dicePair, this);
			initialDelay += delayIncrement;
		}
		
		num1 = random.nextInt(NUM_FACES) + minNum;
		num2 = random.nextInt(NUM_FACES) + minNum;
		dicePair = new DicePairImpl(num1, num2, NUM_FACES);
		this.gameEngineCallback.result(player, dicePair, this);
		player.setRollResult(dicePair);
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		
		while (initialDelay < finalDelay) {
			num1 = random.nextInt(NUM_FACES) + minNum;
			num2 = random.nextInt(NUM_FACES) + minNum;
			dicePair = new DicePairImpl(num1, num2, NUM_FACES);
			this.gameEngineCallback.intermediateHouseResult(dicePair, this);
			initialDelay += delayIncrement;
		}
		
		num1 = random.nextInt(NUM_FACES) + minNum;
		num2 = random.nextInt(NUM_FACES) + minNum;
		dicePair = new DicePairImpl(num1, num2, NUM_FACES);
		this.gameEngineCallback.houseResult(dicePair, this);
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
		Player winner = null;
		int highestRoll = 0;
		this.rollHouse(1, 100, 20);
		
		for (Player player: players) {
			int num1 = player.getRollResult().getDice1();
			int num2 = player.getRollResult().getDice2();
			int total = num1 + num2;
			
			if (total > highestRoll) {
				highestRoll = total;
				winner = player;
			}
		}
		
		winner.setPoints(winner.getPoints() + winner.getBet());
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
		// TODO Auto-generated method stub
		return false;
	}

}
