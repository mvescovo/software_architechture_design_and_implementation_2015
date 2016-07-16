/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class CalculateResultsCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -3136688335648899058L;
	private String playerId = null;
	
	public CalculateResultsCommand(String playerId) {
		this.playerId = playerId;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		if (((GameEngineImpl)gameEngine).setHouseRolling()) {
			((GameEngineImpl)gameEngine).setPlayerIdWhoRolledHouse(playerId);
			gameEngine.calculateResult();
		} else {
			// house was already rolling, abort roll attempt
		}
	}
}
