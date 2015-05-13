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
	private int houseTotal;
	public Player currPlayer;
	
	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay,
			int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		
		// roll the dice and update views
		try {
			while (initialDelay < finalDelay) {
				num1 = random.nextInt(NUM_FACES) + minNum;
				num2 = random.nextInt(NUM_FACES) + minNum;
				dicePair = new DicePairImpl(num1, num2, NUM_FACES);
				System.out.println("rolled. sending to callback...");
				this.gameEngineCallback.intermediateResult(player, dicePair, this);
				initialDelay += delayIncrement;
				Thread.sleep(initialDelay);
			}
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
		num1 = random.nextInt(NUM_FACES) + minNum;
		num2 = random.nextInt(NUM_FACES) + minNum;
		dicePair = new DicePairImpl(num1, num2, NUM_FACES);
		player.setRollResult(dicePair);
		this.gameEngineCallback.result(player, dicePair, this);
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		
		// roll the dice and update views
		try {
			while (initialDelay < finalDelay) {
				num1 = random.nextInt(NUM_FACES) + minNum;
				num2 = random.nextInt(NUM_FACES) + minNum;
				dicePair = new DicePairImpl(num1, num2, NUM_FACES);
				this.gameEngineCallback.intermediateHouseResult(dicePair, this);
				initialDelay += delayIncrement;
				Thread.sleep(initialDelay);
			}		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		num1 = random.nextInt(NUM_FACES) + minNum;
		num2 = random.nextInt(NUM_FACES) + minNum;
		dicePair = new DicePairImpl(num1, num2, NUM_FACES);
		houseTotal = num1 + num2;
		this.gameEngineCallback.houseResult(dicePair, this);	
	}

	@Override
	public void addPlayer(Player player) {
		this.players.add(player);
		System.out.println("added player" + player.getPlayerName());
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
		this.rollHouse(1, 200, 20);
		
		for (Player player: players) {
			int num1 = player.getRollResult().getDice1();
			int num2 = player.getRollResult().getDice2();
			int total = num1 + num2;
			
			if (total > houseTotal) {
				// player won
				System.out.printf("%s%s\n", player.getPlayerName(), " won");
				player.setPoints(player.getPoints() + player.getBet() * 2);
			} else if (total == houseTotal){
				// draw - return points to player
				System.out.println("draw");
				player.setPoints(player.getPoints() + player.getBet());
			} else {
				// player lost.
				System.out.printf("%s%s\n", player.getPlayerName(), " lost");
			}
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}
	
	public GameEngineCallback getGameEngineCallback() {
		return gameEngineCallback;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return this.players;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		if (player.placeBet(bet)) {
			System.out.println("placed bet on server");
			return true;
		} else {
			System.out.println("failed to place bet on server");
			return false;
		}
	}
}
