/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ResultCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = 6950762006437120366L;
	private Player player = null;
	private DicePair dicePair = null;
	
	public ResultCommand(Player player, DicePair dicePair) {
		this.player = player;
		this.dicePair = dicePair;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		if (player.getPlayerId().contentEquals(((GameEngineClientStub)gameEngine).getPlayer().getPlayerId())) {
			// if it's the player, update GUI
			((GameEngineClientStub)gameEngine).getGameEngineCallback().result(player, dicePair, gameEngine);
		} else {
			int total = dicePair.getDice1() + dicePair.getDice2();
			
			// else output to console for other players
			System.out.println("Result for player: " + player.getPlayerName() + ": " + total);
		}
	}
}
