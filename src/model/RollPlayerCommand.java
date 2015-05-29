/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Collection;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class RollPlayerCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -2847156363711340833L;
	private String playerId = null;
	private int initialDelay;
	private int finalDelay;
	private int delayIncrement;
	private Collection<Player> players = null;

	
	public RollPlayerCommand(String playerId, int initialDelay, int finalDelay,
			int delayIncrement) {
		this.playerId = playerId;
		this.initialDelay = initialDelay;
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
	}
	
	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		players = gameEngine.getAllPlayers();
		
		for (Player player: players) {
			if (player.getPlayerId().contentEquals(playerId)) {
				// found the player, roll them
				gameEngine.rollPlayer(player, initialDelay, finalDelay, delayIncrement);
				break;
			}
		}
	}
}
