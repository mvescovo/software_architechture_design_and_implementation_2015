package model;

import java.io.Serializable;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player, Serializable {
	private static final long serialVersionUID = -1656658223445569521L;
	private String playerId = null;
	private String playerName = null;
	private int points;
	private int bet;
	private DicePair rollResult = null;
	private boolean isRolling = false;
	private boolean isParticipatingInRound = false;
	
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
		if ((bet > this.points) || (bet < 0)) {
			return false;
		} else {
			this.bet = bet;
			this.points -= bet;
			return true;
		}
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
	
	public void setRolling() {
		isRolling = true;
	}
	
	public void setNotRolling() {
		isRolling = false;
	}
	
	public boolean getIsRolling() {
		return isRolling;
	}
	
	public void setParticipatingInRound(boolean value) {
		isParticipatingInRound = value;
	}
	
	public boolean getIsParticipatingInRound() {
		return isParticipatingInRound;
	}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
}
