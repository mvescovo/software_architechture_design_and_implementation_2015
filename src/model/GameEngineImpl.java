package model;

import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.Random;

public class GameEngineImpl implements GameEngine {
	private volatile Collection<Player> players = new ArrayList<Player>();
	private GameEngineCallback gameEngineCallback;
	private int houseTotal;
	private volatile boolean houseRolling = false;
	private Object lock = new Object();
	private int nextAvailableId = 0;
	
	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay,
			int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		
		((SimplePlayer)player).setRolling();
		((SimplePlayer)player).setParticipatingInRound(true);
		
		if (((SimplePlayer)player).getIsParticipatingInRound()){
			System.out.println(player.getPlayerName() + " is participating this round (before player roll)");
		} else {
			System.out.println(player.getPlayerName() + " not participating this round (before player roll)");
		}
		
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
		
		((SimplePlayer)player).setNotRolling();
		
		if (((SimplePlayer)player).getIsParticipatingInRound()){
			System.out.println(player.getPlayerName() + " is participating this round (after player roll)");
		} else {
			System.out.println(player.getPlayerName() + " not participating this round (after player roll)");
		}
		
		synchronized(this) {
			this.notify();
		}
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
		System.out.println("added player: " + player.getPlayerName());
	}

	@Override
	public boolean removePlayer(Player player) {
		if (this.players.contains(player)) {
			setNextAvailableId(player.getPlayerId());
			this.players.remove(player);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public synchronized void calculateResult() {		
		// disable clients while house is rolling
		for (Player player: players) {
			((ServerSideGameEngineCallback)gameEngineCallback).disableClientForHouseRoll(player);
		}
		
		// ensure all players have finished rolling
		for (Player player: players) {
			while (((SimplePlayer)player).getIsRolling()) {
				try {
					System.out.println(player.getPlayerName() + " is still rolling");
					//TODO send a callback to all players to let them know the server is waiting for players to finish in progress rolling
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (((SimplePlayer)player).getIsParticipatingInRound()){
				System.out.println(player.getPlayerName() + " is participating this round (before house roll)");
			} else {
				System.out.println(player.getPlayerName() + " not participating this round (before house roll)");
			}
		}
		
		this.rollHouse(1, 500, 20);
		
		// calculate results and send to players
		for (Player player: players) {
			if (((SimplePlayer)player).getIsParticipatingInRound()) {
				System.out.println("going to calculate results");
				System.out.println(player.getPlayerName() + " participating: " + ((SimplePlayer)player).getIsParticipatingInRound());
				int num1 = player.getRollResult().getDice1();
				int num2 = player.getRollResult().getDice2();
				int total = num1 + num2;
							
				System.out.println("houseTotal: " + houseTotal);
				System.out.println("playerTotal: " + total);
				System.out.println("old player points: " + player.getPoints());
				
				if (total > houseTotal) {
					// player won
					System.out.printf("%s%s\n", player.getPlayerName(), " won");
					player.setPoints(player.getPoints() + (player.getBet() * 2));
					System.out.println("new player points: " + player.getPoints());
				} else if (total == houseTotal){
					// draw - return points to player
					System.out.println("draw");
					player.setPoints(player.getPoints() + player.getBet());
					System.out.println("new player points: " + player.getPoints());
				} else {
					// player lost.
					System.out.printf("%s%s\n", player.getPlayerName(), " lost");
					System.out.println("new player points: " + player.getPoints());
				}	
				
				// clear bet if the user was in the round
				player.placeBet(0);
			} else {
				System.out.println(player.getPlayerName() + " not participating this round (after house roll)");
			}
			
			((ServerSideGameEngineCallback)gameEngineCallback).updateResult(player, this);
			((SimplePlayer)player).setParticipatingInRound(false);
		}
		
		houseRolling = false;
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
			System.out.println("placed bet on server for: " + player.getPlayerName());
			return true;
		} else {
			System.out.println("failed to place bet on server for: " + player.getPlayerName());
			return false;
		}
	}
	
	public void connectCallbackServer (Player player, int port) {
		((ServerSideGameEngineCallback)gameEngineCallback).connectToServer(player, port);
	}
	
	public void disconnectCallbackServer (Player player) {
		((ServerSideGameEngineCallback)gameEngineCallback).disconnectFromServer(player);
	}

	public void addPoints(Player player, int points) {
		for (Player currPlayer: players) {
			if (currPlayer == player) {
				currPlayer.setPoints(currPlayer.getPoints() + points);
				System.out.println("added " + points + " points to player: " + player.getPlayerName());
				continue;
			}
		}
	}
	
	public boolean setHouseRolling() {
		synchronized (lock) {
			if (houseRolling) {
				// if house already rolling then it wasn't set
				return false;
			} else {
				// set house to rolling
				houseRolling = true;
				return true;
			}
		}
	}
	
	public boolean isHouseRolling() {
		return houseRolling;
	}
	
	public String takeNextAvailableId() {
		String newId = Integer.toString(nextAvailableId);
		boolean newIdUpdated;

		// update the next available id for the next player
		do {
			nextAvailableId++;
			newIdUpdated = true;
			
			for (Player player: players) {
				if (Integer.parseInt(player.getPlayerId()) == nextAvailableId) {
					newIdUpdated = false;
					break;
				}
			}
		} while (!newIdUpdated);

		return newId;
	}
	
	public void setNextAvailableId(String nextAvailableId) {
		// reuse the id's from removed players
		this.nextAvailableId = Integer.parseInt(nextAvailableId);
	}
}
