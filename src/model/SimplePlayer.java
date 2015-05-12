package model;

import java.io.Serializable;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player, Serializable {
	private static final long serialVersionUID = -1656658223445569521L;
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private DicePair rollResult;
	
	public SimplePlayer(String playerId, String playerName, int points) {
		this.playerId = playerId;
		setPlayerName(playerName);
		setPoints(points);
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;		
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return this.playerId;
	}
	
	@Override
	public boolean placeBet(int bet) {
		// pre: bet <= points
		if (bet > this.points)
			throw new IllegalArgumentException("Bet too high for: ");
		
		this.bet = bet;
		this.points -= bet;
		
		return true;
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public DicePair getRollResult() {
		return this.rollResult;
	}

	@Override
	public void setRollResult(DicePair rollResult) {
		this.rollResult = rollResult;
		
	}
}
