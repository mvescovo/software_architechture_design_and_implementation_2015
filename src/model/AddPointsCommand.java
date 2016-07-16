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
public class AddPointsCommand implements CommandInterface, Serializable {
	private static final long serialVersionUID = -8898436970611347884L;
	private String playerId = null;
	private int points;
	private Collection<Player> players = null;

	
	public AddPointsCommand(String playerId, int points) {
		this.playerId = playerId;
		this.points = points;
	}
	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		players = gameEngine.getAllPlayers();

		for (Player player: players) {
			if (player.getPlayerId().contentEquals(playerId)) {
				// found the player, add points for them
				((GameEngineImpl)gameEngine).addPoints(player, points);
				break;
			}
		}
	}
}
